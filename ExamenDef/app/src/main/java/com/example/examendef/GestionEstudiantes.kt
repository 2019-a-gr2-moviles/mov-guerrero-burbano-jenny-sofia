package com.example.examendef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_gestion_estudiantes2.*

class GestionEstudiantes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_estudiantes2)

        cargarEstudiantes()
        val indiceSeleccionado= intent.getIntExtra("indice",-1)


    }
    fun cargarEstudiantes(){
        val url = "${MainActivity.url}/estudiante"
        var lista = listOf<Estudiante>()
        var listaLibros = ArrayList<Estudiante>()
        url
            .httpGet()
            .responseString { request, response, result ->
                when(result){
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message} --- ${request}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        var libroParseada = Klaxon().parseArray<Estudiante>(data)
                        MainActivity.dbEstudiante=libroParseada!!

                        runOnUiThread {
                            val adapter =
                                ArrayAdapter(this, android.R.layout.simple_list_item_1, libroParseada)
                            lv_estudiantes.adapter = adapter
                            lv_estudiantes.onItemClickListener =
                                AdapterView.OnItemClickListener { parent, view, position, id ->

                                    seleccioinarEstudiante(position)
                                }
                        }

                    }
                }
            }

    }
    fun seleccioinarEstudiante(indice:Int){
        val intent= Intent(
            this, EditarEstudiante::class.java
        )
        intent.putExtra("indice", indice)

        startActivity(intent);
        finish()
    }



}
