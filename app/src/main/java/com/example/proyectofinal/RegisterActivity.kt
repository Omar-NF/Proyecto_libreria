package com.example.proyectofinal

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var usuario: EditText
    private lateinit var contraseña: EditText
    private lateinit var registrar: Button
    private lateinit var volver: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usuario = findViewById(R.id.edtUsuario)
        contraseña = findViewById(R.id.edtPass)
        registrar = findViewById(R.id.btnRegistrar)
        volver = findViewById(R.id.btnVolver)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    fun btnRegistrar(v: View) {
        val usser = usuario.text.toString()
        val pass = contraseña.text.toString()

        if (usser.isNotBlank() && pass.isNotBlank()) {
            val editor = sharedPreferences.edit()
            if (!sharedPreferences.contains(usser)) {
                editor.putString(usser, pass)
                editor.apply()
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun btnVolver(v: View) {
        finish() // Finaliza la actividad actual y vuelve a la actividad anterior en la pila
    }
}
