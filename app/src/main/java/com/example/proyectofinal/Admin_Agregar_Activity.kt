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

    fun guardarLibro(view: View){
        if (nombreLibros.text.isNotBlank() && nombreLibros.text.isNotEmpty()
            && precio_Libros.text.isNotBlank() && precio_Libros.text.isNotEmpty()){
            val nombre = nombreLibros.text.toString()
            val genero = opcionSel
            val precio = precio_Libros.text.toString().toInt()
            var disponibilidad : String = ""
            if (renta.isChecked) { disponibilidad = "Renta" }
            if (venta.isChecked) { disponibilidad = "Venta" }
            if (ambos.isChecked) { disponibilidad = "Ambos" }

            val libro = Libro(nombre, genero, precio, disponibilidad)
            guardarShared(libro)

            limpiar()
        }else{
            Toast.makeText(this,"Llena todos los campos", Toast.LENGTH_LONG).show()
        }
    }//fun guardar

    fun guardarShared(libro: Libro){
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        // Recuperar la lista actual de libros
        val json = sharedPreferences.getString("libros",null)
        val type = object : TypeToken<MutableList<Libro>>() {}.type
        val libros : MutableList<Libro> = if (json != null){
            gson.fromJson(json, type)
        }else{
            mutableListOf()
        }
        //Agregar un nuevo libro
        libros.add(libro)
        //Guardar la lista actualizada de libros
        val jsonString = gson.toJson(libros)
        editor.putString("libros",jsonString)
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
}