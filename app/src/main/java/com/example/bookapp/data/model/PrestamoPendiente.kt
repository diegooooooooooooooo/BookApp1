package com.example.bookapp.data.model

data class PrestamoPendiente(
    val socioNombre: String,
    val libroTitulo: String,
    val fechaVencimiento: Long,
    val diasRetraso: Int
)