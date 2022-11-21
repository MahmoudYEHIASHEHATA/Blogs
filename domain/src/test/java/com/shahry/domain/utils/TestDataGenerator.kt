package com.shahry.domain.utils

import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.entity.PostEntity

class TestDataGenerator {


    companion object {

            fun generateAuthorsItems(): List<AuthorEntity> {
            val item1 = AuthorEntity(1, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            val item2 = AuthorEntity(2, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            val item3 = AuthorEntity(3, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            return listOf(item1, item2, item3)
        }
        fun generateAuthorPostItems(): List<PostEntity> {
            val item1 =
                PostEntity(1, "20/10/2020", "MH", authorId = 4, body = "test", imageUrl = "dd")
            val item2 =
                PostEntity(2, "Mahmoud", "MH", authorId = 4, body = "test", imageUrl = "dd")
            val item3 =
                PostEntity(3, "Mahmoud", "MH", authorId = 4, body = "test", imageUrl = "dd")
            return listOf(item1, item2, item3)

        }
    }

}