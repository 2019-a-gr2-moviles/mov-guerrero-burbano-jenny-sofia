package com.example.a05proyectorestaurante

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

class AdaptadorDisponibles(private val listaPlatos: List<Plato>,

                           private val contexto: GestionComboActivity,
                           private val recyclerView: RecyclerView, private val opcion: Int) : RecyclerView.Adapter<AdaptadorDisponibles.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombrePlato: TextView
        var descripcionPlato: TextView
        var precioPlato: TextView
        var botonSeleccionar: ImageButton
        var layout: ConstraintLayout
        var indice: Int
        var idPlato: Int

        init {
            nombrePlato = view.findViewById(R.id.txtd_nombre) as TextView
            descripcionPlato = view.findViewById(R.id.txtd_descripcion) as TextView
            precioPlato = view.findViewById(R.id.txtd_precio) as TextView
            layout = view.findViewById(R.id.layout_disponibles) as ConstraintLayout
            botonSeleccionar= view.findViewById(R.id.btn_addSeleccionado) as ImageButton
            indice = -1
            idPlato= -1
            botonSeleccionar.setOnClickListener {
                contexto.seleccionar(idPlato)
            }

        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_disponibles,
                p0,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaPlatos.size
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val plato = listaPlatos[position]
        myViewHolder.nombrePlato.text = plato.nombre
        myViewHolder.descripcionPlato.text = plato.descripcion
        myViewHolder.precioPlato.text = plato.precio.toString()
        myViewHolder.idPlato= plato.id!!
        myViewHolder.indice = position
    }
}