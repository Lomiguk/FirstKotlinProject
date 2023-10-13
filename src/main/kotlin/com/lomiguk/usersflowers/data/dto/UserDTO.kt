package com.lomiguk.usersflowers.data.dto

import com.lomiguk.usersflowers.entity.User

class UserDTO (val login: String){
    constructor(user: User) : this(user.login)
}