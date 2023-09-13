package com.mati.jetpackcatalog.ui.theme

//la data class es como para hacer un tipado de los atrivutos
data class CheckInfo(
    val title: String,
    var selected: Boolean = false,
    var onChekedChange: (Boolean) -> Unit
)