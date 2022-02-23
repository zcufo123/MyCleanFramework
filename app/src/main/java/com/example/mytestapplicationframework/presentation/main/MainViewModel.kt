package com.example.mytestapplicationframework.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mytestapplicationframework.data.repository.EntityRepository
import com.example.mytestapplicationframework.domain.EntityListUseCase

class MainViewModel @ViewModelInject constructor(
    entityListUseCase: EntityListUseCase
) : ViewModel() {

    val entities = entityListUseCase.getEntities()
}
