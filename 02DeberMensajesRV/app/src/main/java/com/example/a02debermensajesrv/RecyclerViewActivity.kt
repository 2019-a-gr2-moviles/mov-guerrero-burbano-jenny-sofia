package com.example.a02debermensajesrv

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        btn_regresar.setOnClickListener {
            irIntentRespuesta()
        }
    }
    fun irIntentRespuesta(){
        val intent= Intent(
            this, MainActivity::class.java
        )
        startActivity(intent);
    }
}
