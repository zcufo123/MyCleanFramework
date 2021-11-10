package com.example.mytestapplicationframework.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytestapplicationframework.data.entities.Entity

@Dao
interface EntityDao {

    @Query("SELECT * FROM entities")
    fun getAllEntities() : LiveData<List<Entity>>

    @Query("SELECT * FROM entities WHERE id = :id")
    fun getEntity(id: Int): LiveData<Entity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<Entity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Entity)


}