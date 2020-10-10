package com.c4rex.brikzapp.level

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.player.PlayerModel
import com.c4rex.brikzapp.stagepreview.StagePreviewActivity
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.shapes
import com.c4rex.brikzapp.ui.typography

class SelectStageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val level = intent.getParcelableExtra<LevelModel>("level")
        val player = intent.getParcelableExtra<PlayerModel>("player")

        setContent {
            BrikzAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    if (level != null && player != null) {
                        setUpStages(stages = level.stages, player = player) {}
                    }
                }
            }
        }
    }

    @Composable
    private fun setUpStages(
        stages: List<StageModel>,
        player: PlayerModel,
        onSelected: (StageModel) -> Unit
    ) {
        ScrollableRow(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            stages.forEach {
                LevelCard(it, player) { onSelected(it) }
            }
        }
    }

    private fun intentPreviewStage(stage:StageModel, player:PlayerModel): Intent {
        val intent = Intent(this, StagePreviewActivity::class.java)
        intent.putExtra("stage", stage)
        intent.putExtra("player", player)

        return intent;
    }

    private fun intentLeaderBoard(stage:StageModel, player:PlayerModel): Intent {
        val intent = Intent(this, SelectStageActivity::class.java)
        intent.putExtra("stage", stage)
        intent.putExtra("player", player)

        return intent;
    }

    @Composable
    private fun LevelCard(
        stage: StageModel,
        player: PlayerModel,
        onClick: () -> Unit
    ) {
        val padding = 20.dp
        val dm = resources.displayMetrics
        val lvlWidth = dm.widthPixels/dm.density
        Column(
            Modifier
                .width(lvlWidth.dp)
                .padding(padding)
                .clickable(onClick = onClick)
        ) {
            stage.name?.let {
                Text(
                    text = it,
                    style = typography.h2,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp
                )
            }
            Spacer(Modifier.preferredSize(padding))
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = shapes.medium,
                elevation = 4.dp
            ) {
                Column {
                    Image(
                        asset = imageResource(id = stage.getImage()),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .weight(4f)
                            .fillMaxSize()
                    )
                    Row(
                        modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .weight(1f)
                                .fillMaxWidth(),
                    ) {
                        Button(
                                onClick = { startActivity(intentPreviewStage(stage, player))},
                                modifier = Modifier
                                        .weight(2f)
                                        .fillMaxHeight(),
                                enabled = stage.enabled
                        ) {
                            Text(text = "Let's Build!", style = typography.h2)
                        }
                        Button(
                                onClick = {},
                                modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight(),
                                enabled = stage.enabled
                        ) {
                            Image(
                                    asset = imageResource(id = R.drawable.winner_cup_white)
                            )
                        }
                    }
                }
            }
        }
    }
}
