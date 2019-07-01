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
import kotlinx.android.synthetic.main.activity_menu_combo.*

class MenuComboActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_combo)
        cargar(0)
    }
    fun cargar(opcion: Int){
        val url = "http://192.168.200.5:1337/combo"
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
}
