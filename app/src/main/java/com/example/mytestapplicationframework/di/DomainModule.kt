package com.example.mytestapplicationframework.di

import com.example.mytestapplicationframework.data.repository.EntityRepository
import com.example.mytestapplicationframework.domain.EntityDetailUseCase
import com.example.mytestapplicationframework.domain.EntityListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun provideEntityListUseCase(entityRepository: EntityRepository) =
        EntityListUseCase(entityRepository)

    @Provides
    fun provideEntityDetailUseCase(entityRepository: EntityRepository) =
        EntityDetailUseCase(entityRepository)

}