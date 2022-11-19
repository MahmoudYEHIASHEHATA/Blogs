package com.shahry.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahry.local.model.AuthorLocalModel
import com.shahry.local.model.PostLocalModel

@Database(
    entities = [
        AuthorLocalModel::class,
        PostLocalModel::class
    ],
    version = DatabaseConstants.databaseVersion,
    // Enable export database schema to allow $[androidx.room.AutoMigration] in the next database versions
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authorDao() : AuthorDAO
    abstract fun postDao() : PostDAO
}