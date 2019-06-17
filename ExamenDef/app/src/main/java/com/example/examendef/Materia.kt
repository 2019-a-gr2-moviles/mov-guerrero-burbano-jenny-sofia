package com.example.examendef
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Materia (public var id:Int,var nombre: String, var codigo:String, var descripcion:String, var activo:Boolean, var fechaCreacion:Date,
               var numeroHoras:Int, var estudianteId:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readSerializable() as Date,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(codigo)
        parcel.writeString(descripcion)
        parcel.writeByte(if (activo) 1 else 0)
        parcel.writeSerializable(fechaCreacion)
        parcel.writeInt(numeroHoras)
        parcel.writeInt(estudianteId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Materia> {
        override fun createFromParcel(parcel: Parcel): Materia {
            return Materia(parcel)
        }

        override fun newArray(size: Int): Array<Materia?> {
            return arrayOfNulls(size)
        }
    }
    override fun toString(): String {
        return this.nombre
    }

}