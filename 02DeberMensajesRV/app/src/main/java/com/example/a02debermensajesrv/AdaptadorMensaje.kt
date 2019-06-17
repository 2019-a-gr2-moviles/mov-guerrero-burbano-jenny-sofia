package com.example.a02debermensajesrv

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class AdaptadorMensaje(private val listaMensajes: List<Mensaje>,

                       private val contexto: RecyclerViewActivity,
private val recyclerView: RecyclerView) :
RecyclerView.Adapter<AdaptadorMensaje.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: AdaptadorMensaje.MyViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorMensaje.MyViewHolder {

    }
}