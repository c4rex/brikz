package com.c4rex.brikzapp.level

import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.player.PlayerMockSource
import com.c4rex.brikzapp.player.PlayerModel

object LevelMockSource {
    private fun getStages(player:PlayerModel, levelId:Int, topic:String):ArrayList<StageModel> {
        return ArrayList<StageModel>().apply {
            add(StageModel(1, 101, "$topic 1", 120000, 1, player.getStageCompletedStatusById(levelId, 101), enabled = true, R.drawable.bricks_level_1_stage_1, arrayListOf(R.drawable.build_level_1_stage_1_opt_1, R.drawable.build_level_1_stage_1_opt_2, R.drawable.build_level_1_stage_1_opt_3)))
            add(StageModel(1, 102, "$topic 2", 120000, 1, player.getStageCompletedStatusById(levelId, 102), player.getStageCompletedStatusById(levelId, 101), R.drawable.bricks_level_1_stage_2, arrayListOf(R.drawable.build_level_1_stage_2_opt_1, R.drawable.build_level_1_stage_2_opt_2, R.drawable.build_level_1_stage_2_opt_3)))
        }
    }

    fun getLevels(player:PlayerModel):List<LevelModel> {
        return ArrayList<LevelModel>().apply {
            add(LevelModel(1, "Colors", player.getLevelCompletedStatusById(1), enabled = true, getStages(player, 1, "Colors")))
            add(LevelModel(2, "Shapes", player.getLevelCompletedStatusById(2), player.getLevelCompletedStatusById(1), getStages(player, 2, "Shapes")))
            add(LevelModel(3, "Animals", player.getLevelCompletedStatusById(3), player.getLevelCompletedStatusById(2), getStages(player, 3, "Animals")))
        }
    }
}
