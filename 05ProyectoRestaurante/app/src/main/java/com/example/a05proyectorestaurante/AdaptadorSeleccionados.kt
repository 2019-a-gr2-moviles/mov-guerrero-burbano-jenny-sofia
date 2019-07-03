package com.example.a05proyectorestaurante

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

class AdaptadorSeleccionados(private val listaPlatos: List<PlatoAuxiliar>,

                             private val contexto: GestionComboActivity,
                             private val recyclerView: RecyclerView, private val opcion: Int) : RecyclerView.Adapter<AdaptadorSeleccionados.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombrePlato: TextView
        var descripcionPlato: TextView
        var precioPlato: TextView
        var botonSeleccionar: ImageButton

        var layout: ConstraintLayout
        var indice: Int
        var idSeleccionado: Int

        init {
            nombrePlato = view.findViewById(R.id.txts_nombre) as TextView
            descripcionPlato = view.findViewById(R.id.txts_descripcion) as TextView
            precioPlato = view.findViewById(R.id.txts_precio) as TextView
            botonSeleccionar= view.findViewById(R.id.btn_deleteSeleccionado)

            layout = view.findViewById(R.id.layout_seleccionados) as ConstraintLayout
            indice = -1
            idSeleccionado=-1
            botonSeleccionar.setOnClickListener {
                contexto.quitar(idSeleccionado)
            }



        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_seleccionados,
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
        myViewHolder.idSeleccionado=plato.idRelacion
        myViewHolder.indice = position

    }
}