package com.example.examendef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_gestion_estudiantes2.*
import kotlinx.android.synthetic.main.activity_gestion_materia.*

class GestionMateria : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_materia)
        val idEstudianteSeleccionado= intent.getIntExtra("id",-1)
        val opcion= intent.getIntExtra("opcion",-1)
        cargarMaterias(idEstudianteSeleccionado)




    }
     fun cargarMaterias(idEstudiante:Int){
         val url = "${MainActivity.url}/materia?estudianteId=${idEstudiante}"
         var lista = listOf<Estudiante>()
         var listaLibros = ArrayList<Estudiante>()
         url
             .httpGet()
             .responseString { request, response, result ->
                 when(result){
                     is Result.Failure -> {
                         val ex = result.getException()
                         Log.i("http", "Errorssss: ${ex.message} --- ${request}")
                     }
                     is Result.Success -> {
                         val data = result.get()


                         var libroParseada = Klaxon().parseArray<Materia>(data)

                         MainActivity.dbMateria=libroParseada!!

                         runOnUiThread {
                             val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,libroParseada)
                             lv_Materias.adapter=adapter;

                             lv_Materias.onItemClickListener=
                                 AdapterView.OnItemClickListener { parent, view, position, id ->
                                     var index=libroParseada[position].id
                                     seleccionarMateria(index, idEstudiante)
                                 }

                         }

                     }
                 }
             }
     }
    fun seleccionarMateria(posicion:Int, idEstudiante: Int){
        val intent= Intent(
            this, EditMateriaDef::class.java
        )

        intent.putExtra("id",posicion )
        intent.putExtra("idEstudiante", idEstudiante)

        startActivity(intent);
    }


}
