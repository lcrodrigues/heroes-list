package com.example.heroeslist.data.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
)