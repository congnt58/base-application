package com.example.cong_nt.myappbase.base.retrofit;

import com.example.cong_nt.myappbase.model.CheckPhoneData;
import com.example.cong_nt.myappbase.model.LoginData;
import com.example.cong_nt.myappbase.model.ObjResponse;
import com.example.cong_nt.myappbase.model.RedirectResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GgApiRequest {

    @GET("/api/driver/redirect-server")
    Observable<ObjResponse<RedirectResponse>> redirectServer(@Query("app_code") String appCode, @Query("region_name") String regionName, @Query("develop") int develop);

    @POST("/api/driver/login")
    @FormUrlEncoded
    Observable<ObjResponse<LoginData>> login(@Field("phone_number") String phoneNumber, @Field("password") String password);

    @POST("/api/driver/register/check-phone")
    @FormUrlEncoded
    Observable<ObjResponse<CheckPhoneData>> checkPhoneNumber(@Field("phone_number") String phoneNumber);
}
