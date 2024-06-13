package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Admin_Agregar_Activity : AppCompatActivity() {
    private lateinit var guardar : Button
    private lateinit var borrar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_agregar)

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