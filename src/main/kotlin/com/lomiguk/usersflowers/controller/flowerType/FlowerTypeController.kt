package com.lomiguk.usersflowers.controller.flowerType

import com.lomiguk.usersflowers.data.dto.FlowerTypeDTO
import com.lomiguk.usersflowers.data.request.flower.FlowerTypeAddRequest
import com.lomiguk.usersflowers.exception.flower.CouldNotFoundCreatedFlowerTypeException
import com.lomiguk.usersflowers.exception.flower.DeletedFlowerTypeIsUndeleted
import com.lomiguk.usersflowers.service.flower.FlowerTypeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Boolean.TRUE

@RestController
@RequestMapping("/flower_type")
class FlowerTypeController(val flowerTypeService: FlowerTypeService) {
    val logger: Logger? = LoggerFactory.getLogger(FlowerTypeController::class.java.name)
    // add
    @PostMapping("")
    fun add(@RequestBody flowerTypeRequest: FlowerTypeAddRequest): ResponseEntity<FlowerTypeDTO>{
        return try {
            ResponseEntity(flowerTypeService.add(flowerTypeRequest), HttpStatus.CREATED)
        }catch (e: CouldNotFoundCreatedFlowerTypeException){
            logger?.error(e.message)
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
    // delete by name
    @DeleteMapping("/deleting/name/{name}")
    fun deleteByName(@PathVariable name: String) : ResponseEntity<Any>{
        try{
            flowerTypeService.del(name)
        }catch (e: DeletedFlowerTypeIsUndeleted){
            logger?.error(e.message)
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.ok().body(TRUE)
    }
    // delete by id
    @DeleteMapping("/deleting/id/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any>{
        try {
            flowerTypeService.del(id)
        }catch (e: DeletedFlowerTypeIsUndeleted){
            logger?.error(e.message)
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.ok().body(TRUE)
    }
    // get by id
    @GetMapping("/getting/id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<FlowerTypeDTO>{
        val flowerTypeDTO = flowerTypeService.get(id)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(flowerTypeDTO, HttpStatus.OK)
    }
    // get by name
    @GetMapping("/getting/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<FlowerTypeDTO>{
        val flowerTypeDTO = flowerTypeService.get(name)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(flowerTypeDTO, HttpStatus.OK)
    }
    // get with pagination
    @GetMapping("/")
    fun get(@RequestParam limit: Int, @RequestParam offset: Int):ResponseEntity<Collection<FlowerTypeDTO>>{
        return ResponseEntity(flowerTypeService.get(limit, offset), HttpStatus.OK)
    }
}