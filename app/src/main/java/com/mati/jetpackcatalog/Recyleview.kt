package com.mati.jetpackcatalog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.jetpackcatalog.model.Superhero
import kotlinx.coroutines.launch


////ESTO ES PARA MOSTRAR VARIOS COMOSABLES , MAS QUE NADA CUANDO TENEOS UN ARRAY DE DEATOS Y QUEREMOS PINTAS
@Composable
fun SimpleRecyclerView() {
    val myList = listOf("mati", "dani", "luca", "santi")
    LazyColumn {
        item { Text(text = "Primer item") }
        items(7) {
            Text(text = "Este es el item $it")
        }
        items(myList) {
            Text(text = "hola me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}


@Composable
fun SuperHeroView() {
    //sacar el contexto
    val contex = LocalContext.current
    //le digo que por cada carta se haga un espacio de 8 dp
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        //le estoy poniendo otro nombre que hace que retorne los herores
        items(getSuperheroes()) { superhero ->
            ItemHero(superHero = superhero) {
                //preguntar a chatgpt
                Toast.makeText(
                    contex,
                    it.superheroName,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {

    val contex = LocalContext.current
    //LE PONGO grupby para que lo agrupa por publisher
    val superHero: Map<String, List<Superhero>> = getSuperheroes().groupBy { it.publisher }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        //el primero es el nombre del publiser y el segundo es la lista de datos porque estoy filtradno por publisher
        superHero.forEach { (publisher, mySuperHero) ->
            stickyHeader {
                Text(text = publisher)
            }
            items(mySuperHero) { superhero ->
                ItemHero(superHero = superhero) {
                    Toast.makeText(
                        contex,
                        it.superheroName,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }
}


@Composable
fun SuperHeroViewWithSpecialControlsView() {
    //sacar el contexto
    val contex = LocalContext.current
    //esto controla el stado del recycer view
    val rvState = rememberLazyListState()
    val corrutinesScope = rememberCoroutineScope()

    Column {
        //le digo que por cada carta se haga un espacio de 8 dp
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            //weight es como el peso
            modifier = Modifier.weight(1f),
        ) {
            //le estoy poniendo otro nombre que hace que retorne los herores
            items(getSuperheroes()) { superhero ->
                ItemHero(superHero = superhero) {
                    //preguntar a chatgpt
                    Toast.makeText(
                        contex,
                        it.superheroName,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        val showButton by remember {
            //cuando el estado observable cambiar es recomendar usar esto y lo uso cuadno quiero hacer calculos cuando observo varios estado
            derivedStateOf {
                //
                rvState.firstVisibleItemIndex > 0
            }
        }
//nos da un valor desde el sitio que estemos y el primero es 0 y el ultimo 100
        rvState.firstVisibleItemScrollOffset

        //si llego al final me muestra el btn
        if (showButton) {
            Button(
                onClick = {
                    //tengo que usar corrutians para que funcione
                    corrutinesScope.launch {
                        //que vaya a la primera posicion
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Buton cool ")
            }
        }

    }


}

@Composable
fun SuperHeroGridView() {
    //sacar el contexto
    val contex = LocalContext.current
    //columns son las columnas que van, puede ser fijo y pongo adaptative para que se adapte  y que minimo tenga 100dp
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        content = {
            items(getSuperheroes()) { superhero ->
                ItemHero(superHero = superhero) {
                    //preguntar a chatgpt
                    Toast.makeText(
                        contex,
                        it.superheroName,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        },
        //un padding al lazy
        contentPadding = PaddingValues(horizontal = 16.dp),
    )

}

@Composable
fun ItemHero(superHero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .width(200.dp)
            //para que pueda ser clicleable la card
            .clickable { onItemSelected(superHero) }) {
        Column {
            Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "supperhero avatar",
                //que ocupe todo el ancho
                modifier = Modifier.fillMaxWidth(),
                //va a tomar todo el ancho
                contentScale = ContentScale.Crop
            )
            Text(
                text = superHero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier.align(Alignment.End),
                fontSize = 10.sp
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("SpiderMan", "Petter Parker", "Marvel", R.drawable.spiderman),
        Superhero("SpiderMan", "Petter Parker", "DC", R.drawable.logan),
        Superhero("SpiderMan", "Petter Parker", "DC", R.drawable.batman),
        Superhero("SpiderMan", "Petter Parker", "Marvel", R.drawable.thor),
        Superhero("SpiderMan", "Petter Parker", "DC", R.drawable.flash),
        Superhero("SpiderMan", "Petter Parker", "DC", R.drawable.green_lantern),
        Superhero("SpiderMan", "Petter Parker", "Marvel", R.drawable.wonder_woman),
    )
}