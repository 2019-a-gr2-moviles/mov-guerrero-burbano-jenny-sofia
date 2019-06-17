package com.example.examendef

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_gestion_estudiantes2.*

class GestionEstudiantes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_estudiantes2)

        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,MainActivity.dbEstudiante

        )
        val indiceSeleccionado= intent.getIntExtra("indice",-1)

        lv_estudiantes.adapter=adapter
        lv_estudiantes.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->

            seleccioinarEstudiante(position)
        }
    }
    fun seleccioinarEstudiante(indice:Int){
        val intent= Intent(
            this, EditarEstudiante::class.java
        )
        intent.putExtra("indice", indice)

        startActivity(intent);
    }



}
