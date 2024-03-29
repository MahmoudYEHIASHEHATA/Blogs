package com.shahry.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahry.local.model.AuthorLocalModel

@Dao
interface AuthorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAuthors(authors : List<AuthorLocalModel>): List<Long>

    @Query("SELECT * FROM author")
    suspend fun getAllAuthors(): List<AuthorLocalModel>
}