package com.shahry.blogs.di

import android.content.Context
import androidx.room.Room
import com.shahry.local.database.AppDatabase
import com.shahry.local.database.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that holds database related classes
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    /**
     * Provides [AppDatabase] instance
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DatabaseConstants.databaseName)
            .build()
    }

    /**
     * Provides [AuthorDAO] instance
     */
    @Provides
    @Singleton
    fun provideAuthorDAO(appDatabase: AppDatabase) = appDatabase.authorDao()



    /**
     * Provides [PostDAO] instance
     */
    @Provides
    @Singleton
    fun providePostDAO(appDatabase: AppDatabase) = appDatabase.postDao()

}