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
    "https://xv7rwt21-5000.asse.devtunnels.ms/admin/"

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

interface MessageObj {
    val id: Int
    val user_id: Int
    val phone: String
    val message: String
    val msg_type: MsgType
    val created_at: String
}

enum class GenderObj {
    FEMALE, MALE
}

@Serializable
data class DetailsObj(
    val id: Int,
    val description: String,
    val education: String,
    val profession: String,
    val maritalStatus: String,
    val religion: String,
    val ethnicity: String,
    val userId: Int
)

@Serializable
data class PersonObj(
    val id: Int,
    val name: String,
    val phone: String,
    val age: Int,
    val gender: GenderObj,
    val county: String,
    val town: String,
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
    suspend fun fetchUsers(): Response<List<PersonObj>>

    @GET("messages")
    suspend fun fetchMessages(): List<MessageObj>
}

object PenziApi {
    val retrofitService: PenziApiService by lazy {
        retrofit.create(PenziApiService::class.java)
    }
}
