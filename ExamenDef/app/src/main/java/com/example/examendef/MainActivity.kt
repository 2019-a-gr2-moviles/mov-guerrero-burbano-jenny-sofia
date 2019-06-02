package com.example.examendef

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_crear_estudiante.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object objetoCompartido
    {
        var nombre:String="prueba"
        var contadorEstudiateId:Int=1
        var contadorMateriaId:Int=1
        var dbEstudiante = ArrayList<Estudiante>()



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_entrar.setOnClickListener{
            enviarNombre()
        }
        val estudiante= Estudiante(
            nombres = "SOFIA",
            apellidos = "GUERRERO",
            fechaNacimiento = Date(),
            semestreActual = 1,
            graduado = true,
            id = 0


        )
        dbEstudiante.add(estudiante)


    }
    fun enviarNombre(){

        objetoCompartido.nombre=input_Nombre.text.toString();
        val intent= Intent(
            this, MenuEstudiante::class.java
        )
        startActivity(intent);
    }
  
}
