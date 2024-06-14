package com.example.proyectofinal.ui.slideshow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal.Libro
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.FragmentSlideshowBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SlideshowFragment : Fragment() {
    private lateinit var nombreLibroBuscar : EditText
    private lateinit var venta : Switch
    private lateinit var renta : Switch
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

        nombreLibroBuscar.setOnClickListener{
            btnBuscar ()
        }

        return root
    }

    fun btnBuscar (){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}