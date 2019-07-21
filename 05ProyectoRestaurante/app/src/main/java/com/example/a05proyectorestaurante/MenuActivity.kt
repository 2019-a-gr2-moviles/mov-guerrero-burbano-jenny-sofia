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
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {
    companion object objetoCompartido {
        var url = "http://172.29.64.87:1337"
        var listaPlatos = listOf<Plato>()
        var opcion:Int =-1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        this.supportActionBar?.hide()
        opcion = intent.getIntExtra("opcion", -1)
        var listaPlatos = arrayListOf<Plato>()
        btnBuscarPlato.setOnClickListener {
            buscar()
        }
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
    fun buscar(){
        var aux=listaPlatos.filter {
            it.nombre.contains(input_busquedaPlato.text.toString())

        }
        iniciarRecycleView(aux, this, rv_plato, opcion)

    }

    fun cargar(opcion: Int) {
        val url = "${objetoCompartido.url}/plato"
        var lista = listOf<Plato>()
        var listaPlatos2 = listOf<Plato>()
        iniciarRecycleView(listaPlatos2, this, rv_plato, opcion)

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
                        listaPlatos=lista
                        runOnUiThread {
                            iniciarRecycleView(lista, this, rv_plato, opcion)
                        }

                    }
                }
            }
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
                        runOnUiThread {
                            cargar(opcion)
                        }

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
                        runOnUiThread {
                            cargar(opcion)
                        }

                        Log.i("http", "TODO BIIIEN")
                    }
                }
            }

    }
    fun eliminarPlato(idPlato: Int){
        val url = "${MenuActivity.url}/plato?id=${idPlato}"
        val urlRelacion = "${MenuActivity.url}/historialComboPlato?idPlato=${idPlato}"

        var lista = listOf<Plato>()
        urlRelacion
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        var platoParseada = Klaxon().parseArray<Historial>(data)
                        var x=platoParseada
                        if(platoParseada!!.size!=0){
                            runOnUiThread {
                                Toast.makeText(applicationContext, "NO SE PUEDE ELIMINAR ", Toast.LENGTH_SHORT).show()
                            }
                        }
                        if(platoParseada!!.size==0){
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
