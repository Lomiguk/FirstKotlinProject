package com.lomiguk.usersflowers.repository.DAO.flowerType

import com.lomiguk.usersflowers.data.entity.FlowerType
import com.lomiguk.usersflowers.repository.rowMapper.FlowerTypeRowMapper
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository


const val ADD_FLOWER_TYPE_QUERY = "INSERT INTO flower_type_table (name) VALUES (?);"
const val GET_FLOWER_TYPE_BY_NAME_QUERY = "SELECT * FROM flower_type_table WHERE name = ?;"
const val GET_FLOWER_TYPE_BY_ID_QUERY = "SELECT * FROM flower_type_table WHERE id = ?;"
const val DEL_FLOWER_TYPE_BY_NAME_QUERY = "DELETE FROM flower_type_table WHERE name = ?;"
const val DEL_FLOWER_TYPE_BY_ID_QUERY = "DELETE FROM flower_type_table WHERE id = ?;"
const val GET_FLOWER_TYPE_WITH_PAGINATION_QUERY = "SELECT * FROM flower_type_table ORDER BY id LIMIT ? OFFSET ?"
@Repository
class FlowerTypeRepository(val jdbcTemplate: JdbcTemplate) {
    fun add(name: String){
        jdbcTemplate.update(ADD_FLOWER_TYPE_QUERY, name)
    }
    fun get(name: String): FlowerType? {
        return try{
            jdbcTemplate.queryForObject(GET_FLOWER_TYPE_BY_NAME_QUERY, FlowerTypeRowMapper(), name)
        }
        catch (e: EmptyResultDataAccessException){
            null
        }
    }

    fun get(id: Long): FlowerType? {
        return try {
            jdbcTemplate.queryForObject(GET_FLOWER_TYPE_BY_ID_QUERY, FlowerTypeRowMapper(), id)
        }
        catch (e: EmptyResultDataAccessException){
            null
        }
    }

    fun del(name: String) {
        jdbcTemplate.update(DEL_FLOWER_TYPE_BY_NAME_QUERY, name)
    }

    fun del(id: Long) {
        jdbcTemplate.update(DEL_FLOWER_TYPE_BY_ID_QUERY, id)
    }

    fun get(limit: Int, offset: Int): Collection<FlowerType> {
        return jdbcTemplate.query(GET_FLOWER_TYPE_WITH_PAGINATION_QUERY,FlowerTypeRowMapper(), limit, offset)
    }
}