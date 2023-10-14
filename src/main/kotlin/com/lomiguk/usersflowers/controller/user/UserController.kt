package com.lomiguk.usersflowers.controller.user

import com.lomiguk.usersflowers.data.dto.UserDTO
import com.lomiguk.usersflowers.data.dto.UserFlowersDTO
import com.lomiguk.usersflowers.data.request.UserFlowersAddRequest
import com.lomiguk.usersflowers.data.request.UserFlowersDelRequest
import com.lomiguk.usersflowers.data.request.UserFlowersUpdCntRequest
import com.lomiguk.usersflowers.data.request.user.UserAddRequest
import com.lomiguk.usersflowers.data.request.user.UserDelRequest
import com.lomiguk.usersflowers.exception.CouldNotFoundCreatedUserFlowersException
import com.lomiguk.usersflowers.exception.CouldNotFoundPartOfStagingTableException
import com.lomiguk.usersflowers.service.UserFlowersService
import com.lomiguk.usersflowers.service.user.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Boolean.TRUE

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService,
                      val userFlowersService: UserFlowersService) {
    val logger: Logger? = LoggerFactory.getLogger(UserController::class.java.name)
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

    // ------------------- UsersFlowers
    @PostMapping("/flowers")
    fun addUserFlowers(@RequestBody userFlowersAddRequest: UserFlowersAddRequest): ResponseEntity<UserFlowersDTO>{
        val userFlowers = try {
            userFlowersService.add(userFlowersAddRequest)
        }catch (e: CouldNotFoundPartOfStagingTableException){
            logger?.error(e.message)
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        catch (e: CouldNotFoundCreatedUserFlowersException){
            logger?.warn(e.message)
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(userFlowers, HttpStatus.OK)
    }
    @DeleteMapping("/flowers")
    fun deleteUserFlowers(@RequestBody userFlowersDelRequest: UserFlowersDelRequest): ResponseEntity<Any>{
        userFlowersService.del(userFlowersDelRequest)
        return ResponseEntity.ok().body(TRUE)
    }
    @PostMapping("/flowers/count")
    fun updateCountUserFlowers(@RequestBody userFlowersUpdCntRequest: UserFlowersUpdCntRequest): ResponseEntity<Any>{
        userFlowersService.updateCount(userFlowersUpdCntRequest)
        return ResponseEntity.ok().body(TRUE)
    }
    @GetMapping("/flowers/{userId}/{flowerTypeId}")
    fun getUserFlowers(@PathVariable("userId") userId:Long,
                       @PathVariable("flowerTypeId") flowerTypeId:Long): ResponseEntity<UserFlowersDTO>{
        val userFlowers = try {
            userFlowersService.get(userId, flowerTypeId)
                ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        }catch (e: CouldNotFoundPartOfStagingTableException){
            logger?.error(e.message)
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        catch (e: CouldNotFoundCreatedUserFlowersException){
            logger?.warn(e.message)
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(userFlowers, HttpStatus.OK)
    }
}