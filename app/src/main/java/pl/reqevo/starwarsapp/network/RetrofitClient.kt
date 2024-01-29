package pl.reqevo.starwarsapp.network

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {

    // Base URL must end in /
    private const val BASE_URL = "https://swapi.dev/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}

