package com.sagrd.kevinduran_p2_ap2.util

import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto

data class GastosListState(
    val isLoading: Boolean = false,
    val gastos: List<GastoDto> = emptyList(),
    val error: String = ""
)