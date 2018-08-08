package comcater.employeemanagement.retrofitnetwork;


import comcater.employeemanagement.common.ConstantValue;
import comcater.employeemanagement.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRetrofitService {



    @POST(ConstantValue.LOGIN)
    @FormUrlEncoded
    Call<UserModel> login(@Field("email") String email, @Field("password") String password);


}
