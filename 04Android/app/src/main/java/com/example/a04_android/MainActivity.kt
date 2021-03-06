package com.example.a04_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_irRecybleView.setOnClickListener {
            irRecycleView()
        }
        btn_intentRespuesta.setOnClickListener {
            irIntentRespuesta()
        }
    }
    fun irRecycleView(){
        val intent= Intent(
            this, RecycleViewActivity::class.java
        )

        startActivity(intent);
        finish()
    }
    fun irIntentRespuesta(){
        val intent= Intent(
            this, IntentRespuestaActivity::class.java
        )

        startActivity(intent);
        finish()
    }
    fun irResultadoPropio(){
        val intent= Intent(
            this, IntentRespuestaActivity::class.java
        )

        startActivity(intent);
        finish()
    }
}
