package com.example.a04_android

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_respuesta.*

class IntentRespuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_respuesta)
        btn_enviarIntentRespuesta.setOnClickListener {
            enviarIntentConRespuesta()
        }
        btn_enviarRespuestaPropia.setOnClickListener {
            enviarIntentConRespuestaPropia()
        }
    }
    fun enviarIntentConRespuesta(){
        val intentConRespuesta= Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        this.startActivityForResult(intentConRespuesta, 304)
    }
    fun enviarIntentConRespuestaPropia(){
        val intentPropio= Intent(
            this,ResultadoPropioActivity::class.java
        )
        this.startActivityForResult(intentPropio, 305)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode){
            RESULT_OK ->{
                Log.i("intent-respuesta", "lo logramos ${Activity.RESULT_OK}")
                when(requestCode){
                    304->{
                        Log.i("intent-respuesta", "contacto llego")
                        var uri= data?.data

                        var cursor = contentResolver.query(uri, null,null,null, null)
                        cursor.moveToFirst()
                        val indiceTelefono= cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono= cursor.getString(indiceTelefono)
                        Log.i("intent-respuesta", "El telefono es $telefono")
                    }
                    305->{
                        val nombre=data?.getStringExtra("NombreUsuario")
                        val edad= data?.getIntExtra("edadUsuario",0)
                        Log.i("intent-respuesta", "Nombre: $nombre Edad: $edad")
                    }
                }
            }
            RESULT_CANCELED->{
                Log.i("intent-respuesta", "No escogio :(")
            }
        }
    }
}
