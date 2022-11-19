package com.shahry.data.mapper

import com.shahry.common.Mapper
import com.shahry.data.model.AuthorDTO
import com.shahry.domain.entity.AuthorEntity
import javax.inject.Inject

class AuthorDataDomainMapper @Inject constructor() : Mapper<AuthorDTO, AuthorEntity> {
    override fun from(i: AuthorDTO?): AuthorEntity {
        return AuthorEntity(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            lat = i?.lat ?: "",
            long = i?.long ?: ""
        )
    }

    override fun to(o: AuthorEntity?): AuthorDTO {
        return AuthorDTO(
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