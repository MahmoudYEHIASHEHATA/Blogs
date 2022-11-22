package com.shahry.remote.utils

import com.shahry.remote.mapper.PostNetworkDataMapper
import com.shahry.remote.model.AuthorResponseNetwork
import com.shahry.remote.model.PostResponseNetwork


/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun generateAuthor(): List<AuthorResponseNetwork> {
            return listOf(
                AuthorResponseNetwork(
                    id = 16,
                    name = "Mahmoud Shehatah",
                    userName = "test",
                    email = "mahmoudyahiashhatah@gmail.com",
                )
            )
        }

        fun generatePosts(): List<PostResponseNetwork> {
            return listOf(
                PostResponseNetwork(
                    id = 16,
                )
            )
        }
    }

}