package com.example.a05proyectorestaurante

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

class AdaptadorCombo(private val listaCombos: List<Combo>,

private val contexto: MenuComboActivity,
private val recyclerView: RecyclerView, private val opcion:Int): RecyclerView.Adapter<AdaptadorCombo.MyViewHolder>() {



    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var nombreCombo: TextView
        var descripcionCombo: TextView
        var precioCombo: TextView
        var botonVerCombo: ImageButton
        var botonEditCombo: ImageButton
        var botonEliminarCombo: ImageButton
        var layout: ConstraintLayout
        var indice: Int
        var idSeleccionado: Int
        init {
            nombreCombo= view.findViewById(R.id.txtc_nombre) as TextView
            descripcionCombo= view.findViewById(R.id.txtc_descripcion) as TextView
            precioCombo= view.findViewById(R.id.txtc_precio) as TextView
            botonVerCombo = view.findViewById(R.id.btn_verCombo) as ImageButton
            botonEditCombo = view.findViewById(R.id.btn_editarCombo) as ImageButton
            botonEliminarCombo = view.findViewById(R.id.btn_eliminarCombo) as ImageButton
            layout = view.findViewById(R.id.layout_combo) as ConstraintLayout
            indice= -1
            idSeleccionado=-1
            botonVerCombo.setOnClickListener {
                contexto.irGestionCombo(idSeleccionado)
            }
            botonEliminarCombo.setOnClickListener {
                contexto.eliminarCombo(idSeleccionado)
            }
            botonEditCombo.setOnClickListener {
                contexto.editarGuardarCombo(listaCombos[indice], 2)
            }



        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorCombo.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_combo,
                p0,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaCombos.size
    }
    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val combo = listaCombos[position]
        myViewHolder.nombreCombo.text = combo.nombre
        myViewHolder.descripcionCombo.text = combo.descripcion
        myViewHolder.precioCombo.text = combo.precio.toString()
        myViewHolder.indice = position
        myViewHolder.idSeleccionado=combo.id!!
    }


}