package com.example.mytestapplicationframework.domain

import com.example.mytestapplicationframework.data.repository.EntityRepository
import com.example.mytestapplicationframework.utils.performGetOperation
import javax.inject.Inject

class EntityListUseCase @Inject constructor(private val repository: EntityRepository) {

    fun getEntities() = performGetOperation(
        databaseQuery = { repository.getLocalEntities() },
        networkCall = { repository.getRemoteEntities() },
        saveCallResult = { repository.saveEntities(it.results) }
    )

}