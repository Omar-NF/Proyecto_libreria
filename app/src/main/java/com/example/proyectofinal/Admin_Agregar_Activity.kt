package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

class Admin_Agregar_Activity : AppCompatActivity() {
    private lateinit var nombreLibros : EditText
    private lateinit var cantidadLibros : Spinner
    private lateinit var precio_Libros : EditText
    private lateinit var grupo : RadioGroup
    private lateinit var renta : RadioButton
    private lateinit var venta : RadioButton
    private lateinit var ambos : RadioButton
    private lateinit var guardar : Button
    private lateinit var borrar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_agregar)

        nombreLibros = findViewById(R.id.edtNombre)
        cantidadLibros = findViewById(R.id.spCantidad)
        precio_Libros = findViewById(R.id)
        grupo = findViewById(R.id)
        renta = findViewById(R.id)
        venta = findViewById(R.id)
        ambos = findViewById(R.id)
        guardar = findViewById(R.id.btnGuardarLib)
        borrar = findViewById(R.id.btnBorrarLib)
    }

    fun guardarLibro(view: View){
    }

    fun regresarAdmin(view: View){
        val intent = Intent(this,AdminActivity::class.java)
        startActivity(intent)
    }
}