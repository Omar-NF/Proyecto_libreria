package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var usuario: EditText
    private lateinit var contraseña: EditText
    private lateinit var siguiente: Button
    private lateinit var registrar: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuario = findViewById(R.id.edtUsuario)
        contraseña = findViewById(R.id.edtPass)
        siguiente = findViewById(R.id.btnInicio)
        registrar = findViewById(R.id.btnRegister)

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Insertar usuario admin predeterminado en SharedPreferences si no existe
        val editor = sharedPreferences.edit()
        if (!sharedPreferences.contains("admin")) {
            editor.putString("admin", "admin")
        }
        editor.apply()

        // Configurar los OnClickListeners para los botones
        siguiente.setOnClickListener(this)
        registrar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnInicio -> btnSiguiente()
            R.id.btnRegister -> goToRegister()
        }
    }

    private fun btnSiguiente() {
        val usser = usuario.text.toString()
        val pass = contraseña.text.toString()

        if (usser.isNotBlank() && pass.isNotBlank()) {
            val storedPass = sharedPreferences.getString(usser, null)
            if (storedPass != null && storedPass == pass) {
                when (usser) {
                    "admin" -> {
                        val intent = Intent(this@MainActivity, AdminActivity::class.java)
                        startActivity(intent)
                    }
                    else -> {
                        val intent = Intent(this@MainActivity, MenuActivity::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
