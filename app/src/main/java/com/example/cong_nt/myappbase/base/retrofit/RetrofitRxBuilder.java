package com.example.cong_nt.myappbase.base.retrofit;

import com.example.cong_nt.myappbase.MyApp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRxBuilder {
    private static Builder builder;
    private static Retrofit baseRetrofit;

    private RetrofitRxBuilder(@NotNull Builder requestBuiler) {
        this.builder = requestBuiler;
    }

    public static GgApiRequest getGgRequest(InternetConnectionListener listener){
        if (builder == null){
            return null;
        }
        builder.setInternetConnectionListener(listener);
        if (baseRetrofit == null) {
            baseRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(builder.apiUrl)
                    .client(getUnsafeOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return baseRetrofit.create(GgApiRequest.class);
    }

    public static void setNewBuilder(Builder builder_){
        baseRetrofit = null;
        builder = builder_;
    }

    public static final class Builder {
        private String apiUrl;
        private String authToken;
        private String language;
        private boolean sslMode;
        private boolean showLoging;
        private InternetConnectionListener internetConnectionListener;

        public void setInternetConnectionListener(InternetConnectionListener internetConnectionListener) {
            this.internetConnectionListener = internetConnectionListener;
        }

        public void removeConnectionListener(){
            this.internetConnectionListener = null;
        }

        public Builder url(String url) {
            apiUrl = url;
            return this;
        }

        public Builder token(String authToken) {
            this.authToken = authToken;
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public Builder sslMode(boolean sslMode) {
            this.sslMode = sslMode;
            return this;
        }

        public Builder showLoging(boolean showLoging) {
            this.showLoging = showLoging;
            return this;
        }

        public RetrofitRxBuilder build() {
            return new RetrofitRxBuilder(this);
        }
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient;
            OkHttpClient.Builder okHttpClientBuiler = new OkHttpClient.Builder();
            okHttpClientBuiler.connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS);

            Interceptor headerAuthorizationInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    okhttp3.Request request = chain.request();
//                        Headers headers = request.headers().newBuilder().add("x-access-token", builder.authToken).build();
//                        request = request.newBuilder().headers(headers).build();
                    if (builder.authToken != null) {
                        request = request.newBuilder().addHeader("x-access-token", builder.authToken).build();
                    }
                    if (builder.language != null) {
                        request = request.newBuilder().addHeader("language", builder.language).build();
                    }
                    return chain.proceed(request);
                }
            };
            okHttpClientBuiler.addInterceptor(headerAuthorizationInterceptor);
            okHttpClientBuiler.addInterceptor(new NetworkConnectionInterceptor() {
                @Override
                public boolean isInternetAvailable() {
                    return MyApp.getIntance().isNetworkAvailable();
                }

                @Override
                public void onInternetUnavailable() {
                    builder.internetConnectionListener.onInternetUnavailbale();
                }
            });

            if (builder.sslMode) {
                okHttpClientBuiler.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                        .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            }
            if (builder.showLoging) {
                okHttpClientBuiler.addInterceptor(httpLoggingInterceptor);
            }
            okHttpClient = okHttpClientBuiler.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
