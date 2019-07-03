package com.example.a05proyectorestaurante

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_gestion_combo.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu_combo.*

class GestionComboActivity : AppCompatActivity() {
    companion object objetoCompartido {
        var url = "http://172.29.49.230:1337"
        var idComboCO:Int=-1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_combo)
        val idCombo = intent.getIntExtra("indice", -1)
        idComboCO=idCombo
        var lista = ArrayList<Plato>()

        cargarPlatos(idCombo)

    }

    fun cargarPlatos(indice: Int) {
        val url = "${MenuActivity.objetoCompartido.url}/Plato"
        val urlSelec = "${MenuActivity.objetoCompartido.url}/historialComboPlato/?idCombo=${indice}"

        var lista = listOf<Plato>()
        var listaSelec = listOf<Historial>()
        var platosSelec = ArrayList<PlatoAuxiliar>()
        var platosDisponibles = ArrayList<Plato>()

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
                        urlSelec
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
                                        var historialParseada = Klaxon().parseArray<Historial>(data)
                                        listaSelec = historialParseada!!
                                        lista.forEach {
                                            platosDisponibles.add(it)
                                        }
                                        listaSelec.forEach {
                                            Log.i("http", "LISTA OBJETOS !!!! ${it.idPlato.nombre}")
                                            var platoAux:PlatoAuxiliar=PlatoAuxiliar(it.idPlato.id, it.idPlato.nombre, it.idPlato.descripcion, it.idPlato.precio, it.id)

                                            platosSelec.add(platoAux)
                                            var id=it.idPlato.id
                                            var x= platosDisponibles.find {

                                               it.id==id
                                            }
                                            platosDisponibles.remove(x)
                                            //platosDisponibles.removeAt(index)


                                        }

                                        runOnUiThread {
                                            iniciarRecycleViewDisponibles(platosDisponibles, this, rv_disponibles, 1)
                                            iniciarRecycleViewSeleccionados(platosSelec, this, rv_seleccionados, 1)
                                        }


                                    }
                                }
                            }
                    }
                }
            }
    }
    fun quitar(idRelacion: Int){
        val url = "${MenuActivity.url}/historialComboPlato?id=${idRelacion}"
        var lista = listOf<Plato>()

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
                           cargarPlatos(idComboCO)
                        }

                    }
                }
            }
    }
    fun seleccionar(idPlato:Int){
        val url = "${MenuActivity.url}/historialComboPlato"
        val bodyJson = """
              {
                "idPlato": ${idPlato},
                "idCombo" : ${idComboCO}

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
                        Log.i("http", "rEQUEST!!!!!!!!!1TODO BIIIIIIIIIIIIIIIIIEN: ${request}")
                        runOnUiThread {
                            cargarPlatos(idComboCO)
                        }
                    }
                }
            }

    }

    fun iniciarRecycleViewDisponibles(
        lista: List<Plato>,
        actividad: GestionComboActivity,
        recycler_view: RecyclerView,
        opcion: Int
    ) {
        val adaptadorPlato = AdaptadorDisponibles(lista, actividad, recycler_view, opcion)
        rv_disponibles.adapter = adaptadorPlato
        rv_disponibles.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_disponibles.layoutManager = LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }

    fun iniciarRecycleViewSeleccionados(
        lista: List<PlatoAuxiliar>,
        actividad: GestionComboActivity,
        recycler_view: RecyclerView,
        opcion: Int
    ) {
        val adaptadorPlato = AdaptadorSeleccionados(lista, actividad, recycler_view, opcion)
        rv_seleccionados.adapter = adaptadorPlato
        rv_seleccionados.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_seleccionados.layoutManager = LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }

}
