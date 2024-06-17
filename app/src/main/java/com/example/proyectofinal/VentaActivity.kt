package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectofinal.ui.home.HomeFragment
import com.google.gson.Gson

class VentaActivity : AppCompatActivity() {
    private lateinit var nombre : TextView
    private lateinit var operacion : TextView
    private lateinit var correo : TextView
    private lateinit var precio : TextView
    private lateinit var confirmar : Button
    private lateinit var salir : Button
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()
    private var libroNom : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venta)

        nombre = findViewById(R.id.txtNombreLib)
        operacion = findViewById(R.id.txtOperacionLib)
        correo = findViewById(R.id.txtUsuario)
        precio = findViewById(R.id.txtPrecio)
        confirmar = findViewById(R.id.btnComprarConf)
        salir = findViewById(R.id.btnRegreso)

        val libroJson = intent.getStringExtra("libro")
        if (libroJson != null) {
            val libro = gson.fromJson(libroJson, Libro::class.java)
            nombre.text = libro.nombre
            operacion.text = libro.disponibilidad
            correo.text = libro.genero
            precio.text = libro.precio.toString()

            libroNom = libro.nombre
        }
    }

    fun btnComprar(v : View){
        Toast.makeText(this, "Gracias por tu comra!! \n tu libro $libroNom", Toast.LENGTH_SHORT).show()
        nombre.text = ""
        operacion.text = ""
        correo.text = ""
        precio.text = ""
    }

    fun btnSalir(v : View){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}