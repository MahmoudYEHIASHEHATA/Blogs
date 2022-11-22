package com.shahry.remote.mapper

import com.shahry.common.Mapper
import com.shahry.data.model.AuthorDTO
import com.shahry.remote.model.AuthorResponseNetwork
import javax.inject.Inject

class AuthorNetworkDataMapper  @Inject constructor() :Mapper<AuthorResponseNetwork,AuthorDTO>{
    override fun from(i: AuthorResponseNetwork?): AuthorDTO {
        return AuthorDTO(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
            lat = i?.address?.latitude ?: "",
            long = i?.address?.longitude ?: ""
        )
    }

    override fun to(o: AuthorDTO?): AuthorResponseNetwork {
        return AuthorResponseNetwork()
    }
}