package com.example.a04_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycle_view.*
import kotlin.random.Random

class RecycleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)
        val lista = arrayListOf<Persona>()
        val recycler_view= rv_personas
        val actividad= this
        lista.add(Persona("Sofia", "0404734665"))
        lista.add(Persona("Ana", "174585865"))
        lista.add(Persona("Carmen", "0404734665"))
        val adaptadorPersona= AdaptadorPersona(lista, actividad, recycler_view)
        rv_personas.adapter=adaptadorPersona
        rv_personas.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_personas.layoutManager= LinearLayoutManager(this)
        adaptadorPersona.notifyDataSetChanged()
    }

}
