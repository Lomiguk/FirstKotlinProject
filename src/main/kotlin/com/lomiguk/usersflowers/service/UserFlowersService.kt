package com.lomiguk.usersflowers.service

import com.lomiguk.usersflowers.data.dto.FlowerTypeDTO
import com.lomiguk.usersflowers.data.dto.UserDTO
import com.lomiguk.usersflowers.data.dto.UserFlowersDTO
import com.lomiguk.usersflowers.data.request.UserFlowersAddRequest
import com.lomiguk.usersflowers.data.request.UserFlowersDelRequest
import com.lomiguk.usersflowers.data.request.UserFlowersUpdCntRequest
import com.lomiguk.usersflowers.exception.CouldNotFoundCreatedUserFlowersException
import com.lomiguk.usersflowers.exception.CouldNotFoundPartOfStagingTableException
import com.lomiguk.usersflowers.exception.DeletedUserFlowersUndeleted
import com.lomiguk.usersflowers.repository.DAO.UserFlowersRepository
import com.lomiguk.usersflowers.repository.DAO.flowerType.FlowerTypeRepository
import com.lomiguk.usersflowers.repository.DAO.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserFlowersService(val userFlowersRepository: UserFlowersRepository,
                         val usersRepository: UserRepository,
                         val flowerTypeRepository: FlowerTypeRepository
) {
    @Transactional
    fun add(userFlowersRequest: UserFlowersAddRequest): UserFlowersDTO {
        val user = usersRepository.getUserById(userFlowersRequest.userId)
            ?: throw CouldNotFoundPartOfStagingTableException("User with id ${userFlowersRequest.userId} doesn't exist")
        val flowerType = flowerTypeRepository.get(userFlowersRequest.flowerTypeId)
            ?: throw CouldNotFoundPartOfStagingTableException("FlowerType with id ${userFlowersRequest.flowerTypeId} doesn't exist")

        userFlowersRepository.add(userFlowersRequest.userId,
                                  userFlowersRequest.flowerTypeId,
                                  userFlowersRequest.count)
        // --
        val userFlowers = userFlowersRepository.get(userFlowersRequest.userId,
                                                    userFlowersRequest.flowerTypeId)
            ?: throw CouldNotFoundCreatedUserFlowersException("Couldn't found created row of UserFlowers")

        return UserFlowersDTO(UserDTO(user),
                              FlowerTypeDTO(flowerType),
                              userFlowers.count)
    }

    @Transactional
    fun del(userFlowersDelRequest: UserFlowersDelRequest) {
        userFlowersRepository.del(userFlowersDelRequest.userId, userFlowersDelRequest.flowerTypeId)
        val userFlowers = userFlowersRepository.get(userFlowersDelRequest.userId, userFlowersDelRequest.flowerTypeId)
        if (userFlowers != null) throw DeletedUserFlowersUndeleted("UserFlower wasn't deleted")
    }

    fun updateCount(userFlowersUpdCntRequest: UserFlowersUpdCntRequest){
        userFlowersRepository.updateCount(userFlowersUpdCntRequest.count,
                                          userFlowersUpdCntRequest.userId,
                                          userFlowersUpdCntRequest.flowerTypeId)
    }

    fun get(userId: Long, flowerTypeId: Long): UserFlowersDTO?{
        val user = usersRepository.getUserById(userId)
            ?: throw CouldNotFoundPartOfStagingTableException("User with id $userId doesn't exist")
        val flowerType = flowerTypeRepository.get(flowerTypeId)
            ?: throw CouldNotFoundPartOfStagingTableException("FlowerType with id $flowerTypeId doesn't exist")
        val userFlowers = userFlowersRepository.get(userId, flowerTypeId)
            ?: return null
        return UserFlowersDTO(UserDTO(user),
               FlowerTypeDTO(flowerType),
               userFlowers.count)
    }
}