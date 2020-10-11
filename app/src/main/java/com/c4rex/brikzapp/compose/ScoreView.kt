package com.c4rex.brikzapp.compose

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.c4rex.brikzapp.MainActivity

import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.lead.activity.WinActivity
import com.c4rex.brikzapp.level.SelectLevelActivity
import com.c4rex.brikzapp.player.PlayerModel

@Composable
fun Score(score: Int, player: String, level: String, Stage: String) {
    Stack {

        Image(
                asset = imageResource(id = R.drawable.background_winner_cup),
                modifier = Modifier
                        .background(Color(R.color.teal_200))
                        .fillMaxHeight(), contentScale = ContentScale.FillHeight)
        Column() {
            Spacer(modifier = Modifier.weight(40f))
            Text(text = "Success", modifier = Modifier.fillMaxWidth().weight(8f).background(color = Color(R.color.teal_200)), textAlign = TextAlign.Center, style = TextStyle(color = Color.Yellow, fontStyle = FontStyle.Normal, fontSize = 30.sp, fontWeight = FontWeight.Bold))
            Text(text = "", modifier = Modifier.fillMaxWidth().weight(3f).background(color = Color.Yellow).border(border = BorderStroke(width = 3.dp, color = Color(R.color.teal_700))), textAlign = TextAlign.Center, style = TextStyle(color = Color.Yellow, fontStyle = FontStyle.Normal, fontSize = 30.sp, fontWeight = FontWeight.Bold))

            Text(text = score.toString(), Modifier.fillMaxWidth().weight(8f).background(color = Color(R.color.teal_200)), textAlign = TextAlign.Center, style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 30.sp, color = Color.Yellow))
            Spacer(modifier = Modifier.weight(5f))
            back()
            Spacer(modifier = Modifier.weight(35f))



        }
    }


//


}

@Composable
fun back() {
    val context = ContextAmbient.current
    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
    Button(modifier = Modifier, onClick = {
        ContextCompat.startActivity(
                context,
                Intent(
                        context, MainActivity::class.java
                ),
                null
        )
    }, backgroundColor = Color.Magenta) {
        Text("Continue")
    }
    }

}

@Preview(showBackground = true)
@Composable
fun ScorePreview() {
    var level = "1"
    var player = "player_1"
    var score = 889
    var stage = "1"
    Score(level = level, player = player, score = score, Stage = stage)
}
