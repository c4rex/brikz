package com.c4rex.brikzapp.lead.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.c4rex.brikzapp.compose.LeaderBoard
import com.c4rex.brikzapp.compose.Score
import com.c4rex.brikzapp.dao.ScoreRepos
import com.c4rex.brikzapp.lead.activity.ui.BrikzAppTheme
import com.c4rex.brikzapp.level.LevelModel
import com.c4rex.brikzapp.level.StageModel
import com.c4rex.brikzapp.player.PlayerModel

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        val stage = intent.getParcelableExtra<StageModel>("stage")
        val player = intent.getParcelableExtra<PlayerModel>("player")
        val level = intent.getParcelableExtra<LevelModel>("level")
        val score = (500..1000).random()

        if (stage != null && player != null && level != null) {
            if (player.updateStageCompletedStatus(stage.levelId, stage.id, true)) {
                // Enable next lvl
                val nextStageId = stage.id + 1
                level.enableStage(nextStageId, true)
            }
        }

        setContent {
            BrikzAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (level != null && player != null && stage != null) {
                        Score(level = level , player = player, score = score, stage = stage)
                    }
                }
            }
        }
    }
    override fun onBackPressed() {

    }
}


