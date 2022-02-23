package com.example.mytestapplicationframework.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mytestapplicationframework.data.repository.EntityRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: EntityRepository
) : ViewModel() {

    val entities = repository.getEntities()
}
