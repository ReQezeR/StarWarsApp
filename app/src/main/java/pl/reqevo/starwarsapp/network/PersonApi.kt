package pl.reqevo.starwarsapp.network

import pl.reqevo.starwarsapp.models.PersonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PersonApi {
    @GET("people")
    fun getPeople(): Call<PersonResponse>
}