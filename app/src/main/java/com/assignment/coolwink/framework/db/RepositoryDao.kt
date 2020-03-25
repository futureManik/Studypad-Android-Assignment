package com.assignment.coolwink.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Insert(onConflict = REPLACE)
    fun insertRepo(projects: RepositoryEntity)

    @Query("SELECT * FROM projects")
    fun getAllRepo(): MutableList<RepositoryEntity>
}