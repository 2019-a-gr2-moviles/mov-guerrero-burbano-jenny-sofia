package com.example.a02debermensajesrv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        this.supportActionBar?.hide()
        val lista = arrayListOf<Mensaje>()
//        val recycler_view= rv_personas
//        val actividad= this
        lista.add(Mensaje("Sofia", 10, "Hola sofía", false ))
        lista.add(Mensaje("Ana", 4 ,"Hola Ana", true))
        lista.add(Mensaje("Carmen", 1, "Hola Carmen", false))
        lista.add(Mensaje("Aniii", 4 ,"Hola Anaee", false))
        lista.add(Mensaje("Carmeneee", 1, "Hola Carmen222", false))
        lista.add(Mensaje("Carmeneee3", 1, "Hola Carmen222", false))
        lista.add(Mensaje("Carmeneee4", 1, "Hola Carmen222", false))
        iniciarRecycleView(lista, this, rv_mensajes)

    }

    fun iniciarRecycleView(lista: List<Mensaje>, actividad:RecyclerViewActivity,recycler_view: androidx.recyclerview.widget.RecyclerView){
        val adaptadorMensaje= AdaptadorMensaje(lista, actividad, recycler_view)
        rv_mensajes.adapter=adaptadorMensaje
        rv_mensajes.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        //Nos falta el layout manager
        rv_mensajes.layoutManager= androidx.recyclerview.widget.LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adaptadorMensaje.notifyDataSetChanged()
    }
//    fun irIntentRespuesta(){
//        val intent= Intent(
//            this, MainActivity::class.java
//        )
//        startActivity(intent);
//    }
}
