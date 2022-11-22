package com.shahry.local.utils

import com.shahry.local.model.AuthorLocalModel
import com.shahry.local.model.PostLocalModel


/**
 * Dummy data generator for tests
 */
class TestData {

    companion object {
        fun generateAuthorsItems(): List<AuthorLocalModel> {
            val item1 = AuthorLocalModel(1, "Mahmoud", "MH")
            val item2 = AuthorLocalModel(2, "Mahmoud", "MH")
            val item3 = AuthorLocalModel(3, "Mahmoud", "MH")
            return listOf(item1, item2, item3)
        }

        fun generateAuthorPostItems(): List<PostLocalModel> {
            val item1 =
                PostLocalModel(1, "20/10/2020", "MH", authorId = 4, body = "test", imageUrl = "dd")
            val item2 =
                PostLocalModel(2, "Mahmoud", "MH", authorId = 4, body = "test", imageUrl = "dd")
            val item3 =
                PostLocalModel(3, "Mahmoud", "MH", authorId = 4, body = "test", imageUrl = "dd")
            return listOf(item1, item2, item3)

        }
    }

}