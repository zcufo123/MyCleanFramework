package com.example.mytestapplicationframework.domain

import com.example.mytestapplicationframework.data.repository.EntityRepository
import com.example.mytestapplicationframework.utils.performGetOperation

class EntityDetailUseCase(private val repository: EntityRepository) {

    fun getEntityDetail(id: Int) = performGetOperation(
        databaseQuery = { repository.getLocalEntity(id) },
        networkCall = { repository.getRemoteEntity(id) },
        saveCallResult = { repository.saveEntity(it) }
    )
}