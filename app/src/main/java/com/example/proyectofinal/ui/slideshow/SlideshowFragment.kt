package com.example.proyectofinal.ui.slideshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.Info_Libro_Activity
import com.example.proyectofinal.Libro
import com.example.proyectofinal.R
import com.example.proyectofinal.VentaActivity
import com.example.proyectofinal.databinding.FragmentSlideshowBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SlideshowFragment : Fragment() {
    private lateinit var nombreLibroBuscar : EditText
    private lateinit var venta : Switch
    private lateinit var renta : Switch
    private lateinit var buscar : Button
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        nombreLibroBuscar = root.findViewById(R.id.edtNomLibroBuscar)
        venta = root.findViewById(R.id.swVenta)
        renta = root.findViewById(R.id.swRenta)
        buscar = root.findViewById(R.id.btnNuscar)

        buscar.setOnClickListener{
            btnBuscar ()
        }//onClickListener

        return root
    }

    fun btnBuscar (){
        val nombre = nombreLibroBuscar.text.toString()

        if (nombre.isNotBlank()) {
            val sharedPreferences = requireContext().getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("libros", null)
            val type = object : TypeToken<MutableList<Libro>>() {}.type
            val libros: MutableList<Libro> = if (json != null) {
                gson.fromJson(json, type)
            } else {
                mutableListOf()
            }

            val libro = libros.find { it.nombre == nombre }
            if (libro != null) {
                if (venta.isChecked) {
                    // Redirigir a la actividad de venta
                    val intent = Intent(requireContext(), VentaActivity::class.java)
                    intent.putExtra("libro", gson.toJson(libro))
                    startActivity(intent)
                } else if (renta.isChecked) {
                    // Redirigir a la actividad de renta
                    val intent = Intent(requireContext(), Info_Libro_Activity::class.java)
                    intent.putExtra("libro", gson.toJson(libro))
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Seleccione una opción (Venta o Renta)", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Libro no encontrado", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Ingrese el nombre del libro", Toast.LENGTH_LONG).show()
        }
    }

    fun obtenerDatos(){
        val nombre = nombreLibroBuscar.text.toString()

        if (nombre.isNotBlank()) {
            val sharedPreferences =
                requireContext().getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("libros", null)
            val type = object : TypeToken<MutableList<Libro>>() {}.type
            val libros: MutableList<Libro> = if (json != null) {
                gson.fromJson(json, type)
            } else {
                mutableListOf()
            }

            val libro = libros.find { it.nombre == nombre }
            if (libro != null){//si se encontro el libro
                val libro_nom = libro.nombre
                val libro_gen = libro.genero
                val libro_pre = libro.precio
                val libro_dis = libro.disponibilidad
            }
        }
    }//obtenerDatos

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}