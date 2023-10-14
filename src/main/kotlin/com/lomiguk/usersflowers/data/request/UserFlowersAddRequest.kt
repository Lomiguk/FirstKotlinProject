package com.lomiguk.usersflowers.data.request

import org.jetbrains.annotations.NotNull

class UserFlowersAddRequest(@NotNull val userId: Long,
                            @NotNull val flowerTypeId: Long,
                            @NotNull val count: Int)