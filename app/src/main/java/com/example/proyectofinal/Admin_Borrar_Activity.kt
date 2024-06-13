package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class Admin_Borrar_Activity : AppCompatActivity() {
    private lateinit var eliminar : Button
    private lateinit var regresar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_borrar)

        eliminar = findViewById(R.id.btnEliminarLib)
        regresar = findViewById(R.id.btnRegresar)
    }

    fun eliminarLibro(view: View){
    }

    fun regresarAdmin(view: View){
        val intent = Intent(this,AdminActivity::class.java)
        startActivity(intent)
    }
}