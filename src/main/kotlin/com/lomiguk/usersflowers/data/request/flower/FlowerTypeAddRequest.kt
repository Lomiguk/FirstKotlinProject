package com.lomiguk.usersflowers.data.request.flower

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

class FlowerTypeAddRequest(@NotNull @Size(min = 1, max = 60, message = "Size of flower type's name must be between 1 and 60 characters")
                           val name: String)