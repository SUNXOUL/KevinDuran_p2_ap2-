package com.sagrd.kevinduran_p2_ap2.ui.Gastos

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagrd.kevinduran_p2_ap2.MainActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun GastosScreen(
    GastosviewModel: GastosViewModel = hiltViewModel(),
    SuplidoresviewModel: SuplidoresViewModel = hiltViewModel(),
    context: Context
){
    val Gastos = GastosviewModel.stateGastos.value.gastos
    val suplidores = SuplidoresviewModel.stateSuplidores.value.suplidores
    var expanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier= Modifier
            .weight(1.3f)
            .fillMaxWidth()) {
            Column ( modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)){
                OutlinedTextField(
                    value = GastosviewModel.selectedText,
                    onValueChange = {  },
                    isError = GastosviewModel.idSuplidorError,
                    readOnly = true,
                    maxLines= 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = {Text("Selecciona la Suplidor")},
                    trailingIcon = {
                        if (expanded){
                            Icon(imageVector = Icons.Filled.ArrowDropUp,"contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        }
                        else{
                            Icon(imageVector = Icons.Filled.ArrowDropDown,"contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        }

                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                ) {
                    suplidores.forEach { suplidor ->
                        DropdownMenuItem(text = { Text(text = suplidor.nombres)}, onClick = {
                            GastosviewModel.onIdSuplidorChange("${suplidor.idSuplidor}")
                            GastosviewModel.selectedText=suplidor.nombres
                            expanded = !expanded
                        })
                    }
                }
            }
            showDatePicker(context = context, modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f))
            OutlinedTextField(
                value = GastosviewModel.ncf,
                onValueChange = { GastosviewModel.onNcfChange(it)  },
                isError = GastosviewModel.ncfError,
                maxLines= 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f),
                label = { Text("Ingrese NCF") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = GastosviewModel.concepto,
                onValueChange = { GastosviewModel.onConceptoChange(it)  },
                isError = GastosviewModel.conceptoError,
                maxLines= 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f),
                label = { Text("Ingrese Concepto") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next)
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f)
            ){
                OutlinedTextField(
                    value = GastosviewModel.itbis,
                    onValueChange = { GastosviewModel.onItbisChange(it)  },
                    isError = GastosviewModel.itbisError,
                    maxLines= 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    label = { Text("Ingrese ITBIS") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.weight(0.05f))
                OutlinedTextField(
                    value = GastosviewModel.descuento,
                    onValueChange = { GastosviewModel.onDescuentoChange(it)  },
                    isError = GastosviewModel.descuentoError,
                    maxLines= 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    label = { Text("Ingrese Descuento") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next)
                )
            }
            OutlinedTextField(
                value = GastosviewModel.monto,
                onValueChange = { GastosviewModel.onMontoChange(it)  },
                isError = GastosviewModel.montoError,
                maxLines= 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f),
                label = { Text("Ingrese Monto") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next)
            )
            Row(modifier= Modifier
                .fillMaxWidth()
                .weight(1f), horizontalArrangement = Arrangement.Center) {
                Button(modifier=Modifier.fillMaxWidth()
                    ,onClick = { GastosviewModel.save() }) {
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Filled.Save, contentDescription = "")
                        Text(text = "Guardar")
                    }

                }
            }

        }
        Column(modifier= Modifier.weight(1f)) {
            Divider()
            Row (modifier=Modifier.padding(4.dp)){
                Text(text = "Gastos Registrados", fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Filled.AccessTime, contentDescription = "Timer")
            }
            if(SuplidoresviewModel.stateSuplidores.value.isLoading){
                Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "CARGANDO...")
                }
            }
            else
            {
                LazyColumn {

                    items(Gastos){

                        ElevatedCard(modifier = Modifier.padding(6.dp)) {
                            Column(modifier = Modifier.padding(4.dp)) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier=Modifier.weight(1f)) {
                                        Text(text = "ID : ${it.idGasto}",maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,)
                                    }
                                    var fechaSplit = it.fecha?.split("T")?.get(0)
                                    Column(modifier=Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                                        fechaSplit?.let {
                                            Text(text = " ${fechaSplit.split("-")[2]}/${fechaSplit.split("-")[1]}/${fechaSplit.split("-")[0]}")
                                        }
                                    }
                                }
                                Row (modifier = Modifier.fillMaxWidth()){
                                    suplidores.singleOrNull { s -> s.idSuplidor == it.idSuplidor }
                                        ?.let { it ->
                                            Text(
                                                text = it.nombres,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                style = MaterialTheme.typography.headlineLarge,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                }
                                it.concepto?.let { it1 -> Text(text = it1,maxLines=2 , overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium) }
                                Spacer(modifier = Modifier.padding(top = 4.dp))
                                Row (modifier = Modifier.fillMaxWidth()){
                                    Column(modifier=Modifier.weight(1f)) {
                                        Text(text = "NCF   : ${it.ncf}"
                                            ,maxLines = 1,
                                            overflow = TextOverflow.Ellipsis)
                                        Text(text = "ITBIS : ${ NumberFormat.getNumberInstance(Locale("es", "DO")).format(it.itbis) }",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,)
                                    }
                                    Row (horizontalArrangement = Arrangement.End, modifier=Modifier.weight(1f)){
                                        Text(text = "RD$${NumberFormat.getNumberInstance(Locale("es", "DO")).format(it.monto)}", style = MaterialTheme.typography.titleLarge,
                                            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold,maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,)
                                    }

                                }
                                Divider()
                                Row (modifier= Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                                    FilledTonalButton(onClick = { if(it.idGasto!=null) {
                                        GastosviewModel.find(it.idGasto!!)
                                        GastosviewModel.selectedText = suplidores.singleOrNull { s-> s.idSuplidor ==it.idSuplidor  }?.nombres
                                            ?: ""
                                    } }) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
                                            Text(text = "Editar", style = MaterialTheme.typography.bodyLarge)
                                        }

                                    }
                                    Spacer(modifier = Modifier.padding(end = 20.dp))
                                    OutlinedButton(onClick = { it.idGasto?.let {
                                        GastosviewModel.delete(it)
                                    } }) {
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Text(text = "X", color = Color.Red, fontWeight = FontWeight.Bold,style = MaterialTheme.typography.bodyLarge)
                                            Text(text = " Eliminar")
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }



    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun showDatePicker(
    context: Context,
    modifier: Modifier,
    gastosViewModel: GastosViewModel = hiltViewModel(),
) {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()


    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            gastosViewModel.onFechaChange( "$dayOfMonth/$month/$year")
        }, year, month, day
    )
    OutlinedTextField(
        value = gastosViewModel.fecha,
        onValueChange = { },
        readOnly = true,
        modifier = modifier,
        isError = gastosViewModel.fechaError,
        leadingIcon = { IconButton(onClick = {
            datePickerDialog.show()
        }) {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription ="date" )
        }
        },
        label = { Text("Ingrese Fecha") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next)
    )

}