package com.example.cong_nt.myappbase.base.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public abstract class NetworkConnectionInterceptor implements Interceptor {
    public abstract boolean isInternetAvailable();

    public abstract void onInternetUnavailable();

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        if (!isInternetAvailable()) {
            onInternetUnavailable();
        }
        return chain.proceed(request);
    }
}
