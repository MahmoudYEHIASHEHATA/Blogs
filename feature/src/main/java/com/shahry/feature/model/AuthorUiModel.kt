package com.shahry.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorUiModel(
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val lat: String,
    val long: String
): Parcelable
