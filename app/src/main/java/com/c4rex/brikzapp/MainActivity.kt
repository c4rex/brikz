package com.c4rex.brikzapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.c4rex.brikzapp.level.SelectLevelActivity
import com.c4rex.brikzapp.player.PlayerMockSource
import com.c4rex.brikzapp.player.PlayerModel
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.typography

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = PlayerMockSource.getArtistMock()
        setContent {
            BrikzAppTheme {
                Image(
                    asset = imageResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                // A surface container using the 'background' color from the theme
                Surface(color = Color.Transparent) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RenderChallengeBtn(player)
                        Spacer(Modifier.preferredSize(26.dp))
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderChallengeBtn(player:PlayerModel) {
        Button(
            contentColor = Color.White,
            onClick = { startActivity(
                Intent(this@MainActivity, SelectLevelActivity::class.java).apply
                { putExtra("player", player) }
            )
            }
        ) {
            Text(text = "Challenge", style = typography.h2)
        }
    }
}