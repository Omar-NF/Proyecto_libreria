package com.example.proyectofinal

data class Administrador(
    val usuario: String,
    val contraseña: String,
    val privilegios: String
) : Usuario(usuario, contraseña)
