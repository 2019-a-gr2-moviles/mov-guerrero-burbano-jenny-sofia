package com.example.a05proyectorestaurante

import android.support.constraint.ConstraintLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import org.w3c.dom.Text

class AdaptadorMenu(
    private val listaPlatos: List<Plato>,

    private val contexto: MenuActivity,
    private val recyclerView: RecyclerView, private val opcion: Int) : RecyclerView.Adapter<AdaptadorMenu.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombrePlato: TextView
        var descripcionPlato: TextView
        var precioPlato: TextView
        var botonEliminar: ImageButton

        var layout: ConstraintLayout
        var indice: Int
        var idPlato: Int

        init {
            nombrePlato = view.findViewById(R.id.txt_nombre) as TextView
            descripcionPlato = view.findViewById(R.id.txt_descripcion) as TextView
            precioPlato = view.findViewById(R.id.txt_precio) as TextView
            botonEliminar= view.findViewById(R.id.btn_eliminarPlato) as ImageButton



            layout = view.findViewById(R.id.layout_menu) as ConstraintLayout
            indice = -1
            idPlato= -1
//            btnBuscarPlato.setOnClickListener {
//                contexto.buscar()
//            }
            if (opcion == 2) {
                layout.setOnClickListener {
                    contexto.withEditText(listaPlatos[indice], 0)
                }
                botonEliminar.setOnClickListener {
                    contexto.eliminarPlato( idPlato )
                }

            }else{
                botonEliminar.visibility=View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return listaPlatos.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorMenu.MyViewHolder, position: Int) {
        val plato = listaPlatos[position]
        myViewHolder.nombrePlato.text = plato.nombre
        myViewHolder.descripcionPlato.text = plato.descripcion
        myViewHolder.precioPlato.text = plato.precio.toString()
        myViewHolder.indice = position
        myViewHolder.idPlato= plato.id!!
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorMenu.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_menu,
                p0,
                false
            )

        return MyViewHolder(itemView)
    }

}