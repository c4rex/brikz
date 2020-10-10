package com.c4rex.brikzapp.compose

import androidx.compose.foundation.Box
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview


import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.player.PlayerModel

@Composable
fun Score(score:Int,player:String,level:String,Stage:String) {

        Image(
                asset = imageResource(id =R.drawable.background  ),
                modifier = Modifier
                        .background(Color(R.color.teal_200))
                        .fillMaxHeight(),contentScale = ContentScale.FillHeight)
    Column() {
        Text(text = "Success",modifier = Modifier.fillMaxWidth().weight(10f),textAlign = TextAlign.Center,style = TextStyle(fontStyle = FontStyle.Normal,fontSize = 30.sp,fontWeight = FontWeight.Bold))
        Text(text = score.toString(), Modifier.fillMaxWidth().weight(5f),textAlign = TextAlign.Center,style = TextStyle(fontStyle = FontStyle.Italic,fontSize = 30.sp))

    }






//



}

@Preview(showBackground = true)
@Composable
fun ScorePreview() {
    var level = "1"
    var player = "player_1"
    var score = 889
    var stage = "1"
    Score(level = level ,player = player, score = score, Stage = stage )
}
