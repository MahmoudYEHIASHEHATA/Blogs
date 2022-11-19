package com.shahry.domain.entity

data class AuthorEntity(
    val id: String,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val address: Address
)
