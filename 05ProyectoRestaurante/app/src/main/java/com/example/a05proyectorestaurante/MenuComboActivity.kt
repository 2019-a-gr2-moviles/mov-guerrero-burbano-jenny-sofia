package com.example.a05proyectorestaurante

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu_combo.*

class MenuComboActivity : AppCompatActivity() {
    companion object objetoCompartido {

        var listaCombos = listOf<Combo>()
        var opcion:Int =-1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_combo)
        this.supportActionBar?.hide()
        cargar(0)
        btn_nuevoCombo.setOnClickListener {
            editarGuardarCombo(Combo(-1, "", "", 0.0), 1)
        }
        btnCombo.setOnClickListener {
            buscar()
        }

    }
    fun buscar(){
        var aux= listaCombos.filter {
            it.nombre.contains(inputc_busqueda.text.toString())

        }
        iniciarRecycleView(aux, this, rv_combo, opcion)

    }
    fun irGestionCombo(indice:Int){
        val intent= Intent(
            this, GestionComboActivity::class.java
        )
        intent.putExtra("indice", indice )
        startActivity(intent);
    }
    fun cargar(opcion: Int){
        val url = "${MenuActivity.objetoCompartido.url}/combo"
        var lista= listOf<Combo>()

        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")
                        var platoParseada = Klaxon().parseArray<Combo>(data)
                        lista=platoParseada!!
                        listaCombos=lista
                        runOnUiThread {
                            iniciarRecycleView(lista, this, rv_combo, opcion)
                        }

                    }
                }
            }
    }
    fun iniciarRecycleView(lista: List<Combo>, actividad:MenuComboActivity, recycler_view: RecyclerView, opcion: Int){
        val adaptadorPlato= AdaptadorCombo(lista, actividad, recycler_view, opcion)
        rv_combo.adapter=adaptadorPlato
        rv_combo.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_combo.layoutManager= LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }
    fun guardarPlato(combo: Combo) {
        val url = "${MenuActivity.url}/combo"
        val bodyJson = """
              {
                "nombre": "${combo.nombre}",
                "descripcion" : "${combo.descripcion}",
                "precio": ${combo.precio}
              }
            """
        url
            .httpPost().body(bodyJson)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "rEQUEST: ${request}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            cargar(opcion)
                        }

                        Log.i("http", "TODO BIIIEN")
                    }
                }
            }

    }

    fun editarPlato(combo: Combo) {
        val url = "${MenuActivity.url}/combo/${combo.id}"
        val bodyJson = """
  {
    "nombre": "${combo.nombre}",
    "descripcion" : "${combo.descripcion}",
    "precio": ${combo.precio}
  }
"""
        url.httpPut().body(bodyJson)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "rEQUEST: ${request}")

                    }
                    is Result.Success -> {
                        runOnUiThread {
                            cargar(opcion)
                        }

                        Log.i("http", "TODO BIIIEN")
                    }
                }
            }

    }
    fun eliminarCombo(idCombo: Int){
        val url = "${MenuActivity.url}/combo?id=${idCombo}"
        var lista = listOf<Combo>()

        url
            .httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {

                        runOnUiThread {
                            cargar(opcion)
                        }

                    }
                }
            }
    }
    fun editarGuardarCombo(combo: Combo, opcion: Int) {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.dialog_combo, null)

        val nombre = dialogLayout.findViewById<EditText>(R.id.inputC_nombre)
        val descripcion = dialogLayout.findViewById<EditText>(R.id.inputC_descripcion)
        val precio = dialogLayout.findViewById<EditText>(R.id.inputC_precio)
        nombre.setText(combo.nombre.toString())
        descripcion.setText(combo.descripcion.toString())
        precio.setText(combo.precio.toString())


        builder.setView(dialogLayout)

        builder.setPositiveButton("Guardar") { dialogInterface, i ->
            if (opcion == 1) {

                combo.nombre = nombre.text.toString()
                combo.descripcion = descripcion.text.toString()
                combo.precio = precio.text.toString().toDouble()
                guardarPlato(combo)
            } else {

                combo.nombre = nombre.text.toString()
                combo.descripcion = descripcion.text.toString()
                combo.precio = precio.text.toString().toDouble()
                editarPlato(combo)
            }
            Toast.makeText(applicationContext, "EditText is " + nombre.text.toString(), Toast.LENGTH_SHORT).show()

        }
        builder.show()
    }
}
