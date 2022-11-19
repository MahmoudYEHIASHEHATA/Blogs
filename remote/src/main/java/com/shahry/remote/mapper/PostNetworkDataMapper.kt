package com.shahry.remote.mapper

import com.shahry.common.Mapper
import com.shahry.data.model.PostDTO
import com.shahry.remote.model.PostResponseNetwork
import javax.inject.Inject

class PostNetworkDataMapper @Inject constructor() :Mapper<PostResponseNetwork,PostDTO> {
    override fun from(i: PostResponseNetwork?): PostDTO {
        return PostDTO(
            id = i?.id ?: -1,
            date = i?.date ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: "",
            authorId = i?.authorId ?: -1
        )
    }

    override fun to(o: PostDTO?): PostResponseNetwork {
        return PostResponseNetwork()
    }
}