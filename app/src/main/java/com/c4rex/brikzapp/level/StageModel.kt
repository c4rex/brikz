package com.c4rex.brikzapp.level

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.c4rex.brikzapp.R
import java.util.*

data class StageModel(
    var levelId: Int,
    var id:Int,
    var name: String?,
    var timeMilSec: Long,
    var difficulty:Int,
    var completed:Boolean,
    var enabled:Boolean,
    @DrawableRes val imageBricks: Int,
    var imageBrickBuild:List<Int>?
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.createIntArray()?.toList()) {
    }

    fun getImage(): Int {
        if (imageBricks != 0 && !enabled) {
            return R.drawable.padlock
        }
        return imageBricks
    }

     fun getRandomBuild(): Int {
        val i = Random().nextInt(imageBrickBuild!!.size)

        return imageBrickBuild!![i]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(levelId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeLong(timeMilSec)
        parcel.writeInt(difficulty)
        parcel.writeByte(if (completed) 1 else 0)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeInt(imageBricks)
        parcel.writeIntArray(imageBrickBuild?.toIntArray())
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