package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.MainActivity
import com.example.proyectofinal.databinding.ActivityInfoLibroBinding
import com.example.proyectofinal.Libro
import com.google.gson.Gson
import java.util.Calendar

class Info_Libro_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoLibroBinding
    private lateinit var calendarView: CalendarView
    private lateinit var btnRentar: Button
    private lateinit var libro: Libro

    private var fechaRecogida: Long? = null
    private var fechaDevolucion: Long? = null

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el libro desde el intento
        val libroJson = intent.getStringExtra("libro")
        libro = gson.fromJson(libroJson, Libro::class.java)

        calendarView = binding.calendarView
        btnRentar = binding.btnRentar

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Capturar la fecha seleccionada del calendario
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            if (fechaRecogida == null) {
                fechaRecogida = calendar.timeInMillis
                Toast.makeText(this, "Fecha de recogida seleccionada", Toast.LENGTH_SHORT).show()
            } else {
                fechaDevolucion = calendar.timeInMillis
                Toast.makeText(this, "Fecha de devolución seleccionada", Toast.LENGTH_SHORT).show()
            }
        }

        btnRentar.setOnClickListener {
            // Verificar que se hayan seleccionado ambas fechas
            if (fechaRecogida != null && fechaDevolucion != null) {
                mostrarDetallesLibro()
                // Al completar la renta, iniciar MainActivity
                val intent = Intent(this, MenuActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Seleccione ambas fechas de recogida y devolución", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarDetallesLibro() {
        // Mostrar detalles del libro y las fechas seleccionadas
        val mensaje = "Detalles del libro:\n" +
                "Nombre: ${libro.nombre}\n" +
                "Género: ${libro.genero}\n" +
                "Precio: ${libro.precio}\n" +
                "Fecha de Recogida: ${convertirFecha(fechaRecogida!!)}\n" +
                "Fecha de Devolución: ${convertirFecha(fechaDevolucion!!)}"

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun convertirFecha(timestamp: Long): String {
        val formato = java.text.SimpleDateFormat("dd/MM/yyyy")
        return formato.format(java.util.Date(timestamp))
    }
}