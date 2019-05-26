package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class Mascta(val nombre: String, val duenio: Usuario):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Usuario::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeParcelable(duenio, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mascta> {
        override fun createFromParcel(parcel: Parcel): Mascta {
            return Mascta(parcel)
        }

        override fun newArray(size: Int): Array<Mascta?> {
            return arrayOfNulls(size)
        }
    }
}