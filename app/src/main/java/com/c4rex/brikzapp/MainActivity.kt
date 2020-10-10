package com.c4rex.brikzapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.ui.tooling.preview.Preview
import com.c4rex.brikzapp.lead.activity.LeadActivity
import com.c4rex.brikzapp.lead.activity.WinActivity
import com.c4rex.brikzapp.recognition.activities.RecognitionActivity
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
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Brikz")

                        RenderChallengeBtn(player)
                        Spacer(Modifier.preferredSize(26.dp))

                        RecognitionImmerseButton()
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderChallengeBtn(player:PlayerModel) {
        Button(
            onClick = { startActivity(
                Intent(this@MainActivity, SelectLevelActivity::class.java).apply
                { putExtra("player", player) }
            )}
        ) {
            Text(text = "Challenge", style = typography.h2)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun WinButton() {
    val context = ContextAmbient.current

    Button(onClick = {
        startActivity(
                context,
                Intent(
                        context, WinActivity::class.java
                ),
                null
        )
    },backgroundColor = Color.Red) {
        Text("Win Activity Test")
    }

}
@Composable
fun LeadButton() {
    val context = ContextAmbient.current

    Button(onClick = {
        startActivity(
            context,
            Intent(
                context, LeadActivity::class.java
            ),
            null
        )
    },backgroundColor = Color.Red) {
        Text("laed Activity Test")
    }

}
@Preview(showBackground = true)
@Composable
fun RecognitionImmerseButton() {
    val context = ContextAmbient.current

    Button(onClick = {
            startActivity(
                context,
                Intent(
                    context, RecognitionActivity::class.java
                ),
                null
        )
    },backgroundColor = Color.Red) {
        Text("Wikitude", style = typography.h2)
    }

}


