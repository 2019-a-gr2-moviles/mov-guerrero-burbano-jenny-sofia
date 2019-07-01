package com.example.a05proyectorestaurante

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class AdaptadorDisponibles(private val listaPlatos: List<Plato>,

                           private val contexto: MenuActivity,
                           private val recyclerView: RecyclerView, private val opcion: Int) : RecyclerView.Adapter<AdaptadorDisponibles.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombrePlato: TextView
        var descripcionPlato: TextView
        var precioPlato: TextView
        var layout: ConstraintLayout
        var indice: Int

        init {
            nombrePlato = view.findViewById(R.id.txt_nombre) as TextView
            descripcionPlato = view.findViewById(R.id.txt_descripcion) as TextView
            precioPlato = view.findViewById(R.id.txt_precio) as TextView
            layout = view.findViewById(R.id.layout_menu) as ConstraintLayout
            indice = -1
            if (opcion == 2) {
                layout.setOnClickListener {
                    contexto.withEditText(listaPlatos[indice], 0)
                }
            }
        }
    }

}