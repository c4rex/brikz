package com.c4rex.brikzapp.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
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

import androidx.ui.tooling.preview.Preview

import com.c4rex.brikzapp.model.RankItem
import com.c4rex.brikzapp.R

val colors: Array<Color> = arrayOf(Color.LightGray,Color.DarkGray)
@Composable
fun avatar(avatarId: Int) {
    var img = 1;
    if (avatarId === 1) {
         img = R.drawable.avatar_b
    } else {
        img = R.drawable.avatar_a;

    }

    Image(
            asset = imageResource(id = img),
            modifier = Modifier
                    .fillMaxHeight().clip(shape = CircleShape),
            contentScale = ContentScale.Fit,


            )

}

@Composable
fun LeadearBoardRow(item: RankItem, position: Int,color:Color) {
    Row(
            modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()

                    .background(color = color)
                    .padding(top = 15.dp),
    ) {
        Box(modifier = Modifier.weight(2f).fillMaxWidth().align(alignment = Alignment.CenterVertically)) {
            Text(text = position.toString(), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),fontSize = 30.sp)
        }
        Box(modifier = Modifier.weight(4f)) {
            avatar(item.avatarId)
        }
        Box(Modifier.weight(15f)) {
            Column() {
                Text(text = item.name, modifier = Modifier.weight(1f),fontWeight = FontWeight.Bold)
                Text(text = item.score.toString(), modifier = Modifier
                        .weight(3f),style = TextStyle(fontStyle = FontStyle.Italic,fontSize = 30.sp))
            }
        }


    }
}

@Composable
fun LeaderBoard() {

    val items: Array<RankItem> = arrayOf(
            RankItem(1, "a111111111111111", 999999),
            RankItem(3, "222222222", 999999),
            RankItem(1, "ho33333333333la", 999999),
            RankItem(3, "hol4444444444a", 999999),
            RankItem(1, "ho5555555555la", 999999),
            RankItem(3, "ho666666666666la", 999999),
            RankItem(3, "ho666666666666la", 999999),
            RankItem(3, "ho666666666666la", 999999),
            RankItem(3, "ho666666666666la", 999999),
            RankItem(3, "ho666666666666la", 999999),
            RankItem(3, "ho666666666666la", 999999),
            RankItem(1, "h7777777777777ola", 999999)
    );


    Column() {

        ScrollableColumn(modifier = Modifier.weight(8f)) {


            Column {
                for ((index, item) in items.withIndex()) {
                    var isOdd= index %2 === 0;
                    var actualColor= Color.Black;
                            if(isOdd){
                                actualColor = Color(43  ,205,255);
                            }else{
                                actualColor = Color.Cyan;

                            }
                    LeadearBoardRow(item, index, actualColor)
                }
            }
        }

        LeadearBoardRow(RankItem(3, "tttttt", 22), 22, Color.LightGray)


    }


}

@Preview(showBackground = true)
@Composable
fun LeaderBoardPreview() {
    LeaderBoard()
}

