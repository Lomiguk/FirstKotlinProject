package com.lomiguk.flowerCollection.api.data.dto

import com.lomiguk.flowerCollection.entity.User

class UserDTO (val login: String){
    constructor(user: User) : this(user.login)
}