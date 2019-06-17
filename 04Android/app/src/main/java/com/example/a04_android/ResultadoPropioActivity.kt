package com.example.a04_android

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado_propio.*

class ResultadoPropioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_propio)
        btn_devolverRespuesta.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta() {
        val nombre= "Sofia"
        val edad= 22
        val intentRespuesta = Intent(

        )
        intentRespuesta.putExtra("NombreUsuario", nombre)
        intentRespuesta.putExtra("edadUsuario", edad)
        this.setResult(
            RESULT_OK, //PODEMOS ENVIAR TAMBIEN RESULT_CANCELLED
            intentRespuesta
        )
        this.finish()
    }

}
