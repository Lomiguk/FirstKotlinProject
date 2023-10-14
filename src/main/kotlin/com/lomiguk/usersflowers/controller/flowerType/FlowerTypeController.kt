package com.lomiguk.usersflowers.controller.flowerType

import com.lomiguk.usersflowers.data.dto.FlowerTypeDTO
import com.lomiguk.usersflowers.data.request.flower.*
import com.lomiguk.usersflowers.service.flower.FlowerTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Boolean.TRUE

@RestController
@RequestMapping("/flower_type")
class FlowerTypeController(val flowerTypeService: FlowerTypeService) {
    // add
    @PostMapping("")
    fun add(@RequestBody flowerTypeRequest: FlowerTypeAddRequest): ResponseEntity<FlowerTypeDTO>{
        return ResponseEntity(flowerTypeService.add(flowerTypeRequest), HttpStatus.CREATED)
    }
    // delete by name
    @DeleteMapping("/deleting/name/{name}")
    fun deleteByName(@PathVariable name: String) : ResponseEntity<Any>{
        flowerTypeService.del(name)
        return ResponseEntity.ok().body(TRUE)
    }
    // delete by id
    @DeleteMapping("/deleting/id/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any>{
        flowerTypeService.del(id)
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