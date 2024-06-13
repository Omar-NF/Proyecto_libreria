package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var usuario : EditText
    private lateinit var contraseña : EditText
    private lateinit var siguiente : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuario = findViewById(R.id.edtUsuario)
        contraseña = findViewById(R.id.edtPass)
        siguiente = findViewById(R.id.btnInicio)


    }

    fun btnSiguiente(v : View){
        if (usuario.text.isNotBlank() && usuario.text.isNotEmpty()
            && contraseña.text.isNotBlank() && contraseña.text.isNotEmpty()){
            val usser = usuario.text.toString()
            val pass = contraseña.text.toString()

            if (usser == "admin"){
                val intent = Intent(this,AdminActivity::class.java)
                startActivity(intent)
            }
            if (usser == "empleado"){

            }
            if (usser == "cliente"){
                val intent = Intent(this,MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}