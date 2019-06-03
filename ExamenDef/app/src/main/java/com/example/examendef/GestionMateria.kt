package com.example.examendef

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_gestion_materia.*

class GestionMateria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_materia)
        val idEstudianteSeleccionado= intent.getIntExtra("id",-1)
        var arregloFiltrado =MainActivity.dbMateria.filter {
                value->value.estudianteId==idEstudianteSeleccionado
        }

        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,arregloFiltrado

        )
        lv_Materias.adapter=adapter;
        val opcion= intent.getIntExtra("opcion",-1)
        lv_Materias.onItemClickListener=
            AdapterView.OnItemClickListener { parent, view, position, id ->
                var index=arregloFiltrado[position].id
                seleccionarMateria(index)
            }


    }
    fun seleccionarMateria(posicion:Int){
        val intent= Intent(
            this, EditMateriaDef::class.java
        )

        intent.putExtra("id",posicion )

        startActivity(intent);
    }


}
