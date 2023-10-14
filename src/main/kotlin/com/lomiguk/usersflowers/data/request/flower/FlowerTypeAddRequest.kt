package com.lomiguk.usersflowers.data.request.flower

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

class FlowerTypeAddRequest(@NotNull @Size(min = 1, max = 60, message = "name size is 1-60")
                           val name: String = "")