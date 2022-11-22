package com.shahry.feature.utils

import com.shahry.domain.entity.AuthorEntity

class TestDataGenerator {

    companion object {
        fun generateAuthorsItems(): List<AuthorEntity> {
            val item1 = AuthorEntity(1, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            val item2 = AuthorEntity(2, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            val item3 = AuthorEntity(3, "Mahmoud", "MH","mahmoudyahiashhatah@gmail.com","https//","-134.4","23.44")
            return listOf(item1, item2, item3)
        }
    }

}