package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Info_Libro_Activity : AppCompatActivity() {
    private lateinit var descripcion : EditText
    private lateinit var presio : TextView
    private lateinit var comprar : Button
    private lateinit var regrsar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_libro)

        descripcion = findViewById(R.id.cajaLibro)
        presio = findViewById(R.id.edtPresioCompra)
        comprar = findViewById(R.id.btnComprarLib)
        regrsar = findViewById(R.id.btnRegresarInicio)
    }
}