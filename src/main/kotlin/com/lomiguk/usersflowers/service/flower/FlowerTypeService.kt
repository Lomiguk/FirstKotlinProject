package com.lomiguk.usersflowers.service.flower

import com.lomiguk.usersflowers.data.dto.FlowerTypeDTO
import com.lomiguk.usersflowers.data.request.flower.FlowerTypeAddRequest
import com.lomiguk.usersflowers.exception.flower.CouldNotFoundCreatedFlowerTypeException
import com.lomiguk.usersflowers.repository.DAO.flowerType.FlowerTypeRepository
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

@Service
class FlowerTypeService(val flowerTypeRepository: FlowerTypeRepository) {
    fun add(flowerType: FlowerTypeAddRequest): FlowerTypeDTO {
        flowerTypeRepository.add(flowerType.name)
        val createdFlowerType = flowerTypeRepository.getByName(flowerType.name)
            ?: throw CouldNotFoundCreatedFlowerTypeException("Couldn't found created flower type!")
        return createdFlowerType
    }
}