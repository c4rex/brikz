package com.c4rex.brikzapp.level

import android.os.Parcel
import android.os.Parcelable

data class LevelModel(
    var id: Int,
    var name: String?,
    var completed:Boolean,
    var enabled:Boolean,
    var stages:ArrayList<StageModel>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        arrayListOf<StageModel>().apply {
            parcel.readList(this, StageModel::class.java.classLoader)
        }
    ) {
    }

    fun enableStage(stageId:Int, newValue:Boolean):Boolean {
        var updated = false

        for (stage in stages) {
            if (stage.id == stageId) {
                stage.enabled = newValue
                updated = true
                break
            }
        }

        return updated
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeByte(if (completed) 1 else 0)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeList(stages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LevelModel> {
        override fun createFromParcel(parcel: Parcel): LevelModel {
            return LevelModel(parcel)
        }

        override fun newArray(size: Int): Array<LevelModel?> {
            return arrayOfNulls(size)
        }
    }
}
