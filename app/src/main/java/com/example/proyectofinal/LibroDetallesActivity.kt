package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.ui.home.HomeFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LibroDetallesActivity : AppCompatActivity() {
    private lateinit var descripcion : TextView
    private lateinit var presio : TextView
    private lateinit var siguiente : Button
    private lateinit var regrsar : Button
    private lateinit var renta : Switch
    private lateinit var venta : Switch
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_detalles)

        descripcion = findViewById(R.id.txtDetalles)
        presio = findViewById(R.id.edtPresioCompra)
        regrsar = findViewById(R.id.btnRegresarInicio)
        renta = findViewById(R.id.swRentaLib)
        venta = findViewById(R.id.swVentaLib)
        siguiente = findViewById(R.id.btnSiguiente)

        //----------------------- Mostrar Info ---------------------------
        mostrarDetallesLibro()
    }

    private fun mostrarDetallesLibro() {
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val libroJson = sharedPreferences.getString("selectedLibro", null)

        if (libroJson != null) {
            val libro = gson.fromJson(libroJson, Libro::class.java)

            var informacion = "${libro.descripcion}"
            var presioLib = "${libro.precio}"

            descripcion.setText(informacion)
            presio.setText(presioLib)
        }
    }

    fun btnSiguiente(v: View){
        val sharedPreferences = getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val libroJson = sharedPreferences.getString("selectedLibro", null)

        if (libroJson != null) {
            val libro = gson.fromJson(libroJson, Libro::class.java)

            if (renta.isChecked && venta.isChecked) {
                Toast.makeText(this, "Selecciona venta o renta", Toast.LENGTH_SHORT).show()
            } else if (venta.isChecked) {
                val intent = Intent(this, VentaActivity::class.java)
                intent.putExtra("libro", gson.toJson(libro))
                intent.putExtra("libro", gson.toJson(libro))
                intent.putExtra("libro", gson.toJson(libro))
                intent.putExtra("libro", gson.toJson(libro))
                startActivity(intent)
            }else if (renta.isChecked) {
                val intent = Intent(this, Info_Libro_Activity::class.java)
                intent.putExtra("libro", gson.toJson(libro))
                startActivity(intent)
            }
        }
    }
    fun btnAtras(v : View){
        val intent = Intent(this,HomeFragment::class.java)
        startActivity(intent)
    }
}
