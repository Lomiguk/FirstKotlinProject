package com.lomiguk.usersflowers.repository.DAO.user

import com.lomiguk.usersflowers.entity.User
import com.lomiguk.usersflowers.RowMapper.UserRowMapper
import com.lomiguk.usersflowers.data.dto.UserDTO
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository

const val USER_ADD_QUERY = "INSERT INTO user_table (login, password) VALUES (?, ?);"
const val GET_USER_QUERY = "SELECT * FROM user_table WHERE login = ?;"
const val GET_USER_ID_QUERY = "SELECT * FROM user_table WHERE id = ?;"
const val GET_PAGINATION_USER_QUERY = "SELECT * FROM user_table ORDER BY id LIMIT ? OFFSET ?;"
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

    fun getUserById(id: Long): User? {
        return jdbcTemplate.queryForObject(GET_USER_ID_QUERY, UserRowMapper(), id)
    }

    fun getUserByLogin(login: String): User? {
        return jdbcTemplate.queryForObject(GET_USER_QUERY, UserRowMapper(), login)
    }

    fun getUsers(limit: Int, offset: Int): Collection<User> {
        return jdbcTemplate.query(GET_PAGINATION_USER_QUERY, UserRowMapper(), limit, offset)
    }
}