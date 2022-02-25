package com.example.mytestapplicationframework

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.mytestapplicationframework.data.model.Entity
import com.example.mytestapplicationframework.domain.EntityDetailUseCase
import com.example.mytestapplicationframework.presentation.detail.DetailFragment
import com.example.mytestapplicationframework.utils.Constant
import com.example.mytestapplicationframework.utils.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import javax.inject.Inject


@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class DetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Mock
    @Inject
    lateinit var entityDetailUseCase: EntityDetailUseCase

    @Before
    fun setup() {
        entityDetailUseCase = Mockito.mock(EntityDetailUseCase::class.java)
        hiltRule.inject()
    }

    @Test
    fun testLoading() {
        Mockito.`when`(entityDetailUseCase.getEntityDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(Resource(Resource.Status.LOADING, null, null)))
        val fragmentArgs = bundleOf(Constant.ENTITY_ID to 0)
        launchFragmentInHiltContainer<DetailFragment>(fragmentArgs)
        onView(withId(R.id.progress_bar)).check(
            ViewAssertions.matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun testError() {
        Mockito.`when`(entityDetailUseCase.getEntityDetail(Mockito.anyInt()))
            .thenReturn(MutableLiveData(Resource(Resource.Status.ERROR, null, null)))
        val fragmentArgs = bundleOf(Constant.ENTITY_ID to 0)
        launchFragmentInHiltContainer<DetailFragment>(fragmentArgs)
        onView(withId(R.id.progress_bar)).check(
            ViewAssertions.matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun testSuccess() {
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
        val fragmentArgs = bundleOf(Constant.ENTITY_ID to 0)
        launchFragmentInHiltContainer<DetailFragment>(fragmentArgs)
        onView(withId(R.id.progress_bar)).check(
            ViewAssertions.matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
        onView(withId(R.id.detail_cl)).check(
            ViewAssertions.matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

}