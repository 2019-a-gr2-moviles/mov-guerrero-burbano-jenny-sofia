package com.example.a05proyectorestaurante

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.dialog.*
import kotlinx.android.synthetic.main.layout_menu.*


class MenuActivity : AppCompatActivity() {
    companion object objetoCompartido {
        var url = "http://172.29.49.230:1337"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val opcion = intent.getIntExtra("opcion", -1)
        var listaPlatos = arrayListOf<Plato>()
        if (opcion == 1) {
            btn_nuevoPlato.visibility = View.INVISIBLE
        } else {
            btn_nuevoPlato.setOnClickListener {
                withEditText(Plato(-1, "", "", 0.0), 1)
            }
        }
        //si la opcion es 2 es para adminstrador
        cargar(opcion)


    }

    fun cargar(opcion: Int) {
        val url = "${objetoCompartido.url}/plato"
        var lista = listOf<Plato>()

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
                        var platoParseada = Klaxon().parseArray<Plato>(data)
                        lista = platoParseada!!
                        runOnUiThread {
                            iniciarRecycleView(lista, this, rv_plato, opcion)
                        }

                    }
                }
            }
    }

    fun platos() {
        val url = "${objetoCompartido.url}/plato"
        val (request, response, result) = url
            .httpGet().response()
        result.get()

    }

    fun guardarPlato(plato: Plato) {
        val url = "${objetoCompartido.url}/plato"
        val bodyJson = """
              {
                "nombre": "${plato.nombre}",
                "descripcion" : "${plato.descripcion}",
                "precio": ${plato.precio}
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

                        Log.i("http", "TODO BIIIEN")
                    }
                }
            }

    }

    fun editarPlato(plato: Plato) {
        val url = "${objetoCompartido.url}/plato/${plato.id}"
        val bodyJson = """
  {
    "nombre": "${plato.nombre}",
    "descripcion" : "${plato.descripcion}",
    "precio": ${plato.precio}
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

                        Log.i("http", "TODO BIIIEN")
                    }
                }
            }

    }

    fun iniciarRecycleView(lista: List<Plato>, actividad: MenuActivity, recycler_view: RecyclerView, opcion: Int) {
        val adaptadorPlato = AdaptadorMenu(lista, actividad, recycler_view, opcion)
        rv_plato.adapter = adaptadorPlato
        rv_plato.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_plato.layoutManager = LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }

    fun withEditText(plato: Plato, opcion: Int) {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.dialog, null)

        val nombre = dialogLayout.findViewById<EditText>(R.id.input_nombre)
        val descripcion = dialogLayout.findViewById<EditText>(R.id.input_descripcion)
        val precio = dialogLayout.findViewById<EditText>(R.id.inpt_precio)
        nombre.setText(plato.nombre.toString())
        descripcion.setText(plato.descripcion.toString())
        precio.setText(plato.precio.toString())


        builder.setView(dialogLayout)

        builder.setPositiveButton("Guardar") { dialogInterface, i ->
            if (opcion == 1) {

                plato.nombre = nombre.text.toString()
                plato.descripcion = descripcion.text.toString()
                plato.precio = precio.text.toString().toDouble()
                guardarPlato(plato)
            } else {

                plato.nombre = nombre.text.toString()
                plato.descripcion = descripcion.text.toString()
                plato.precio = precio.text.toString().toDouble()
                editarPlato(plato)
            }
            Toast.makeText(applicationContext, "EditText is " + nombre.text.toString(), Toast.LENGTH_SHORT).show()

        }
        builder.show()
    }
}
