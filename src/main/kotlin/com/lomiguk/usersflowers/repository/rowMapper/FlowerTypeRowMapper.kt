package com.lomiguk.usersflowers.repository.rowMapper

import com.lomiguk.usersflowers.data.entity.FlowerType
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class FlowerTypeRowMapper : RowMapper<FlowerType> {
    override fun mapRow(rs: ResultSet, rowNum: Int): FlowerType {
        return FlowerType(rs.getLong("id"),
                          rs.getString("name"))
    }
}