package com.c4rex.brikzapp.stagepreview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import com.c4rex.brikzapp.countdown.CountDownActivity
import com.c4rex.brikzapp.level.LevelModel
import com.c4rex.brikzapp.level.StageModel
import com.c4rex.brikzapp.player.PlayerModel
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.typography

class StagePreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val level = intent.getParcelableExtra<LevelModel>("level")
        val stage = intent.getParcelableExtra<StageModel>("stage")
        val player = intent.getParcelableExtra<PlayerModel>("player")

        setContent {
            BrikzAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    if (stage != null && player != null && level != null) {
                        StagePreviewCard(stage, player, level)
                    }
                }
            }
        }
    }

    private fun intentCountdown(stage:StageModel, player: PlayerModel, level:LevelModel): Intent {
        val intent = Intent(this, CountDownActivity::class.java)
        intent.putExtra("level", level)
        intent.putExtra("stage", stage)
        intent.putExtra("player", player)

        return intent;
    }

    @Composable
    private fun StagePreviewCard(stage: StageModel, player: PlayerModel, level:LevelModel) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                asset = imageResource(id = stage.getRandomBuild()),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(3f)
                    .fillMaxSize()
            )
            Button(
                onClick = { startActivity(intentCountdown(stage, player, level))},
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(0.50f)
                    .fillMaxWidth(),
            ) {
                Text(text = "Start", style = typography.h2)
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure to quit the stage?")
        builder.setMessage("You will lose the stage")

        builder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
        }

        builder.setNegativeButton("No") { dialog, which ->
            moveTaskToBack(false)
        }

        builder.show()
    }
}
