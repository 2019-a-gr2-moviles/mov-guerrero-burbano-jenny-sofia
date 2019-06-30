package com.example.a05proyectorestaurante

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.layout_menu.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var listaPlatos = arrayListOf<Plato>()
        listaPlatos.add(Plato("Oja", "rico", 1.2))
        listaPlatos.add(Plato("Oja22", "ricoa", 3.2))

        var lista= listOf<Plato>()

        val url = "http://192.168.200.5:1337/plato"
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
                        lista=platoParseada!!
                        //iniciarRecycleView(platoParseada!!, this, rv_plato)
                        runOnUiThread {
                            iniciarRecycleView(lista, this, rv_plato)
                        }


                        platoParseada?.forEach{
                            Log.i("http", it.nombre)
                        }
                    }
                }
            }


    }
    fun iniciarRecycleView(lista: List<Plato>, actividad:MenuActivity,recycler_view: RecyclerView){
        val adaptadorPlato= AdaptadorMenu(lista, actividad, recycler_view)
        rv_plato.adapter=adaptadorPlato
        rv_plato.itemAnimator = DefaultItemAnimator()
        //Nos falta el layout manager
        rv_plato.layoutManager= LinearLayoutManager(this)
        adaptadorPlato.notifyDataSetChanged()
    }
}
