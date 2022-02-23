package com.example.mytestapplicationframework.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.mytestapplicationframework.data.entities.Entity
import com.example.mytestapplicationframework.data.repository.EntityRepository
import com.example.mytestapplicationframework.utils.Resource

class DetailViewModel @ViewModelInject constructor(
    private val repository: EntityRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _entity = _id.switchMap { id ->
        repository.getEntity(id)
    }
    val entity: LiveData<Resource<Entity>> = _entity


    fun start(id: Int) {
        _id.value = id
    }

}
