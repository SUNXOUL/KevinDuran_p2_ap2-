package com.sagrd.kevinduran_p2_ap2.ui.Gastos

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrd.clientesremoto.util.Resource
import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto
import com.sagrd.kevinduran_p2_ap2.data.repository.GastoRepository
import com.sagrd.kevinduran_p2_ap2.data.repository.SuplidorRepository
import com.sagrd.kevinduran_p2_ap2.util.GastosListState
import com.sagrd.kevinduran_p2_ap2.util.SuplidoresListState
import com.squareup.moshi.Json
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class GastosViewModel @Inject constructor(
    private val gastoRepository: GastoRepository,
    private val  suplidorRepository: SuplidorRepository
) : ViewModel() {
    var gastoActual by mutableStateOf(GastoDto(0, "",0, "", "", 0,0,0))

    var fecha by mutableStateOf("")
    var idSuplidor by mutableStateOf("")
    var ncf by mutableStateOf("")
    var concepto by mutableStateOf("")
    var descuento by mutableStateOf("")
    var itbis by mutableStateOf("")
    var monto by mutableStateOf("")


    var fechaError by mutableStateOf(true)
    var idSuplidorError by  mutableStateOf(true)
    var ncfError  by  mutableStateOf(true)
    var conceptoError  by  mutableStateOf(true)
    var descuentoError  by  mutableStateOf(true)
    var itbisError  by  mutableStateOf(true)
    var montoError  by  mutableStateOf(true)

    fun String.isValid(): Boolean{
        try {
            this.toFloat()
            return return !this.isNullOrBlank()
        }
        catch (e: Exception){
            return false
        }
    }
    fun onFechaChange(value : String){
        fecha = value
        fechaError = value.isNullOrBlank()
    }
    fun onIdSuplidorChange(value : String){
        idSuplidor = value
        idSuplidorError = value.isValid()
    }
    fun onNcfChange(value : String){
        ncf = value
        ncfError = value.isNullOrBlank()
    }
    fun onConceptoChange(value : String){
        concepto = value
        conceptoError = value.isNullOrBlank()
    }
    fun onDescuentoChange(value : String){
        descuento = value
        descuentoError = value.isValid()
    }
    fun onItbisChange(value : String){
        itbis = value
        itbisError = value.isValid()
    }
    fun onMontoChange(value : String){
        monto = value
        montoError = value.isValid()
    }


    private var _stateGastos = mutableStateOf(GastosListState())
    val stateGastos: State<GastosListState> = _stateGastos

    private var _stateSuplidores = mutableStateOf(SuplidoresListState())
    val stateSuplidores: State<SuplidoresListState> = _stateSuplidores
    init {
        load()
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun load(){
        gastoRepository.getGastos().onEach{ result ->
            when (result) {
                is Resource.Loading -> {
                    _stateGastos.value = GastosListState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateGastos.value = GastosListState(gastos = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateGastos.value = GastosListState(error = result.message ?: "Error desconocido")
                }

                else -> {}
            }
        }.launchIn(viewModelScope)

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

    fun clear(){
        onFechaChange("")
        onIdSuplidorChange("")
        onNcfChange("")
        onConceptoChange("")
        onDescuentoChange("")
        onItbisChange("")
        onMontoChange("")
    }
    fun invalidSave() :Boolean{
        return fechaError and idSuplidorError and ncfError and conceptoError and conceptoError and descuentoError and itbisError and montoError
    }

    fun save(){
        viewModelScope.launch {
            if (!invalidSave()){
                gastoRepository.postGastos(GastoDto(idGasto = 0, fecha=fecha, idSuplidor=idSuplidor.toInt(),ncf = ncf,
                    concepto=concepto, descuento=descuento.toInt(), itbis= itbis.toInt(), monto=monto.toInt() ))
            }
        }
        clear()
        load()
    }
    fun delete(gastoId : Int){
        viewModelScope.launch {
            gastoRepository.deleteGastos(gastoId)
        }
        load()
    }

    fun upload (){
        viewModelScope.launch {
            gastoActual.idGasto?.let {
                gastoRepository.putGastos(GastoDto(idGasto = 0, fecha=fecha, idSuplidor=idSuplidor.toInt(),ncf = ncf,
                    concepto=concepto, descuento=descuento.toInt(), itbis= itbis.toInt(), monto=monto.toInt() ),
                    it
                )
            }
        }
        load()
    }

    fun find(gastoId : Int){
        viewModelScope.launch {
            var gastoBuscado = gastoRepository.getGastoById(gastoId)
           gastoBuscado?.let {
               gastoActual=gastoBuscado
           }
        }
        load()
    }
}