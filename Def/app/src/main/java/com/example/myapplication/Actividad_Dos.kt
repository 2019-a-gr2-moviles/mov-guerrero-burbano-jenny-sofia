package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actividad__dos.*
import kotlinx.android.synthetic.main.content_main.*

class Actividad_Dos : AppCompatActivity() {
    var primero: Boolean=false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad__dos)
        val nombre= intent.getStringExtra("nombre")
        val edad=intent.getIntExtra("edad",0)
        println(nombre)
        println(edad)
        btn_actividad_1.setOnClickListener {
            this.irActividadUno()

        }

    }
    fun irActividadUno(){
        val intent= Intent(
            this, MainActivity::class.java
        )
        startActivity(intent)
       //s finish()


    }
}
