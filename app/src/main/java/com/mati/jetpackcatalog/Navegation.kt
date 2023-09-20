package com.mati.jetpackcatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.mati.jetpackcatalog.model.Routes
import com.mati.jetpackcatalog.model.Routes.*


@Composable
fun Screen1(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {

        Text(
            text = "Pantalla 1",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navigationController.navigate(Pantalla2.route) })
    }
}

@Composable
fun Screen2(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

        Text(
            text = "Pantalla 2",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navigationController.navigate(Pantalla3.route) })
    }
}

@Composable
fun Screen3(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {

        Text(
            text = "Pantalla 3",
            modifier = Modifier
                .align(Alignment.Center)
                //le envio los parametros
                .clickable { navigationController.navigate(Routes.Pantalla4.createRoute(26)) })
    }
}

@Composable
fun Screen4(navigationController: NavHostController, age: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {

        Text(
            text = "Tengo  $age anos",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navigationController.navigate(
                    //le envio la ruta asi sin / despies, porque si pongo "" me lo toma como argumento vacio
                    "screen5") })
    }
}


@Composable
fun Screen5(navigationController: NavHostController, name: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {

        Text(text = "me llamo  $name ", modifier = Modifier.align(Alignment.Center))
    }
}