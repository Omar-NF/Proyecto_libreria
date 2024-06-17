package com.example.proyectofinal.ui.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.InfoLibroActivity
import com.example.proyectofinal.Info_Libro_Activity
import com.example.proyectofinal.Libro
import com.example.proyectofinal.LibroAdapter
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.FragmentGalleryBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GalleryFragment : Fragment() {
    private lateinit var listaDeLibros : RecyclerView
    private val sharedPrefsFile = "LibraryPrefs"
    private val gson = Gson()

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listaDeLibros = root.findViewById(R.id.rclListaDeLibros)
        listaDeLibros.layoutManager = LinearLayoutManager(context)

        cargarLibros()

        return root
    }

    private fun cargarLibros() {
        val sharedPreferences = requireContext().getSharedPreferences(sharedPrefsFile, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("libros", null)
        val type = object : TypeToken<MutableList<Libro>>() {}.type
        val libros: MutableList<Libro> = if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }

        // Configurar el adaptador con un listener para el botón
        val adapter = LibroAdapter(libros) { libro ->
            // Aquí manejas el clic del botón
            Toast.makeText(requireContext(), "Ver más de: ${libro.nombre}", Toast.LENGTH_SHORT).show()
            // Puedes iniciar una nueva actividad o fragmento aquí
            val intent = Intent(requireContext(), Info_Libro_Activity::class.java)
            intent.putExtra("libro", gson.toJson(libro))
            startActivity(intent)
        }
        listaDeLibros.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
