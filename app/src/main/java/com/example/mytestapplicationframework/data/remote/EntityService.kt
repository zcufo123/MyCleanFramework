package com.example.mytestapplicationframework.data.remote

import com.example.mytestapplicationframework.data.model.Entity
import com.example.mytestapplicationframework.data.model.EntityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EntityService {
    @GET("character")
    suspend fun getAllEntities() : Response<EntityList>

    @GET("character/{id}")
    suspend fun getEntity(@Path("id") id: Int): Response<Entity>
}