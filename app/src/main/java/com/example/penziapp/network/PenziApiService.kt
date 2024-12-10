package com.example.penziapp.network

//import retrofit2.converter.scalars.ScalarsConverterFactory
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL =
    "https://9pz93gd2-5000.euw.devtunnels.ms/admin/"

val client = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor())
    .build()

// create retrofit object
private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//interface LoginValuesObj  {
//    var username: String
//    var password: String
//}
data class LoginValuesObj(
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
)

enum class MsgType {
    Incoming, Outgoing
}

data class MessageObj (
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("msg_type")
    val msg_type: String,
    @SerializedName("created_at")
    val created_at: String
)

enum class GenderObj {
    FEMALE, MALE
}

//@Serializable
data class DetailsObj(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("education")
    val education: String,
    @SerializedName("profession")
    val profession: String,
    @SerializedName("maritalStatus")
    val maritalStatus: String,
    @SerializedName("religion")
    val religion: String,
    @SerializedName("ethnicity")
    val ethnicity: String,
    @SerializedName("userId")
    val userId: Int
)

//@Serializable
data class PersonObj(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("county")
    val county: String,
    @SerializedName("town")
    val town: String,
    @SerializedName("details")
    val details: DetailsObj? = null // Optional field with nullable type
)

@Serializable
data class LoginRespBodyObj(
    var message: String = "",
    var token: String = "",
    var username: String = ""
)

// define how the service talks to the web server
interface PenziApiService {
    @POST("login")
    suspend fun login(@Body loginValues: LoginValuesObj): LoginRespBodyObj

    @GET("users")
    suspend fun fetchUsers(): Collection<PersonObj>

    @GET("messages")
    suspend fun fetchMessages(): Collection<MessageObj>
}

object PenziApi {
    val retrofitService: PenziApiService by lazy {
        retrofit.create(PenziApiService::class.java)
    }
}
