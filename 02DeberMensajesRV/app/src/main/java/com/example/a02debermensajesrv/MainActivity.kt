package com.example.a02debermensajesrv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_mensajes.setOnClickListener {
            irIntentRespuesta()
        }
        btn_map.setOnClickListener {
            irMaps()
        }
    }
    fun irIntentRespuesta(){
        val intent= Intent(
            this, ConexionHTTPClient::class.java
        )
        startActivity(intent);
    }
    fun irMaps(){
        val intent= Intent(
            this, MapsActivity::class.java
        )
        startActivity(intent);
    }
}
