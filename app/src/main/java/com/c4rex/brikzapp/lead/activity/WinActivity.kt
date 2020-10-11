package com.c4rex.brikzapp.lead.activity

import android.os.Bundle
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

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var level = "1"
        var player = "player_1"
        var score = 889
        var stage = "1"

        setContent {
            BrikzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                        Score(level = level ,player = player, score = score, Stage = stage)
                }
            }
        }
    }
}

