package com.lomiguk.usersflowers.util

import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets

fun getHash(origin: String): Long{
    return Hashing.sha256().hashString(origin, StandardCharsets.UTF_8)
        .padToLong()
}