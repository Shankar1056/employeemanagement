package comcater.employeemanagement.retrofitnetwork;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import comcater.employeemanagement.common.ConstantValue;
import comcater.employeemanagement.model.AssignWorkModel;
import comcater.employeemanagement.model.EmployeeLatLonModel;
import comcater.employeemanagement.model.UserModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataProvider extends AppCompatActivity implements ServiceMethods {
    private Context context;

    private ApiRetrofitService createRetrofitService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValue.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiRetrofitService.class);

    }

    public  RetrofitDataProvider(Context context)
    {
        this.context = context;
    }

    @Override
    public void login(String email, String password, String device_token, final DownlodableCallback<UserModel> callback) {
        createRetrofitService().login(email, password, device_token).enqueue(
                new Callback<UserModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UserModel> call, @NonNull final Response<UserModel> response) {
                        if (response.code()==200) {

                            UserModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }

    @Override
    public void saveEmpLatLon(String api_token, String emp_id, String lat, String lon, String address, final DownlodableCallback<EmployeeLatLonModel> callback) {
        createRetrofitService().saveEmpLatLon(api_token, emp_id, lat, lon, address).enqueue(
                new Callback<EmployeeLatLonModel>() {
                    @Override
                    public void onResponse(@NonNull Call<EmployeeLatLonModel> call, @NonNull final Response<EmployeeLatLonModel> response) {
                        if (response.code()==200) {

                            EmployeeLatLonModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<EmployeeLatLonModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }

    @Override
    public void getAssignTarget(String api_token, String emp_id, final DownlodableCallback<AssignWorkModel> callback) {
        createRetrofitService().getAssignTarget(api_token, emp_id).enqueue(
                new Callback<AssignWorkModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AssignWorkModel> call, @NonNull final Response<AssignWorkModel> response) {
                        if (response.code()==200) {

                            AssignWorkModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AssignWorkModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }


}
