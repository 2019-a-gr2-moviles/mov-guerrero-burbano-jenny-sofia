package com.example.examendef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_crear_estudiante.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object objetoCompartido
    {
        var nombre:String="prueba"
        var url = "http://192.168.43.38:1337";
        var contadorEstudiateId:Int=1
        var contadorMateriaId:Int=1
        var dbEstudiante = listOf<Estudiante>()
        var dbMateria = listOf<Materia>()

        fun crearaux(){


        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_entrar.setOnClickListener{
            objetoCompartido.crearaux()
            enviarNombre()
        }
        button2.setOnClickListener {
            mapas()
        }


    }
    fun enviarNombre(){

        objetoCompartido.nombre=input_Nombre.text.toString();
        val intent= Intent(
            this, MenuEstudiante::class.java
        )
        startActivity(intent);

    }
    fun mapas(){


        val intent= Intent(
            this, MapsActivity::class.java
        )
        startActivity(intent);
    }
  
}
