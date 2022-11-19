package com.shahry.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahry.local.model.PostLocalModel

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPosts(posts: List<PostLocalModel>): List<Long>


    @Query("SELECT * FROM post WHERE authorId = :authorId ")
    suspend fun getAllAuthors(authorId: Int): List<PostLocalModel>
}