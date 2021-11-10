package com.example.mytestapplicationframework.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mytestapplicationframework.data.repository.EntityRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: EntityRepository
) : ViewModel() {

    val characters = repository.getEntities()
}
