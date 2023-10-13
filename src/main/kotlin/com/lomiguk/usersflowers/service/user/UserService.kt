package com.lomiguk.usersflowers.user

import com.lomiguk.usersflowers.data.dto.UserDTO
import com.lomiguk.usersflowers.data.request.UserAddRequest
import com.lomiguk.usersflowers.user.CouldNotFoundCreatedUserException
import com.lomiguk.usersflowers.user.UserAddPasswordNotMatchException
import com.lomiguk.usersflowers.user.UserRepository
import com.lomiguk.flowerCollection.util.getHash
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepo: UserRepository) {
    fun add(userAddRequest: UserAddRequest): UserDTO {
        if (userAddRequest.password != userAddRequest.passwordRepeat)
            throw UserAddPasswordNotMatchException("Password don't match")
        userRepo.add(userAddRequest.login, getHash(userAddRequest.password))
        // --
        val createdUser = userRepo.getUser(userAddRequest.login)
            ?: throw CouldNotFoundCreatedUserException("Couldn't found created profile!")
        return UserDTO(createdUser)
    }

    fun delete(id: String): UserDTO {
        TODO("Not yet implemented")
    }
}