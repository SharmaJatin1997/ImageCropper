package com.services.services.Interface;

import com.services.services.ModelClass.AllServicePojo;
import com.services.services.ModelClass.RegisterPojo;

import org.checkerframework.common.reflection.qual.GetClass;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceInterface {

    @FormUrlEncoded
    @POST("loginAndRegister")
    Call<RegisterPojo> register(@Field("phone") String phone, @Field("reg_id") String reg_id, @Field("device_type") String device_type, @Field("country_code") String country_code);

    @FormUrlEncoded
    @POST("matchOtp")
    Call<RegisterPojo> matchOtp(@Field("id") String id, @Field("otp") String otp);

    @FormUrlEncoded
    @POST("resendOtp")
    Call<RegisterPojo> resendOtp(@Field("id") String id);

    @Multipart
    @POST("updateInfo")
    Call<RegisterPojo> updateUserInfo(@Part("id") RequestBody id,@Part("name") RequestBody name, @Part("email") RequestBody email, @Part("gender") RequestBody gender,@Part("address") RequestBody address, @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("getUserDetails")
    Call<RegisterPojo> UserDetail(@Field("user_id") String user_id);

    @GET("serviceList")
    Call<AllServicePojo> serviceList();
}