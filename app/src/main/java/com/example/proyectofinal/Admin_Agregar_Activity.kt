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
    private lateinit var nombreLibros: EditText
    private lateinit var genero_Libros: Spinner
    private lateinit var precio_Libros: EditText
    private lateinit var descripcion_Libros: EditText // Nuevo campo
    private lateinit var grupo: RadioGroup
    private lateinit var renta: RadioButton
    private lateinit var venta: RadioButton
    private lateinit var ambos: RadioButton
    private lateinit var guardar: Button
    private lateinit var borrar: Button
    private var opcionSel = "1"
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_agregar)

        nombreLibros = findViewById(R.id.edtNombre)
        genero_Libros = findViewById(R.id.spSpinner)
        precio_Libros = findViewById(R.id.edtPrecio)
        descripcion_Libros = findViewById(R.id.edtDescripcion) // Nuevo campo
        grupo = findViewById(R.id.rdGrupo)
        renta = findViewById(R.id.rdRenta)
        venta = findViewById(R.id.rdVenta)
        ambos = findViewById(R.id.rdAmbos)
        guardar = findViewById(R.id.btnGuardarLib)
        borrar = findViewById(R.id.btnBorrarLib)

        //---------------------------------- Spinner------------------------------------------
        val lstGeneros = resources.getStringArray(R.array.stArrayGeneros)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstGeneros)
        genero_Libros.adapter = adaptador
        genero_Libros.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                opcionSel = lstGeneros[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun guardarLibro(view: View) {
        if (nombreLibros.text.isNotBlank() && precio_Libros.text.isNotBlank() && descripcion_Libros.text.isNotBlank()) {
            val nombre = nombreLibros.text.toString()
            val genero = opcionSel  // Ya es texto legible
            val precio = precio_Libros.text.toString().toInt()
            val descripcion = descripcion_Libros.text.toString()

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
                libroExistente.genero = genero  // Actualizar género si ya existe el libro
                libroExistente.precio = precio
                libroExistente.disponibilidad = disponibilidad
                libroExistente.descripcion = descripcion
            } else {
                val libro = Libro(UUID.randomUUID().toString(), nombre, genero, precio, disponibilidad, descripcion)
                libros.add(libro)
            }

            val jsonString = gson.toJson(libros)
            sharedPreferences.edit().putString("libros", jsonString).apply()
            limpiar()
        } else {
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun limpiar() {
        nombreLibros.text = null
        precio_Libros.text = null
        descripcion_Libros.text = null // Nuevo campo
        grupo.clearCheck()
    }

    fun regresarAdmin(view: View) {
        val intent = Intent(this, AdminActivity::class.java)
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
                nombreLibros.setText(libro.nombre)
                precio_Libros.setText(libro.precio.toString())
                descripcion_Libros.setText(libro.descripcion) // Nuevo campo
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
