package com.example.mytestapplicationframework.data.remote

import javax.inject.Inject

class EntityRemoteDataSource @Inject constructor(
    private val entityService: EntityService
): BaseDataSource() {

    suspend fun getEntities() = getResult { entityService.getAllEntities() }
    suspend fun getEntity(id: Int) = getResult { entityService.getEntity(id) }
}