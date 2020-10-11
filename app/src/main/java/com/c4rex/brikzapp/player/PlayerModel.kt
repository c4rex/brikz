package com.c4rex.brikzapp.player

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.c4rex.brikzapp.R

class PlayerModel(
    var id: Int,
    var name: String?,
    var email: String?,
    var gender: String?,
    @DrawableRes val image: Int,
    var levelAdvance:ArrayList<LevelAdvanceModel>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        arrayListOf<LevelAdvanceModel>().apply {
            parcel.readList(this, LevelAdvanceModel::class.java.classLoader)
        }) {
    }

    fun getProfilePic(): Int {
        if (image != 0) {
            return image
        }

        return getDefaultProfilePic()
    }

    fun getStageCompletedStatusById(levelId:Int, stageId:Int):Boolean {
        var completed = false

        loop@ for (level in levelAdvance) {
            if (level.id == levelId) {
                for (stage in level.stageAdvance) {
                    if (stage.id == stageId) {
                        completed = stage.completed
                        break@loop
                    }
                }
            }
        }

        return completed
    }

    fun getLevelCompletedStatusById(levelId:Int):Boolean {
        var completed = true

        loop@ for (level in levelAdvance) {
            if (level.id == levelId) {
                for (stage in level.stageAdvance) {
                    // If some stage not completed, Level not completed
                    if (!stage.completed) {
                        completed = false
                        break@loop
                    }
                }
            }
        }

        return completed
    }

    fun updateStageCompletedStatus(levelId:Int, stageId:Int, newStatus:Boolean):Boolean {
        var updated = true

        loop@ for (level in levelAdvance) {
            if (level.id == levelId) {
                for (stage in level.stageAdvance) {
                    if (stage.id == stageId) {
                        stage.completed = newStatus
                        updated = true
                        break@loop
                    }
                }
            }
        }

        return updated
    }

    private fun getDefaultProfilePic(): Int {
        return when (this.gender) {
            "male" -> R.drawable.default_male_pic
            "female" -> R.drawable.default_female_pic
            else -> R.drawable.default_female_pic
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(gender)
        parcel.writeInt(image)
        parcel.writeList(levelAdvance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerModel> {
        override fun createFromParcel(parcel: Parcel): PlayerModel {
            return PlayerModel(parcel)
        }

        override fun newArray(size: Int): Array<PlayerModel?> {
            return arrayOfNulls(size)
        }
    }
}