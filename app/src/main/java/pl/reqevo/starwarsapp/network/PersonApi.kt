package pl.reqevo.starwarsapp.network

import pl.reqevo.starwarsapp.models.Person
import pl.reqevo.starwarsapp.models.PersonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {
    @GET("people")
    fun getPeople(@Query("page") page: Int): Call<PersonResponse>

    @GET("people/{id}/")
    fun getPerson(@Path("id") id: Int): Call<Person>
}