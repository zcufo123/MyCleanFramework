package com.example.mytestapplicationframework.di

import android.content.Context
import com.example.mytestapplicationframework.data.local.AppDatabase
import com.example.mytestapplicationframework.data.local.EntityDao
import com.example.mytestapplicationframework.data.remote.EntityRemoteDataSource
import com.example.mytestapplicationframework.data.remote.EntityService
import com.example.mytestapplicationframework.data.repository.EntityRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideService(retrofit: Retrofit): EntityService = retrofit.create(EntityService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(entityService: EntityService) = EntityRemoteDataSource(entityService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.entityDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: EntityRemoteDataSource,
                          localDataSource: EntityDao) =
        EntityRepository(remoteDataSource, localDataSource)
}