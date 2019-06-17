package com.example.a02debermensajesrv

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text

class AdaptadorMensaje(private val listaMensajes: List<Mensaje>,

                       private val contexto: RecyclerViewActivity,
private val recyclerView: RecyclerView) :
RecyclerView.Adapter<AdaptadorMensaje.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var contactoTextView: TextView
        var contenidoMensajeTextView: TextView
        var numeroMensajesTextView: TextView

        init {
            contactoTextView = view.findViewById(R.id.txt_contacto) as TextView
            contenidoMensajeTextView = view.findViewById(R.id.txt_contenidoMensaje) as TextView
            numeroMensajesTextView = view.findViewById(R.id.txt_numeroMensajes) as TextView
            val layout = view.findViewById(R.id.linear_layout) as LinearLayout
            layout.setOnClickListener{
                Log.i("Recycler-view", "Layout presionado")
            }
//            accionBoton.setOnClickListener {
//                nombreTextView.text="Me cambiarooooooooooooN!!!!!!!!!!!"
//                contexto.cambiarNombreTextView("WOW!")
//                val nuevasPersonas = arrayListOf<Persona>()
//                nuevasPersonas.add(Persona("Sofia", "0401245698"))
//                nuevasPersonas.add(Persona("Andrés", "1101245698"))
//                nuevasPersonas.add(Persona("Paula", "1401245698"))
//                contexto.iniciarRecycleView(nuevasPersonas, contexto, recyclerView)
//            }
        }
    }
    override fun getItemCount(): Int {
        return listaMensajes.size;
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorMensaje.MyViewHolder, p1: Int) {
        val mensaje= listaMensajes[p1]
        myViewHolder.contactoTextView.text= mensaje.contacto
        myViewHolder.contenidoMensajeTextView.text= mensaje.contenidoMensaje
        myViewHolder.numeroMensajesTextView.text= mensaje.numeroMensajes.toString()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorMensaje.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout,
                p0,
                false
            )

        return MyViewHolder(itemView)
    }
}