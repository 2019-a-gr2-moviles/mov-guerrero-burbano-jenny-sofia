package com.example.examendef

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import android.R.attr.name
import android.R.id


class Estudiante (public var nombres:String, var apellidos:String, var fechaNacimiento: String,
                 var semestreActual:Int, var graduado: Boolean, var id: Int ){



    override fun toString(): String {
        return this.nombres
    }

}