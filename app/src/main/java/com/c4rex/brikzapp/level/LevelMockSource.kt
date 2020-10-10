package com.c4rex.brikzapp.level

import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.player.PlayerMockSource
import com.c4rex.brikzapp.player.PlayerModel

object LevelMockSource {
    private fun getStages(player:PlayerModel, levelId:Int, topic:String):ArrayList<StageModel> {
        return ArrayList<StageModel>().apply {
            add(StageModel(1, 1, "$topic 1", 120000, 1, player.getStageCompletedStatusById(levelId, 1), enabled = true, R.drawable.default_male_pic, R.drawable.default_male_pic))
            add(StageModel(1, 2, "$topic 2", 120000, 1, player.getStageCompletedStatusById(levelId, 2), player.getStageCompletedStatusById(levelId, 1), R.drawable.default_male_pic, R.drawable.default_male_pic))
            add(StageModel(1, 3, "$topic 3", 120000, 1, player.getStageCompletedStatusById(levelId, 3), player.getStageCompletedStatusById(levelId, 2), R.drawable.default_male_pic, R.drawable.default_male_pic))
            add(StageModel(1, 4, "$topic 4", 120000, 1, player.getStageCompletedStatusById(levelId, 4), player.getStageCompletedStatusById(levelId, 3), R.drawable.default_male_pic, R.drawable.default_male_pic))
        }
    }

    fun getLevels():List<LevelModel> {
        // Get Player
        val player = PlayerMockSource.getArtistMock()

        return ArrayList<LevelModel>().apply {
            add(LevelModel(1, "Colors", player.getLevelCompletedStatusById(1), enabled = true, getStages(player, 1, "Colors")))
            add(LevelModel(2, "Shapes", player.getLevelCompletedStatusById(2), player.getLevelCompletedStatusById(1), getStages(player, 2, "Shapes")))
            add(LevelModel(3, "Animals", player.getLevelCompletedStatusById(3), player.getLevelCompletedStatusById(2), getStages(player, 3, "Animals")))
        }
    }
}
