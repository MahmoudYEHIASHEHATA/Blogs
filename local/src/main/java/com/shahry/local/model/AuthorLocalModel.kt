package com.shahry.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class AuthorLocalModel(
    @PrimaryKey
    var id: Int =-1,
    var name: String ="",
    var userName: String = "",
    var email: String = "",
    var avatarUrl: String = "",
    var lat: String ="",
    var long: String=""
)
