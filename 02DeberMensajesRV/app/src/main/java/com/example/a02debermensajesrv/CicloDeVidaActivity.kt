package com.example.a02debermensajesrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_de_vida.*

class CicloDeVidaActivity : AppCompatActivity() {
    var contador=0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_de_vida)
        Log.i("ciclo-vida", "on create")
        btn_contador.setOnClickListener {
            aumentarContador()
        }
    }
    fun aumentarContador(){
        Log.i("cont", contador.toString())
        txt_cont.text= contador.toString()
        contador++
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.i("ciclo-vida", "onSaveInstanceState")
        outState?.run {
            putInt("contadorGuardado", contador)
        }
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("ciclo-vida", "onRestoreInstanceState")
        val contadorRecuperado=savedInstanceState?.getInt("contadoGuardado")
        if(contadorRecuperado!=null){
            this.contador= contadorRecuperado
            txt_cont.text= this.contador.toString()
        }
    }

    override fun onStart() {
        Log.i("ciclo-vida", "on start")
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida", "on resume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida", "on restart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida", "on pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida", "on stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida", "on destroy")
    }
}
