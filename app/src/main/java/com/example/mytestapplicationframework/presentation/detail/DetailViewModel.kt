package com.example.mytestapplicationframework.presentation.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplicationframework.data.model.Entity
import com.example.mytestapplicationframework.domain.EntityDetailUseCase
import com.example.mytestapplicationframework.utils.Resource

class DetailViewModel @ViewModelInject constructor(
    private val entityDetailUseCase: EntityDetailUseCase
) : ViewModel() {

    fun find(id: Int): LiveData<Resource<Entity>> {
        return entityDetailUseCase.getEntityDetail(id)
    }

}
