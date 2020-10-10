package com.c4rex.brikzapp.player

import androidx.annotation.DrawableRes
import com.c4rex.brikzapp.R

class PlayerModel(
    var id: Int,
    var name:String,
    var email:String,
    var gender:String,
    @DrawableRes val image: Int,
    var levelAdvance:ArrayList<LevelAdvanceModel>
) {
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

    private fun getDefaultProfilePic(): Int {
        return when (this.gender) {
            "male" -> R.drawable.default_male_pic
            "female" -> R.drawable.default_female_pic
            else -> R.drawable.default_female_pic
        }
    }
}