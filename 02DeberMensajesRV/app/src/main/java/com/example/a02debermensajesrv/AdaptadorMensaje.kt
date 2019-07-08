package com.example.a02debermensajesrv

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.w3c.dom.Text

class AdaptadorMensaje(private val listaMensajes: List<Mensaje>,

                       private val contexto: RecyclerViewActivity,
private val recyclerView: androidx.recyclerview.widget.RecyclerView
) :
androidx.recyclerview.widget.RecyclerView.Adapter<AdaptadorMensaje.MyViewHolder>() {
    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        var contactoTextView: TextView
        var contenidoMensajeTextView: TextView
        var numeroMensajesTextView: TextView
        var btnimg_star: ImageButton
        var favorito: Boolean
        var indice: Int


        var img_avatar: ImageView
        lateinit var layout: ConstraintLayout


        init {
            contactoTextView = view.findViewById(R.id.txt_contacto) as TextView
            contenidoMensajeTextView = view.findViewById(R.id.txt_contenidoMensaje) as TextView
            numeroMensajesTextView = view.findViewById(R.id.txt_numeroMensajes) as TextView
            btnimg_star= view.findViewById(R.id.btnimg_star) as ImageButton
            indice=-1

            img_avatar = view.findViewById(R.id.img_avatar) as ImageView
            layout = view.findViewById(R.id.linear_layout) as ConstraintLayout
            favorito=false;

            layout.setOnClickListener{
                Log.i("Recycler-view", "Layout presionado")
            }
            btnimg_star.setOnClickListener{
                if(listaMensajes[indice].favorito){
                    listaMensajes[indice].favorito=false

                }else{
                    listaMensajes[indice].favorito=true
                }
                contexto.iniciarRecycleView(listaMensajes, contexto, recyclerView)


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
        if(mensaje.favorito){
           // myViewHolder.
            myViewHolder.btnimg_star.setImageResource(R.drawable.nstar)
        }
        if(p1%2==0){
            myViewHolder.layout.setBackgroundColor(Color.rgb(234,244,244))
            myViewHolder.btnimg_star.setBackgroundColor(Color.rgb(234,244,244))



        }else{
            myViewHolder.layout.setBackgroundColor(Color.rgb(246,255,248))
            myViewHolder.btnimg_star.setBackgroundColor(Color.rgb(246,255,248))
        }
        myViewHolder.contactoTextView.text= mensaje.contacto
        myViewHolder.contenidoMensajeTextView.text= mensaje.contenidoMensaje
        myViewHolder.numeroMensajesTextView.text= mensaje.numeroMensajes.toString()
        myViewHolder.favorito=mensaje.favorito
        myViewHolder.indice= p1


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