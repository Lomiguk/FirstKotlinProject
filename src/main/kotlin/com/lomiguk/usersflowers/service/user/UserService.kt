package com.lomiguk.usersflowers.service.user

import com.lomiguk.flowerCollection.util.getHash
import com.lomiguk.usersflowers.data.dto.UserDTO
import com.lomiguk.usersflowers.data.request.user.UserAddRequest
import com.lomiguk.usersflowers.data.request.user.UserDelRequest
import com.lomiguk.usersflowers.data.entity.User
import com.lomiguk.usersflowers.exception.user.CouldNotFoundCreatedUserException
import com.lomiguk.usersflowers.exception.user.DeletedUserUndeleted
import com.lomiguk.usersflowers.exception.user.UserAddPasswordNotMatchException
import com.lomiguk.usersflowers.repository.DAO.user.UserRepository
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

    fun delete(userDelRequest: UserDelRequest) {
        if (userDelRequest.password != userDelRequest.passwordRepeat)
            throw UserAddPasswordNotMatchException("Password don't match")
        userRepo.delete(userDelRequest.login)
        // --
        if (userRepo.getUser(userDelRequest.login) != null)
            throw DeletedUserUndeleted("Failed to delete a user")
    }

    fun getOneById(id: Long): UserDTO? {
        val user = userRepo.getUserById(id) ?: return null
        return UserDTO(user)
    }

    fun getOneByLogin(login: String): UserDTO? {
        val user = userRepo.getUserByLogin(login) ?: return null
        return UserDTO(user)
    }

    fun getPack(limit: Int, offset: Int): Collection<UserDTO> {
        return transformCollectionUserEntityToDTO(userRepo.getUsers(limit, offset))
    }

    private fun transformCollectionUserEntityToDTO(users: Collection<User>): Collection<UserDTO> {
        val usersList = arrayListOf<UserDTO>()
        for(user: User in users){
            usersList.add(UserDTO(user))
        }
        return usersList
    }
}