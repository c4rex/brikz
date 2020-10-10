package com.c4rex.brikzapp.player

import android.os.Parcel
import android.os.Parcelable

class LevelAdvanceModel(
    var id:Int,
    var stageAdvance:ArrayList<StageAdvanceModel>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        arrayListOf<StageAdvanceModel>().apply {
            parcel.readList(this, StageAdvanceModel::class.java.classLoader)
        }) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeList(stageAdvance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LevelAdvanceModel> {
        override fun createFromParcel(parcel: Parcel): LevelAdvanceModel {
            return LevelAdvanceModel(parcel)
        }

        override fun newArray(size: Int): Array<LevelAdvanceModel?> {
            return arrayOfNulls(size)
        }
    }
}
