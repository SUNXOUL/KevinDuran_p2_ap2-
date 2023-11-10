package com.sagrd.kevinduran_p2_ap2.ui.Gastos

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.clientesremoto.util.Resource
import com.sagrd.kevinduran_p2_ap2.data.repository.GastoRepository
import com.sagrd.kevinduran_p2_ap2.data.repository.SuplidorRepository
import com.sagrd.kevinduran_p2_ap2.util.GastosListState
import com.sagrd.kevinduran_p2_ap2.util.SuplidoresListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class SuplidoresViewModel @Inject constructor(
    private val  suplidorRepository: SuplidorRepository

) : ViewModel() {
    private var _stateSuplidores = mutableStateOf(SuplidoresListState())
    val stateSuplidores: State<SuplidoresListState> = _stateSuplidores
    init {
        load()
    }
    fun load(){

        suplidorRepository.getSuplidores().onEach{ result ->
            when (result) {
                is Resource.Loading -> {
                    _stateSuplidores.value = SuplidoresListState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateSuplidores.value = SuplidoresListState(suplidores = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateSuplidores.value = SuplidoresListState(error = result.message ?: "Error desconocido")
                }

                else -> {}
            }
        }.launchIn(viewModelScope)


    }


}