package com.lomiguk.flowerCollection.controller.user

import com.lomiguk.flowerCollection.api.data.dto.UserDTO
import com.lomiguk.flowerCollection.api.data.request.UserAddRequest
import com.lomiguk.flowerCollection.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
open class UserController (val userService: UserService) {
    @PostMapping(value = ["/",""])
    open fun add(@RequestBody userAddRequest: UserAddRequest): ResponseEntity<UserDTO>{
        return ResponseEntity(userService.add(userAddRequest), HttpStatus.CREATED)
    }
    @DeleteMapping("/{id}")
    open fun delete(@PathVariable id: String) : ResponseEntity<UserDTO>{
        return ResponseEntity(userService.delete(id), HttpStatus.OK)
    }
}