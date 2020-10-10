package com.c4rex.brikzapp.player

import android.os.Parcel
import android.os.Parcelable

class StageAdvanceModel(var id:Int, var completed:Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeByte(if (completed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StageAdvanceModel> {
        override fun createFromParcel(parcel: Parcel): StageAdvanceModel {
            return StageAdvanceModel(parcel)
        }

        override fun newArray(size: Int): Array<StageAdvanceModel?> {
            return arrayOfNulls(size)
        }
    }
}
