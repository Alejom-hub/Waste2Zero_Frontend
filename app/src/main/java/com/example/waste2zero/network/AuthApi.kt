package com.example.waste2zero.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val BASE_URL = "https://w2z.matwa.is-cool.dev/"
private const val LOGIN_PATH = "api/auth/login"
private const val REGISTER_PATH = "api/auth/register"

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

interface AuthApiService {
    @GET("health")
    suspend fun health(): Response<JsonObject>

    @POST(LOGIN_PATH)
    suspend fun login(@Body request: LoginRequest): Response<JsonObject>

    @POST(REGISTER_PATH)
    suspend fun register(@Body request: RegisterRequest): Response<JsonObject>
}

object AuthApi {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val service: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
}

class AuthRepository(
    private val service: AuthApiService = AuthApi.service
) {
    suspend fun checkHealth(): Result<String> {
        return runCatching {
            val response = service.health()
            if (response.isSuccessful) {
                "API disponible (health OK)"
            } else {
                "Health fallo: HTTP ${response.code()}"
            }
        }
    }

    suspend fun login(email: String, password: String): Result<String> {
        return runCatching {
            val response = service.login(LoginRequest(email = email, password = password))
            if (response.isSuccessful) {
                "Login exitoso"
            } else {
                val details = response.errorBody()?.string().orEmpty()
                "Login fallo: HTTP ${response.code()} ${details.take(200)}"
            }
        }
    }

    suspend fun register(name: String, email: String, password: String): Result<String> {
        return runCatching {
            val response = service.register(
                RegisterRequest(name = name, email = email, password = password)
            )
            if (response.isSuccessful) {
                "Registro exitoso"
            } else {
                val details = response.errorBody()?.string().orEmpty()
                "Registro fallo: HTTP ${response.code()} ${details.take(200)}"
            }
        }
    }
}
