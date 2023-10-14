package com.lomiguk.usersflowers.service.flower

import com.lomiguk.usersflowers.data.dto.FlowerTypeDTO
import com.lomiguk.usersflowers.data.entity.FlowerType
import com.lomiguk.usersflowers.data.request.flower.FlowerTypeAddRequest
import com.lomiguk.usersflowers.exception.flower.CouldNotFoundCreatedFlowerTypeException
import com.lomiguk.usersflowers.exception.flower.DeletedFlowerTypeIsUndeleted
import com.lomiguk.usersflowers.repository.DAO.flowerType.FlowerTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FlowerTypeService(val flowerTypeRepository: FlowerTypeRepository) {
    @Transactional
    fun add(flowerType: FlowerTypeAddRequest): FlowerTypeDTO {
        flowerTypeRepository.add(flowerType.name)
       val createdFlower = flowerTypeRepository.get(flowerType.name)
            ?: throw CouldNotFoundCreatedFlowerTypeException("Couldn't found created flower type!")
        return FlowerTypeDTO(createdFlower)
    }
    @Transactional
    fun del(name: String) {
        flowerTypeRepository.del(name)
        val deletedFlowerType = flowerTypeRepository.get(name)
        if (deletedFlowerType != null)
            throw DeletedFlowerTypeIsUndeleted("Was found deleted flower type!")
    }
    @Transactional
    fun del(id: Long) {
        flowerTypeRepository.del(id)
        val deletedFlowerType = flowerTypeRepository.get(id)
        if (deletedFlowerType != null)
            throw DeletedFlowerTypeIsUndeleted("Was found deleted flower type!")
    }

    fun get(id: Long) : FlowerTypeDTO? {
        val gotFlowerType = flowerTypeRepository.get(id)
            ?: return null
        return FlowerTypeDTO(gotFlowerType)
    }
    fun get(name: String) : FlowerTypeDTO? {
        val gotFlowerType = flowerTypeRepository.get(name)
            ?: return null
        return FlowerTypeDTO(gotFlowerType)
    }

    fun get(limit: Int, offset: Int): Collection<FlowerTypeDTO> {
        return transformCollectionFlowerTypeEntityToDTO(flowerTypeRepository.get(limit, offset))
    }

    private fun transformCollectionFlowerTypeEntityToDTO(flowerTypes: Collection<FlowerType>): Collection<FlowerTypeDTO> {
        val flowerTypeList = arrayListOf<FlowerTypeDTO>()
        for(flowerType in flowerTypes){
            flowerTypeList.add(FlowerTypeDTO(flowerType))
        }
        return flowerTypeList
    }
}