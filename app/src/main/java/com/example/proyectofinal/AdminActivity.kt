package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class AdminActivity : AppCompatActivity() {
    private lateinit var agregar : ImageButton
    private lateinit var borrar : ImageButton
    private lateinit var lista : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        agregar = findViewById(R.id.imgAgregar)
        borrar = findViewById(R.id.imgBorrar)
        lista = findViewById(R.id.imgListaLibros)
    }

    fun btnagregar(view: View){
        val intent = Intent(this,Admin_Agregar_Activity::class.java)
        startActivity(intent)
    }

    fun btnborrar(view: View){
        val intent = Intent(this,Admin_Borrar_Activity::class.java)
        startActivity(intent)
    }

    fun btnlista(view: View){
        val intent = Intent(this,Admin_list_Activity::class.java)
        startActivity(intent)
    }

    fun btnsalir(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}