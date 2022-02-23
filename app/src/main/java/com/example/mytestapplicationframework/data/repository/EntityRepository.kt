package com.example.mytestapplicationframework.data.repository

import com.example.mytestapplicationframework.data.entities.Entity
import com.example.mytestapplicationframework.data.local.EntityDao
import com.example.mytestapplicationframework.data.remote.EntityRemoteDataSource
import com.example.mytestapplicationframework.utils.performGetOperation
import javax.inject.Inject

class EntityRepository @Inject constructor(
    private val remoteDataSource: EntityRemoteDataSource,
    private val localDataSource: EntityDao
) {
    fun getLocalEntity(id: Int) = localDataSource.getEntity(id)

    suspend fun getRemoteEntity(id: Int) = remoteDataSource.getEntity(id)

    suspend fun saveEntity(entity: Entity) = localDataSource.insert(entity)

    fun getLocalEntities() = localDataSource.getAllEntities()

    suspend fun getRemoteEntities() = remoteDataSource.getEntities()

    suspend fun saveEntities(entities: List<Entity>) = localDataSource.insertAll(entities)
}