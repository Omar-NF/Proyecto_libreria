package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibroAdapter (private val libros: List<Libro>) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(view)
    }
    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]
        holder.bind(libro)
    }

    override fun getItemCount(): Int {
        return libros.size
    }

    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
        private val precioTextView: TextView = itemView.findViewById(R.id.precioTextView)
        private val disponibilidadTextView: TextView = itemView.findViewById(R.id.disponibilidadTextView)

        fun bind(libro: Libro) {
            nombreTextView.text = libro.nombre
            generoTextView.text = libro.genero
            precioTextView.text = libro.precio.toString()
            disponibilidadTextView.text = libro.disponibilidad
        }
    }
}