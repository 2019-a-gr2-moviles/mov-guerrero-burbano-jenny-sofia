package com.example.a05proyectorestaurante

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class GestionComboActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_combo)
        val url = "http://192.168.200.5:1337/historialComboPlato"
        var lista= listOf<Historial>()

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
                        var platoParseada = Klaxon().parseArray<Historial>(data)
                        lista=platoParseada!!
                        lista.forEach{

                        }

                    }
                }
            }
    }
}
