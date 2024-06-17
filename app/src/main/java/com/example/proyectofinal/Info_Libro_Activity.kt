package com.example.proyectofinal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson

class Info_Libro_Activity : AppCompatActivity() {
    private lateinit var descripcion : EditText
    private lateinit var presio : TextView
    private lateinit var comprar : Button
    private lateinit var regrsar : Button
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_libro)

        descripcion = findViewById(R.id.cajaLibro)
        presio = findViewById(R.id.edtPresioCompra)
        comprar = findViewById(R.id.btnComprarLib)
        regrsar = findViewById(R.id.btnRegresarInicio)

        //----------------------- Mostrar Info ---------------------------
        mostrarDetallesLibro()
    }
    private fun mostrarDetallesLibro() {
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val libroJson = sharedPreferences.getString("selectedLibro", null)

        if (libroJson != null) {
            val libro = gson.fromJson(libroJson, Libro::class.java)

            var informacion = "${libro.descripcion}"
            var presioLib = "${libro.precio}"

            descripcion.setText(informacion)
            presio.setText(presioLib)
        }
    }

}