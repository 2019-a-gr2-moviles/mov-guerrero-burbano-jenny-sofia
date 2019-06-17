package com.example.a02debermensajesrv

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val lista = arrayListOf<Mensaje>()
//        val recycler_view= rv_personas
//        val actividad= this
        lista.add(Mensaje("Sofia", 10, "Hola sofía" ))
        lista.add(Mensaje("Ana", 4 ,"Hola Ana"))
        lista.add(Mensaje("Carmen", 1, "Hola Carmen"))
        lista.add(Mensaje("Aniii", 4 ,"Hola Anaee"))
        lista.add(Mensaje("Carmeneee", 1, "Hola Carmen222"))
        iniciarRecycleView(lista, this, rv_mensajes)

    }
    fun iniciarRecycleView(lista: List<Mensaje>, actividad:RecyclerViewActivity,recycler_view: RecyclerView){
        val adaptadorMensaje= AdaptadorMensaje(lista, actividad, recycler_view)
        rv_mensajes.adapter=adaptadorMensaje
        rv_mensajes.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_mensajes.layoutManager= LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        adaptadorMensaje.notifyDataSetChanged()
    }
//    fun irIntentRespuesta(){
//        val intent= Intent(
//            this, MainActivity::class.java
//        )
//        startActivity(intent);
//    }
}
