package com.example.proyectofinal

data class Libro(
    val id: String,
    val nombre: String,
    var genero: String,
    var precio: Int,
    var disponibilidad: String,
    var descripcion: String
)
