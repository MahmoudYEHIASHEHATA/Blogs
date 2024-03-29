package com.shahry.remote.model

data class AuthorResponseNetwork(
    val id: Int = 0,
    val name: String = "",
    val userName: String = "",
    val email: String = "",
    val avatarUrl: String = "",
    val address: Address? = null,
)
