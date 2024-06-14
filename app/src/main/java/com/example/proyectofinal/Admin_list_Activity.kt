package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Admin_list_Activity : AppCompatActivity() {
    private lateinit var listViewLibros: ListView
    private lateinit var regresar: Button
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_list)

        listViewLibros = findViewById(R.id.lvListaLibros)
        cargarLibros()
    }//Oncreate

    private fun cargarLibros() {
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("libros", null)
        val type = object : TypeToken<MutableList<Libro>>() {}.type
        val libros: MutableList<Libro> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
        val librosArray = libros.map { libro ->
            "${libro.nombre} - ${libro.genero} - ${libro.precio} - ${libro.disponibilidad}"
        }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, librosArray)
        listViewLibros.adapter = adapter
    }

    fun btnRegreso(v : View){
        val intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
    }
}