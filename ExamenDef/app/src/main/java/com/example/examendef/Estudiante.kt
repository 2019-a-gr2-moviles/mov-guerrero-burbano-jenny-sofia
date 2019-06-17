package com.example.examendef

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import android.R.attr.name
import android.R.id


class Estudiante (public var nombres:String, var apellidos:String, var fechaNacimiento: Date,
                 var semestreActual:Int, var graduado: Boolean, var id: Int ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readSerializable() as Date,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeInt(semestreActual)
        parcel.writeSerializable(fechaNacimiento)
        parcel.writeByte(if (graduado) 1 else 0)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Estudiante> {
        override fun createFromParcel(parcel: Parcel): Estudiante {
            return Estudiante(parcel)
        }

        override fun newArray(size: Int): Array<Estudiante?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return this.nombres
    }

}