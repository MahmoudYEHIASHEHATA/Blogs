package com.shahry.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class AuthorLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val lat: String,
    val long: String
)
