package com.shahry.local.mapper

import com.shahry.common.Mapper
import com.shahry.data.model.AuthorDTO
import com.shahry.local.model.AuthorLocalModel
import javax.inject.Inject

class AuthorLocalDataMapper @Inject constructor() : Mapper<AuthorLocalModel, AuthorDTO> {
    override fun from(i: AuthorLocalModel?): AuthorDTO {
        return AuthorDTO(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            lat = i?.lat ?: "",
            long = i?.long ?: ""
        )
    }

    override fun to(o: AuthorDTO?): AuthorLocalModel {
        return AuthorLocalModel(
            id = o?.id ?: -1,
            name = o?.name ?: "",
            userName = o?.userName ?: "",
            email = o?.email ?: "",
            avatarUrl = o?.avatarUrl ?: "",
            lat = o?.lat ?: "",
            long = o?.long ?: ""
        )
    }
}