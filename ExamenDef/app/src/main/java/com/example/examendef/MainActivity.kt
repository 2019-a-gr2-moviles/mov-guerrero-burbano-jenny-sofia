package com.example.examendef

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object objetoCompartido
    {
        var nombre:String="prueba"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun enviarNombre(){
        objetoCompartido.nombre=input_Nombre.text.toString();

    }
}
