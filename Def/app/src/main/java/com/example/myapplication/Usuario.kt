package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Usuario(public var nombre: String,
              var edad:Int,
              var fechaNacimiento: Date,
              var sueldo: Double): Parcelable { //podemos tener propiedades y parámetros con el val le hago que sea parámetros
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readSerializable() as Date, //no entiendo como serializar una clase de tipo date
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeSerializable(fechaNacimiento)
        parcel.writeDouble(sueldo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

}
