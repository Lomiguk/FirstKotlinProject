package com.lomiguk.usersflowers.controller.flowerType

import com.lomiguk.usersflowers.data.dto.FlowerTypeDTO
import com.lomiguk.usersflowers.data.request.flower.FlowerTypeAddRequest
import com.lomiguk.usersflowers.service.flower.FlowerTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FlowerTypeController(val flowerTypeService: FlowerTypeService) {
    // add
    @PostMapping("")
    fun add(@RequestBody flowerType: FlowerTypeAddRequest): ResponseEntity<FlowerTypeDTO>{
        return ResponseEntity(flowerTypeService.add(flowerType), HttpStatus.OK)
    }
    // delete
    // get by id
    // get by login
    // get with pagination
}