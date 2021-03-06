package com.services.services.Utils;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.services.services.ApiClient.ClientApi;
import com.services.services.Interface.ServiceInterface;
import com.services.services.ModelClass.AllServicePojo;
import com.services.services.ModelClass.RegisterPojo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyMVVM extends ViewModel {

    ServiceInterface serviceInterface = ClientApi.apiClient().create(ServiceInterface.class);

    // Register

    private MutableLiveData<RegisterPojo> userRegister;

    public LiveData<RegisterPojo> userRegisteration(final Activity activity, String phone, String reg_id, String device_type, String country_code) {
        userRegister = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            com.omninos.util_data.CommonUtils.showProgress(activity);
            serviceInterface.register(phone, reg_id, device_type, country_code).enqueue(new Callback<RegisterPojo>() {
                @Override
                public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                    com.omninos.util_data.CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        userRegister.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<RegisterPojo> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return userRegister;
    }

    //matchOtp

    private MutableLiveData<RegisterPojo> matchOtp;

    public LiveData<RegisterPojo> userMatchOtp(final Activity activity, String id, String otp) {
        matchOtp = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            com.omninos.util_data.CommonUtils.showProgress(activity);
            serviceInterface.matchOtp(id, otp).enqueue(new Callback<RegisterPojo>() {
                @Override
                public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                    com.omninos.util_data.CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        matchOtp.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<RegisterPojo> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return matchOtp;
    }

    //Resend

    private MutableLiveData<RegisterPojo> resendOtp;

    public LiveData<RegisterPojo> UserResendOtp(final Activity activity, String id) {
        resendOtp = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            com.omninos.util_data.CommonUtils.showProgress(activity);
            serviceInterface.resendOtp(id).enqueue(new Callback<RegisterPojo>() {
                @Override
                public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                    com.omninos.util_data.CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        resendOtp.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<RegisterPojo> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return resendOtp;
    }

    //UserInfo

    private MutableLiveData<RegisterPojo> updateUserInfo;

    public LiveData<RegisterPojo> updateUserInfoRegister(final Activity activity, RequestBody id, RequestBody name, RequestBody email, RequestBody gender, RequestBody address, MultipartBody.Part image) {
        updateUserInfo = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            com.omninos.util_data.CommonUtils.showProgress(activity);
            serviceInterface.updateUserInfo(id, name, email, gender, address, image).enqueue(new Callback<RegisterPojo>() {
                @Override
                public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                    com.omninos.util_data.CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        updateUserInfo.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<RegisterPojo> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return updateUserInfo;
    }

    //UserDetail

    private MutableLiveData<RegisterPojo> UserDetail;

    public LiveData<RegisterPojo> userGetDetail(final Activity activity, String user_id) {
        UserDetail = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            com.omninos.util_data.CommonUtils.showProgress(activity);
            serviceInterface.UserDetail(user_id).enqueue(new Callback<RegisterPojo>() {
                @Override
                public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                    com.omninos.util_data.CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        UserDetail.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<RegisterPojo> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return UserDetail;
    }

    //serviceList

    private MutableLiveData<AllServicePojo> serviceList;

    public LiveData<AllServicePojo> AllServiceList(final Activity activity) {
        serviceList = new MutableLiveData<>();
        if (CommonUtils.isNetworkConnected(activity)) {
            com.omninos.util_data.CommonUtils.showProgress(activity);
            serviceInterface.serviceList().enqueue(new Callback<AllServicePojo>() {
                @Override
                public void onResponse(Call<AllServicePojo> call, Response<AllServicePojo> response) {
                    com.omninos.util_data.CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        serviceList.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<AllServicePojo> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return serviceList;
    }


}