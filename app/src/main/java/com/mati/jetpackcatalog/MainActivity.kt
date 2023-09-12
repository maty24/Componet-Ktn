package com.mati.jetpackcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.jetpackcatalog.ui.theme.JetpackcatalogTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    //esto es un estado
                    var myText by remember { mutableStateOf("") }
                    //las lambda pueden estar fuera de los parentesis
                    MyTextField(myText) { myText = it }
                   */MyImage()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackcatalogTheme {
        MyIcon()
    }
}

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