package com.c4rex.brikzapp.level

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.player.PlayerModel
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.shapes
import com.c4rex.brikzapp.ui.typography

class SelectLevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val player = intent.getParcelableExtra<PlayerModel>("player")
        setContent {
            BrikzAppTheme {
                Image(
                    asset = imageResource(id = R.drawable.background_default_resize),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Surface(color = Color.Transparent) {
                    if (player != null) {
                        populateLevels(player)
                    }
                }
            }
        }
    }

    @Composable
    private fun populateLevels(player: PlayerModel) {
        setUpLevels(levels = LevelMockSource.getLevels(player), player = player) {}
    }


    @Composable
    private fun setUpLevels(
        levels: List<LevelModel>,
        player: PlayerModel,
        onSelected: (LevelModel) -> Unit
    ) {
        ScrollableColumn(
            Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.preferredSize(26.dp))
            Text(
                text = "Topics",
                style = typography.h2,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 46.sp
            )
            Spacer(Modifier.preferredSize(26.dp))
            levels.forEach {
                LevelCard(it, player) { onSelected(it) }
            }
        }
    }

    private fun levelClick(level: LevelModel, player:PlayerModel) {
        val intent = Intent(this, SelectStageActivity::class.java)
        intent.putExtra("level", level)
        intent.putExtra("player", player)
        Log.v("awMe3", level.toString())
        if (level.enabled) {
            startActivity(intent)
        }
    }

    @Composable
    private fun LevelCard(
        level: LevelModel,
        player: PlayerModel,
        onClick: () -> Unit
    ) {
        val padding = 16.dp
        Column(
            Modifier
                .fillMaxWidth()
                .padding(padding)
                .clickable(onClick = onClick)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { levelClick(level, player) }),
                shape = shapes.medium,
                elevation = 4.dp,
                backgroundColor = if (level.enabled) colors.primary else Color(0xFFBDBDBD)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(36.dp)
                ) {
                    Text(
                        text = level.id.toString(),
                        style = typography.h1,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = if (level.enabled) colors.onPrimary else Color(0xFF9E9E9E)
                    )
                    Spacer(Modifier.preferredSize(6.dp))
                    level.name?.let {
                        Text(
                            text = it,
                            style = typography.h2,
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            color = if (level.enabled) colors.onPrimary else Color(0xFF9E9E9E)
                        )
                    }
                    Spacer(Modifier.preferredSize(6.dp))
                }
            }
        }
    }
}
