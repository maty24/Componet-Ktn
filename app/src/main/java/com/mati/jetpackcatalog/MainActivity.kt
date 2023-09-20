package com.mati.jetpackcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mati.jetpackcatalog.ui.theme.CheckInfo
import com.mati.jetpackcatalog.ui.theme.JetpackcatalogTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    //esto es un estado
                    var myText by remember { mutableStateOf("") }
                    //las lambda pueden estar fuera de los parentesis
                    MyTextField(myText) { myText = it }
*//*
                    val myOptions = getOptions(listOf("matias", "ejemplo", "dani"))
                    Column {
                        myOptions.forEach {
                            //el it es el valor del myOption
                            MyCheckBoxWithTextCompleted(it)
                        }
                    }*/
                    /*
                    var show by rememberSaveable { mutableStateOf(false) }
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Mostrar dialogo")
                        }
                        MyDialog(
                            show = show,
                            onDesmis = { show = false },
                            onConfirm = { Log.i("mati", "ola mtis") })

                    }*/
                    /*
                    var show by rememberSaveable { mutableStateOf(false) }
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Mostrar dialogo")
                        }
                        MyConfirmationDialog(
                            show = show,
                            onDesmis = { show = false }
                        )
                    }*/
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = "screen1") {
                        composable("screen1") {
                            Screen1(navigationController)
                        }
                        composable("screen2") {
                            Screen2(navigationController)
                        }
                        composable("screen3") {
                            Screen3(navigationController)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            //it es el valor de tites
            title = it,
            selected = status,
            onChekedChange = { myNewStatus -> status = myNewStatus }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackcatalogTheme {
        MyBadgeBox()
    }
}


@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {
    Row(Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onChekedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}


////////////////////////////////
@Composable
fun MyText() {
    Column {
        Text(text = "Esto es una ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        //para los estilos no es necesio ponder style
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.combine(
                listOf(TextDecoration.Underline, TextDecoration.LineThrough)
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 20.sp)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable//el segundo parametro es un tipado de la funcion lambda
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {

    //el it es el que contiene el valor
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFiledAdvance() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = {
        //pregunta si nuectra cadena de texto contiene una a
        myText = if (it.contains("a")) {
            //si contiene una a que me lo reemplaze por ""
            it.replace("a", "")
        } else {
            it
        }
    },
        label = { Text(text = "Introduce tu nombre") })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutlined() {
    var myText by remember { mutableStateOf("") }
    //visulmente es diferente
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Holita") },
        //color del inmput, parambiar el color
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Red
        )
    )
}


////////////////BOTONES///////////////

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)

    ) {
        Button(
            //funcion del boton
            onClick = { enabled = false },
            //desavilitar boton
            enabled = enabled,
            //colores del boton
            colors = ButtonDefaults.buttonColors(
                //backgrund
                containerColor = Color.Blue,
                contentColor = Color.Magenta
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola")
        }
        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                //backgrund
                containerColor = Color.Blue,
                contentColor = Color.Magenta,
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.Green
            ),
        ) {
            Text(text = "Hola")
        }
    }
}


////////////IMAGENES//////////////

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ola",
        alpha = 0.5f
    )
}

@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ola",
        modifier = Modifier
            //el clip es para recortar la imagen
            .clip(CircleShape)
            //le pongo un borde
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyIcon() {//icnos de google estan todos los iconos
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Icono", tint = Color.Red)
}


///////////////CIRCULAR PROGRES///////////////////

@Composable
fun MyProgress() {
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
        LinearProgressIndicator(modifier = Modifier.padding(top = 32.dp), color = Color.Red)
    }
}

/////////////////////SWITCH//////////////

@Composable
fun MySwitch() {
    //esto es para mantener el estado
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            //sin chekear
            uncheckedThumbColor = Color.Red,
            //con chekear
            checkedThumbColor = Color.Green
        )
    )
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true
    )
}


@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Row(Modifier.padding(8.dp)) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

//////////////CARD///////////////

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        //
        shape = MaterialTheme.shapes.small,
        //estilo de los bordes
        border = BorderStroke(2.dp, Color.Black),
        //backgroud color
        colors = CardDefaults.cardColors(Color.Magenta)

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ejemplo 1")
            Text(text = "ejemplo 2")
            Text(text = "ejemplo 3")

        }
    }
}

//////////////////////BADGEDBOX///////////////////////////////////


//esta es la weaita que poner como un numero arriba de un icono o algo por el estilo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    //el badge es el contenido del circulo de arriba
    BadgedBox(
        badge = { Text(text = "100") },
    ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "favorite")
    }

}

//////////////////////////////DIVIDER////////////////////

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )
}

//////////////////////////////DROPDOWN////////////////////
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu() {
    var selectedText by rememberSaveable {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var desserts = listOf("elao", "chocolate", "cafe", "cuenta ru")
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { desserts ->
                DropdownMenuItem(text = { Text(text = desserts) }, onClick = { expanded = false })
            }
        }
    }
}