package com.example.examendef
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Materia (var id:Int,var nombre: String, var codigo:String, var descripcion:String,
               var activo:Boolean, var fechaCreacion:String,
               var numeroHorasPorSemana:Int, var estudianteId:Estudiante, var longitud: String, var latitud: String) {

    override fun toString(): String {
        return this.nombre
    }

}