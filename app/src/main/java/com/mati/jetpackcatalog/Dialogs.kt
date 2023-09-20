package com.mati.jetpackcatalog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDesmis: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDesmis() }) {
            Column(
                Modifier
                    .background(Color.White)
                    //ocupa toda la pantalla
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Phone ringtone")
                Divider(Modifier.fillMaxWidth(), color = Color.LightGray)
                var status by rememberSaveable { mutableStateOf(false) }

            }
        }
    }
}

@Composable
fun MyCustomDialog(
    show: Boolean, onDesmis: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog("my ejemplo")
                AccountItem(email = "ejekfn@gmail.com", drawable = R.drawable.avatar)
                AccountItem(email = "ejekfn@gmail.com", drawable = R.drawable.avatar)
                AccountItem(email = "ejekfn@gmail.com", drawable = R.drawable.add)
            }
        }
    }
}

@Composable// pongo @DrawableRes que esto va ser una referencia para que no reviente
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            //
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MyTitleDialog(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(bottom = 12.dp)

    )
}


@Composable
fun MySimpleCustomDialog(
    show: Boolean, onDesmis: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDesmis() },
            //cuando se hace para atras o clcik fuera del dialog
            properties = DialogProperties(
                dismissOnBackPress = false, dismissOnClickOutside = false
            )
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
            }
        }

    }

}


@Composable
fun MyDialog(
    show: Boolean, onDesmis: () -> Unit, onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(

            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.

            },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Hola soy un texto wuay") },
            //una accion para confirmar
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirm")
                }
            },
            //una accion para desconfirmar
            dismissButton = {
                TextButton(onClick = { onDesmis() }) {
                    Text(text = "Desmmis")
                }

            })
    }


}

