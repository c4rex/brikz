package com.c4rex.brikzapp.level

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.shapes
import com.c4rex.brikzapp.ui.typography

class SelectStageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val level = intent.getParcelableExtra<LevelModel>("level")

        setContent {
            BrikzAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    if (level != null) {
                        setUpStages(stages = level.stages) {}
                    }
                }
            }
        }
    }

    @Composable
    private fun setUpStages(
        stages: List<StageModel>,
        onSelected: (StageModel) -> Unit
    ) {
        ScrollableRow(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            stages.forEach {
                LevelCard(it) { onSelected(it) }
            }
        }
    }

    @Composable
    private fun LevelCard(
        stage: StageModel,
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
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
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
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .weight(3f)
                    )
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .weight(1f)
                            .fillMaxWidth(),
                        enabled = stage.enabled
                    ) {
                        Text(text = "Let's Build!", style = typography.h2)
                    }
                }
            }
        }
    }
}
