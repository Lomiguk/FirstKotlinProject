package com.lomiguk.usersflowers.data.request.user

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

class UserDelRequest(@NotNull @Size(min = 1, max = 50, message = "login size 1-30")
                     val login: String,
                     @NotNull @Size(min = 5, message = "min password size - 5")
                     val password: String,
                     @NotNull @Size(min = 5, message = "min password size - 5")
                     val passwordRepeat: String)