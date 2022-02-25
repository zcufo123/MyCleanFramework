package com.example.mytestapplicationframework.di

import android.content.Context
import com.example.mytestapplicationframework.data.local.AppDatabase
import com.example.mytestapplicationframework.data.local.EntityDao
import com.example.mytestapplicationframework.data.remote.EntityRemoteDataSource
import com.example.mytestapplicationframework.data.remote.EntityService
import com.example.mytestapplicationframework.data.repository.EntityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(entityService: EntityService) =
        EntityRemoteDataSource(entityService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.entityDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: EntityRemoteDataSource,
        localDataSource: EntityDao
    ) = EntityRepository(remoteDataSource, localDataSource)

}