package com.example.proyectofinal

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class LibroDetallesActivity : AppCompatActivity() {
    private lateinit var nombreTextView: TextView
    private lateinit var generoTextView: TextView
    private lateinit var precioTextView: TextView
    private lateinit var disponibilidadTextView: TextView
    private lateinit var descripcionTextView: TextView

    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_detalles)

        nombreTextView = findViewById(R.id.nombreTextView)
        generoTextView = findViewById(R.id.generoTextView)
        precioTextView = findViewById(R.id.precioTextView)
        disponibilidadTextView = findViewById(R.id.disponibilidadTextView)
        descripcionTextView = findViewById(R.id.descripcionTextView)

        mostrarDetallesLibro()
    }

    private fun mostrarDetallesLibro() {
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val libroJson = sharedPreferences.getString("selectedLibro", null)

        if (libroJson != null) {
            val libro = gson.fromJson(libroJson, Libro::class.java)
            nombreTextView.text = libro.nombre
            generoTextView.text = libro.genero
            precioTextView.text = libro.precio.toString()
            disponibilidadTextView.text = libro.disponibilidad
            descripcionTextView.text = libro.descripcion
        }
    }
}
