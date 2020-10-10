package com.c4rex.brikzapp.level

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.c4rex.brikzapp.R

data class StageModel(
    var id: Int,
    var stageId:Int,
    var name: String?,
    var timeMilSec:Int,
    var difficulty:Int,
    var completed:Boolean,
    var enabled:Boolean,
    @DrawableRes val imageBricks: Int,
    @DrawableRes val imageBricksBuild: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    fun getImage(): Int {
        return R.drawable.padlock
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(stageId)
        parcel.writeString(name)
        parcel.writeInt(timeMilSec)
        parcel.writeInt(difficulty)
        parcel.writeByte(if (completed) 1 else 0)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeInt(imageBricks)
        parcel.writeInt(imageBricksBuild)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StageModel> {
        override fun createFromParcel(parcel: Parcel): StageModel {
            return StageModel(parcel)
        }

        override fun newArray(size: Int): Array<StageModel?> {
            return arrayOfNulls(size)
        }
    }
}