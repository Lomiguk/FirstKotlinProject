package com.lomiguk.usersflowers.repository.DAO.flowerType

import com.lomiguk.usersflowers.data.entity.FlowerType
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class FlowerTypeRepository(val jdbcTemplate: JdbcTemplate) {
    fun add(name: String){}
    fun getByName(name: String): FlowerType {
        
    }
}