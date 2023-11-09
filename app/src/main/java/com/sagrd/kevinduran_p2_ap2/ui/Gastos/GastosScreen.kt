package com.sagrd.kevinduran_p2_ap2.ui.Gastos

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun GastosScreen(
    viewModel: GastosViewModel = hiltViewModel(),
){
    val Gastos = viewModel.stateGastos.value.gastos
    val suplidores = viewModel.stateSuplidores.value.suplidores

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier= Modifier.weight(1f)) {


        }
        LazyColumn(modifier= Modifier.weight(1f)) {
            items(Gastos){
                Card() {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier=Modifier.weight(1f)) {
                            Text(text = "ID :${it.idGasto}")
                        }
                        Column(modifier=Modifier.weight(1f)) {
                            Text(text = " ${it.fecha}")
                        }
                    }
                }
            }
        }

    }
}