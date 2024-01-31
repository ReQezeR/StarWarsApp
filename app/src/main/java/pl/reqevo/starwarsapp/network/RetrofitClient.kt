package pl.reqevo.starwarsapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // Base URL must end in /
    private const val BASE_URL = "https://swapi.dev/api/"

    fun getInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}

