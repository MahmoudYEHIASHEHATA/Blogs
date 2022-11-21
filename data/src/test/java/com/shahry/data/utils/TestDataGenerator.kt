package com.shahry.data.utils

import com.shahry.data.model.AuthorDTO
import com.shahry.data.model.PostDTO
import com.shahry.domain.entity.AuthorEntity
import com.shahry.domain.entity.PostEntity

class TestDataGenerator {


    companion object {

            fun generateAuthorsItems(): List<AuthorDTO> {
            val item1 = AuthorDTO(1, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            val item2 = AuthorDTO(2, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            val item3 = AuthorDTO(3, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            return listOf(item1, item2, item3)
        }
        fun generateAuthorPostItems(): List<PostDTO> {
            val item1 =
                PostDTO(1, "20/10/2020", "MH", authorId = 4, body = "test", imageUrl = "dd")
            val item2 =
                PostDTO(2, "Mahmoud", "MH", authorId = 4, body = "test", imageUrl = "dd")
            val item3 =
                PostDTO(3, "Mahmoud", "MH", authorId = 4, body = "test", imageUrl = "dd")
            return listOf(item1, item2, item3)

        }
    }

}