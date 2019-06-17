package com.example.a02debermensajesrv

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_mensajes.setOnClickListener {
            irIntentRespuesta()
        }
    }
    fun irIntentRespuesta(){
        val intent= Intent(
            this, RecyclerViewActivity::class.java
        )
        startActivity(intent);
    }
}
