package com.example.proyectofinal

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Margen(private val spaceHeight: Int) : RecyclerView.ItemDecoration()  {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            top = spaceHeight
            left = spaceHeight
            right = spaceHeight
            if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
                bottom = spaceHeight
            }
        }
    }
}