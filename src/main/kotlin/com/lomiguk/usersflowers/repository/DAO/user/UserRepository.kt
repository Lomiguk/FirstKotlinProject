package com.lomiguk.usersflowers.user

import com.lomiguk.usersflowers.entity.User
import com.lomiguk.usersflowers.RowMapper.UserRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import kotlin.math.log

const val USER_ADD_QUERY = "INSERT INTO user_table (login, password) VALUES (?, ?);"
const val GET_USER_QUERY = "SELECT * FROM user_table WHERE login = ?;"
const val DEL_USER_QUERY = "DELETE FROM user_table WHERE login = ?;"
@Repository
class UserRepository(private val jdbcTemplate: JdbcTemplate) {
    fun add(login: String, hash: Long) {
        jdbcTemplate.update(USER_ADD_QUERY, login, hash)
    }

    fun getUser(login: String): User? {
        return jdbcTemplate.queryForObject(GET_USER_QUERY, UserRowMapper(), login)
    }

    fun delete(login: String){
        jdbcTemplate.update(DEL_USER_QUERY, login)
    }
}