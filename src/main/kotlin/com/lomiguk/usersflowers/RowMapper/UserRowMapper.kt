package com.lomiguk.usersflowers.RowMapper

import com.lomiguk.usersflowers.entity.User
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserRowMapper : RowMapper<User>{
    override fun mapRow(rs: ResultSet, rowNum: Int): User {
        return User(rs.getLong("id"),
            rs.getString("login"),
            rs.getLong("password"))
    }

}