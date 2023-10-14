package com.lomiguk.usersflowers.repository.rowMapper

import com.lomiguk.usersflowers.data.entity.UserFlowers
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserFlowersRowMapper: RowMapper<UserFlowers> {
    override fun mapRow(rs: ResultSet, rowNum: Int): UserFlowers {
        return UserFlowers(rs.getLong("user_id"),
                           rs.getLong("flower_type_id"),
                           rs.getInt("count"))
    }
}