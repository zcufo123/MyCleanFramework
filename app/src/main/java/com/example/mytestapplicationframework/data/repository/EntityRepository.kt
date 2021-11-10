package com.example.mytestapplicationframework.data.repository

import com.example.mytestapplicationframework.data.local.EntityDao
import com.example.mytestapplicationframework.data.remote.EntityRemoteDataSource
import com.example.mytestapplicationframework.utils.performGetOperation
import javax.inject.Inject

class EntityRepository @Inject constructor(
    private val remoteDataSource: EntityRemoteDataSource,
    private val localDataSource: EntityDao
) {

    fun getEntity(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getEntity(id) },
        networkCall = { remoteDataSource.getEntity(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getEntities() = performGetOperation(
        databaseQuery = { localDataSource.getAllEntities() },
        networkCall = { remoteDataSource.getEntities() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}