package com.example.mytestapplicationframework


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mytestapplicationframework.data.model.Entity
import com.example.mytestapplicationframework.domain.EntityDetailUseCase
import com.example.mytestapplicationframework.presentation.detail.DetailViewModel
import com.example.mytestapplicationframework.utils.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var entityDetailUseCase: EntityDetailUseCase

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this).close()
        detailViewModel = DetailViewModel(entityDetailUseCase)
    }

    @Test
    fun entitiesTestSuccess() {
        Mockito.`when`(entityDetailUseCase.getEntityDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(Resource(Resource.Status.SUCCESS, null, null)))
        val entity = detailViewModel.find(0).value
        assert(entity!!.status == Resource.Status.SUCCESS)
        assert(entity!!.data == null)
    }

    @Test
    fun entitiesTestError() {
        Mockito.`when`(entityDetailUseCase.getEntityDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(Resource(Resource.Status.ERROR, null, null)))
        val entity = detailViewModel.find(0).value
        assert(entity!!.status == Resource.Status.ERROR)
        assert(entity!!.data == null)
    }

    @Test
    fun entitiesTestLoading() {
        Mockito.`when`(entityDetailUseCase.getEntityDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(Resource(Resource.Status.LOADING, null, null)))
        val entity = detailViewModel.find(0).value
        assert(entity!!.status == Resource.Status.LOADING)
        assert(entity!!.data == null)
    }

    @Test
    fun entitiesTestSuccessWithOneEntity() {
        val source =
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
        Mockito.`when`(entityDetailUseCase.getEntityDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(Resource(Resource.Status.SUCCESS, source, null)))
        val entity = detailViewModel.find(0).value?.data
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
