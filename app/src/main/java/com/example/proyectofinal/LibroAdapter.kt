package com.example.proyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibroAdapter(private val libros: List<Libro>, private val onVerMasClick: (Libro) -> Unit) :
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return LibroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]
        holder.bind(libro)

        // Configurar el listener del botón
        holder.verMasButton.setOnClickListener {
            onVerMasClick(libro)
        }
    }

    override fun getItemCount(): Int {
        return libros.size
    }

    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
        private val precioTextView: TextView = itemView.findViewById(R.id.precioTextView)
        private val disponibilidadTextView: TextView = itemView.findViewById(R.id.disponibilidadTextView)
        val verMasButton: Button = itemView.findViewById(R.id.btnVerMas)

        fun bind(libro: Libro) {
            nombreTextView.text = libro.nombre
            generoTextView.text = libro.genero
            precioTextView.text = libro.precio.toString()
            disponibilidadTextView.text = libro.disponibilidad
        }
    }
}