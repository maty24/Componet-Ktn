package com.mati.jetpackcatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

@Composable
fun BasicSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by remember {
            mutableStateOf(0f)
        }
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
        //paso el numero a string
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun AdvanceSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by remember { mutableStateOf(0f) }
        var completeValue by remember { mutableStateOf("") }
        Slider(
            value = sliderPosition, onValueChange = { sliderPosition = it },
            //le digo que el rango va ser del 0 al 10, la f es que es un flota que va a tener decimales
            valueRange = 0f..10f,
            //posiciones que se muestran en pantalla
            steps = 9,
            //esto es cuando se para el el scroll se obtiene el valor
            onValueChangeFinished = { completeValue = sliderPosition.toString() }
        )
        //paso el numero a string
        Text(text = sliderPosition.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRangeSlider() {
    Column() {
        //valor del rango esntre esos 2 numeros
        var currentRage by remember { mutableStateOf(0f..10f) }
        RangeSlider(
            value = currentRage,
            onValueChange = { currentRage = it },
            //valor del rango del slider
            valueRange = 0f..10f,
            steps = 9
        )
        //primer valor del rango
        Text(text = "Valor Inferior ${currentRage.start}")
        //ultimo valor del rango
        Text(text = "Valor Superior ${currentRage.endInclusive}")
    }
}