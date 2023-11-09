package com.sagrd.kevinduran_p2_ap2.util

import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto
import com.sagrd.kevinduran_p2_ap2.data.remote.dto.SuplidorDto

data class SuplidoresListState(
    val isLoading: Boolean = false,
    val suplidores: List<SuplidorDto> = emptyList(),
    val error: String = ""
)