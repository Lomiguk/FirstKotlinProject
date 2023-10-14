package com.lomiguk.usersflowers.controller.user

import com.lomiguk.usersflowers.data.dto.UserDTO
import com.lomiguk.usersflowers.data.request.user.UserAddRequest
import com.lomiguk.usersflowers.data.request.user.UserDelRequest
import com.lomiguk.usersflowers.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Boolean.TRUE

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService) {
    @PostMapping(value = ["/",""])
    fun add(@RequestBody userAddRequest: UserAddRequest): ResponseEntity<UserDTO>{
        return ResponseEntity(userService.add(userAddRequest), HttpStatus.CREATED)
    }
    @DeleteMapping("/")
    fun delete(@RequestBody userDelRequest: UserDelRequest) : ResponseEntity<Any> {
        userService.delete(userDelRequest)
        return ResponseEntity.ok().body(TRUE)
    }
    @GetMapping("/getting/id/{id}")
    fun getOneById(@PathVariable("id") id: Long):ResponseEntity<UserDTO>{
        val userDTO = userService.getOneById(id)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(userDTO, HttpStatus.OK)
    }
    @GetMapping("/getting/login/{login}")
    fun getOneByLogin(@PathVariable("login") login: String):ResponseEntity<UserDTO>{
        val userDTO = userService.getOneByLogin(login)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(userDTO, HttpStatus.OK)
    }
    @GetMapping("/")
    fun getPack(@RequestParam limit: Int, @RequestParam offset: Int):ResponseEntity<Collection<UserDTO>>{
        return ResponseEntity(userService.getPack(limit, offset), HttpStatus.OK)
    }
}