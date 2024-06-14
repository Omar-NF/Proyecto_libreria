package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Admin_Borrar_Activity : AppCompatActivity() {
    private lateinit var libroBorrar : EditText
    private lateinit var eliminar : Button
    private lateinit var regresar : Button
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_borrar)

        libroBorrar = findViewById(R.id.edtLibroAEliminar)
        eliminar = findViewById(R.id.btnEliminarLib)
        regresar = findViewById(R.id.btnRegresar)
    }

    fun eliminarLibro(view: View){
        val nombreLibro = libroBorrar.text.toString()

        if (nombreLibro.isNotBlank() && nombreLibro.isNotEmpty()) {
            val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val json = sharedPreferences.getString("libros", null)
            val type = object : TypeToken<MutableList<Libro>>() {}.type
            val libros: MutableList<Libro> = if (json != null) {
                gson.fromJson(json, type)
            } else {
                mutableListOf()
            }
            // Buscar y eliminar el libro
            val libroEliminado = libros.removeIf { it.nombre == nombreLibro }

            if (libroEliminado) {
                //Guardar lista actualizada
                val jsonString = gson.toJson(libros)
                editor.putString("libros", jsonString)
                editor.apply()
                Toast.makeText(this, "Libro eliminado", Toast.LENGTH_SHORT).show()
                libroBorrar.text = null
            }
        }else {
            Toast.makeText(this, "Libro no encontrado", Toast.LENGTH_SHORT).show()
        }
    }//fun

    fun regresarAdmin(view: View){
        val intent = Intent(this,AdminActivity::class.java)
        startActivity(intent)
    }
}