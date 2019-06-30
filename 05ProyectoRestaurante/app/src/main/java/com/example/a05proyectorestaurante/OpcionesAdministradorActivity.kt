package com.example.a05proyectorestaurante

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_opciones_administrador.*

class OpcionesAdministradorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones_administrador)
        btn_adminPlato.setOnClickListener {
            irActividadMenu(2)
        }
    }
    fun irActividadMenu(opcion: Int){
        val intent= Intent(
            this, MenuActivity::class.java
        )
        intent.putExtra("opcion", opcion )
        startActivity(intent);
    }
}
