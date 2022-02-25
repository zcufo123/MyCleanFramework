package com.example.mytestapplicationframework

import androidx.lifecycle.MutableLiveData
import com.example.mytestapplicationframework.data.model.Entity
import com.example.mytestapplicationframework.domain.EntityListUseCase
import com.example.mytestapplicationframework.presentation.main.MainViewModel
import com.example.mytestapplicationframework.utils.Resource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var entityListUseCase: EntityListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this).close()
        mainViewModel = MainViewModel(entityListUseCase)
    }

    @Test
    fun entitiesTestSuccess() {
        val entityList = listOfNotNull(null)
        Mockito.`when`(entityListUseCase.getEntities())
            .thenReturn(MutableLiveData(Resource(Resource.Status.SUCCESS, entityList, null)))
        assert(mainViewModel.entities.value?.status == Resource.Status.SUCCESS)
        assert(mainViewModel.entities.value?.data.isNullOrEmpty())
    }

    @Test
    fun entitiesTestError() {
        val entityList = listOfNotNull(null)
        Mockito.`when`(entityListUseCase.getEntities())
            .thenReturn(MutableLiveData(Resource(Resource.Status.ERROR, entityList, null)))
        assert(mainViewModel.entities.value?.status == Resource.Status.ERROR)
        assert(mainViewModel.entities.value?.data.isNullOrEmpty())
    }

    @Test
    fun entitiesTestLoading() {
        val entityList = listOfNotNull(null)
        Mockito.`when`(entityListUseCase.getEntities())
            .thenReturn(MutableLiveData(Resource(Resource.Status.LOADING, entityList, null)))
        assert(mainViewModel.entities.value?.status == Resource.Status.LOADING)
        assert(mainViewModel.entities.value?.data.isNullOrEmpty())
    }

    @Test
    fun entitiesTestSuccessWithOneEntity() {
        val entityList = listOfNotNull(
            Entity(
                "test",
                "male",
                0,
                "image",
                "charlie",
                "human",
                "live",
                "good",
                ""
            )
        )
        Mockito.`when`(entityListUseCase.getEntities())
            .thenReturn(MutableLiveData(Resource(Resource.Status.SUCCESS, entityList, null)))
        assert(mainViewModel.entities.value?.status == Resource.Status.SUCCESS)
        val entity = mainViewModel.entities.value?.data?.get(0)
        assert(entity!!.created == "test")
        assert(entity.gender == "male")
        assert(entity.id == 0)
        assert(entity.image == "image")
        assert(entity.name == "charlie")
        assert(entity.species == "human")
        assert(entity.status == "live")
        assert(entity.type == "good")
        assert(entity.url == "")
    }
}