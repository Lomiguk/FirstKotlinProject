package com.lomiguk.usersflowers.repository.DAO

import com.lomiguk.usersflowers.data.entity.UserFlowers
import com.lomiguk.usersflowers.repository.rowMapper.UserFlowersRowMapper
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

const val ADD_USER_FLOWERS_QUERY = "INSERT INTO user_flower_table VALUES (?, ?, ?);"
const val GET_USER_FLOWERS_QUERY = "SELECT * FROM user_flower_table WHERE user_id = ? AND flower_type_id = ?;"
const val DEL_USER_FLOWERS_QUERY = "DELETE FROM user_flower_table WHERE user_id = ? AND flower_type_id = ?;"
const val UPD_USER_FLOWERS_QUERY = "UPDATE user_flower_table SET count = ? WHERE user_id = ? AND flower_type_id = ?;"
@Repository
class UserFlowersRepository(val jdbcTemplate: JdbcTemplate) {
    fun add(userId: Long, flowerTypeId: Long, count: Int) {
        jdbcTemplate.update(ADD_USER_FLOWERS_QUERY, userId, flowerTypeId, count)
    }

    fun get(userId: Long, flowerTypeId: Long): UserFlowers? {
        return try {
            jdbcTemplate.queryForObject(GET_USER_FLOWERS_QUERY, UserFlowersRowMapper(), userId, flowerTypeId)
        }
        catch (e: EmptyResultDataAccessException){
            null
        }
    }

    fun del(userId: Long, flowerTypeId: Long) {
        jdbcTemplate.update(DEL_USER_FLOWERS_QUERY, userId, flowerTypeId)
    }

    fun updateCount(count: Int, userId: Long, flowerTypeId: Long) {
        jdbcTemplate.update(UPD_USER_FLOWERS_QUERY, count, userId, flowerTypeId)
    }
}