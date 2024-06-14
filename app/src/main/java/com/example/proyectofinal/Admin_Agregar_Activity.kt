package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.UUID

class Admin_Agregar_Activity : AppCompatActivity() {
    private lateinit var nombreLibros : EditText
    private lateinit var genero_Libros : Spinner
    private lateinit var precio_Libros : EditText
    private lateinit var grupo : RadioGroup
    private lateinit var renta : RadioButton
    private lateinit var venta : RadioButton
    private lateinit var ambos : RadioButton
    private lateinit var guardar : Button
    private lateinit var borrar : Button
    private var opcionSel = "1"
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_agregar)

        nombreLibros = findViewById(R.id.edtNombre)
        genero_Libros = findViewById(R.id.spSpinner)
        precio_Libros = findViewById(R.id.edtPrecio)
        grupo = findViewById(R.id.rdGrupo)
        renta = findViewById(R.id.rdRenta)
        venta = findViewById(R.id.rdVenta)
        ambos = findViewById(R.id.rdAmbos)
        guardar = findViewById(R.id.btnGuardarLib)
        borrar = findViewById(R.id.btnBorrarLib)

        //---------------------------------- Spinner------------------------------------------
        val lstGeneros = resources.getStringArray(R.array.stArrayGeneros)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstGeneros)
        //asosiacion
        genero_Libros.adapter = adaptador
        //Oidor de eventos
        genero_Libros.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                opcionSel = lstGeneros [position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }//oidor
    }//onCreate

    fun guardarLibro(view: View) {
        if (nombreLibros.text.isNotBlank() && nombreLibros.text.isNotEmpty() &&
            precio_Libros.text.isNotBlank() && precio_Libros.text.isNotEmpty()) {

            val nombre = nombreLibros.text.toString()
            val genero = opcionSel
            val precio = precio_Libros.text.toString().toInt()
            var disponibilidad = ""
            if (renta.isChecked) disponibilidad = "Renta"
            if (venta.isChecked) disponibilidad = "Venta"
            if (ambos.isChecked) disponibilidad = "Venta y renta"

            val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("libros", null)
            val type = object : TypeToken<MutableList<Libro>>() {}.type
            val libros: MutableList<Libro> = if (json != null) {
                gson.fromJson(json, type)
            } else {
                mutableListOf()
            }

            val libroExistente = libros.find { it.nombre == nombre }
            if (libroExistente != null) {
                // Actualizamos el libro existente
                libroExistente.genero = genero
                libroExistente.precio = precio
                libroExistente.disponibilidad = disponibilidad
            } else {
                // Creamos un nuevo libro
                val libro = Libro(UUID.randomUUID().toString(), nombre, genero, precio, disponibilidad)
                libros.add(libro)
            }

            val jsonString = gson.toJson(libros)
            sharedPreferences.edit().putString("libros", jsonString).apply()
            limpiar()
        } else {
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show()
        }
    }


    fun guardarShared(libro: Libro){
        // 1. Obtener una instancia de SharedPreferences
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        // 2. Crear un editor para modificar SharedPreferences
        val editor = sharedPreferences.edit()
        // 3. Recuperar la lista actual de libros en formato JSON
        val json = sharedPreferences.getString("libros", null)
        // 4. Definir el tipo de la lista de libros
        val type = object : TypeToken<MutableList<Libro>>() {}.type
        // 5. Deserializar el JSON a una lista mutable de objetos Libro si existe, o crear una lista vacía si no
        val libros: MutableList<Libro> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
        // 6. Agregar el nuevo libro a la lista
        libros.add(libro)
        // 7. Serializar la lista actualizada de libros a formato JSON
        val jsonString = gson.toJson(libros)
        // 8. Guardar la lista actualizada de libros en SharedPreferences
        editor.putString("libros", jsonString)
        editor.apply()
    }

    private fun limpiar() {
        nombreLibros.text = null
        precio_Libros.text = null
        grupo.clearCheck()
    }

    fun regresarAdmin(view: View){
        val intent = Intent(this,AdminActivity::class.java)
        startActivity(intent)
    }

    fun buscarLibro(view: View) {
        val nombre = nombreLibros.text.toString()
        if (nombre.isNotBlank() && nombre.isNotEmpty()) {
            val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("libros", null)
            val type = object : TypeToken<MutableList<Libro>>() {}.type
            val libros: MutableList<Libro> = if (json != null) {
                gson.fromJson(json, type)
            } else {
                mutableListOf()
            }

            val libro = libros.find { it.nombre == nombre }
            if (libro != null) {
                // Llenar los campos de edición con los datos del libro
                nombreLibros.setText(libro.nombre)
                precio_Libros.setText(libro.precio.toString())
                when (libro.disponibilidad) {
                    "Renta" -> renta.isChecked = true
                    "Venta" -> venta.isChecked = true
                    "Venta y renta" -> ambos.isChecked = true
                }
                genero_Libros.setSelection((genero_Libros.adapter as ArrayAdapter<String>).getPosition(libro.genero))
            } else {
                Toast.makeText(this, "Libro no encontrado", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Ingresa el nombre del libro", Toast.LENGTH_LONG).show()
        }
    }



}