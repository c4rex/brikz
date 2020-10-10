package com.c4rex.brikzapp.level

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.shapes
import com.c4rex.brikzapp.ui.typography

class SelectLevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            BrikzAppTheme {
                Surface(color = colors.background) {
                    populateLevels()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun populateLevels() {
        setUpLevels(levels = LevelMockSource.getLevels()) {}
    }


    @Composable
    private fun setUpLevels(
        levels: List<LevelModel>,
        onSelected: (LevelModel) -> Unit
    ) {
        ScrollableColumn(
            Modifier.fillMaxSize()
        ) {
            Text(
                text = "Topics",
                style = typography.h2,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(Modifier.preferredSize(26.dp))
            levels.forEach {
                LevelCard(it) { onSelected(it) }
            }
        }
    }

    private fun levelClick(level:LevelModel) {
        if (level.enabled) {
            startActivity(
                    Intent(this@SelectLevelActivity, SelectStageActivity::class.java).apply
                    { putExtra("level", level) }
            )
        }
    }

    @Composable
    private fun LevelCard(
        level: LevelModel,
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
                    .clickable(onClick = {levelClick(level)}),
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
