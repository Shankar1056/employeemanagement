package comcater.employeemanagement.retrofitnetwork;


import comcater.employeemanagement.common.ConstantValue;
import comcater.employeemanagement.model.AssignWorkModel;
import comcater.employeemanagement.model.EmployeeLatLonModel;
import comcater.employeemanagement.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiRetrofitService {


    @Headers("x-api-key:"+ConstantValue.APIKEY)
    @POST(ConstantValue.LOGIN)
    @FormUrlEncoded
    Call<UserModel> login(@Field("email") String email, @Field("password") String password, @Field("device_token") String device_token);

    @Headers("x-api-key:"+ConstantValue.APIKEY)
    @POST(ConstantValue.LOGIN)
    @FormUrlEncoded
    Call<EmployeeLatLonModel> saveEmpLatLon(@Header("api_token") String api_token, @Field("emp_id") String emp_id, @Field("lat") String lat, @Field("lon") String lon, @Field("address") String address);

    @Headers("x-api-key:"+ConstantValue.APIKEY)
    @POST(ConstantValue.GETASSIGNTARGET)
    @FormUrlEncoded
    Call<AssignWorkModel> GetAssignTarget(@Header("api_token") String api_token, @Field("emp_id") String emp_id);


}
