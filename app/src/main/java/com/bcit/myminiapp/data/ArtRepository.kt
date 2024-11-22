package com.bcit.myminiapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArtRepository(private val client: HttpClient) {

    suspend fun getArtwork(): Art {
        val response = client.get(Endpoints.FIELDS.url)
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Art::class.java)
    }
}