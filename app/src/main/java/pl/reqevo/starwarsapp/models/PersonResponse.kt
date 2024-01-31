package pl.reqevo.starwarsapp.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PersonResponse(
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?,
    @JsonProperty("results")
    val results: List<Person>
)