package com.mati.jetpackcatalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    //para el estado del snakbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    //estado del drawer que por defecto lo dejo en false
    val drawerState = rememberDrawerState(DrawerValue.Closed)


    //esto esta arriba del sacaffol para tener una pantalla de aalado
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                MyModalDrawer() {
                    scope.launch {
                        //cierro el drawer
                        drawerState.close()
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                //le estoy mandando una funcion lambda a al snakbar y las snakbar necesita una corrutina
                MyTopAppBar(
                    onClickIcon = {
                        scope.launch {
                            //para que muestre el valor que me envia la funcion
                            snackbarHostState.showSnackbar("Has pulsado $it")
                        }
                    },
                    onClickDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = { MyBottomNavigation() },
            floatingActionButton = { MyFAB() },
            floatingActionButtonPosition = FabPosition.Center,
        ) {

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Primera toolbar") },
        //cambio de color del backgroud
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Green),
        //icono de nevagacion que al apretar se va a la izquierda
        navigationIcon = {
            IconButton(onClick = { onClickIcon("Atras") }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }

        },
        //botones al final de la toolbar que hacen acciones xD
        actions = {
            IconButton(onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "buscar")
            }

            IconButton(onClick = { onClickDrawer()}) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "done")
            }
        }
    )
}


@Composable
fun MyBottomNavigation() {
    //para tener el estado mutable para ver cual esta selecionado
    var index by remember { mutableStateOf(0) }
    NavigationBar(contentColor = MaterialTheme.colorScheme.primary) {

        NavigationBarItem(
            //si el estado es 0 enconces se pone como selecionado
            selected = index == 0,
            onClick = { index = 0 },
            label = { Text(text = "Home") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home"
                )
            })

        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            label = { Text(text = "Favorite") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite"
                )
            })

        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            label = { Text(text = "Search") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            })

    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(onClick = { }, containerColor = Color.Yellow, contentColor = Color.Blue) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyModalDrawer(function: () -> Job) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Priemera Opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "segunda Opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "tercera Opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "cuarta Opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "quinta Opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "septima Opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}