package com.example.mytestapplicationframework.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplicationframework.data.entities.Entity
import com.example.mytestapplicationframework.domain.EntityListUseCase
import com.example.mytestapplicationframework.utils.Resource

class MainViewModel @ViewModelInject constructor(
    private val entityListUseCase: EntityListUseCase
) : ViewModel() {

    val entities: LiveData<Resource<List<Entity>>> get() = entityListUseCase.getEntities()

}
