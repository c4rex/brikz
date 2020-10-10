package com.c4rex.brikz

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
import androidx.core.content.ContextCompat.startActivity
import androidx.ui.tooling.preview.Preview
import com.c4rex.brikzapp.recognition.activities.RecognitionActivity
import com.c4rex.brikzapp.level.SelectLevelActivity
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.typography
 

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrikzTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Brikz")
                        App()
                        RecognitionImmerseButton()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun App() {
            Button(
                onClick = { startActivity(Intent(this@MainActivity, SelectLevelActivity::class.java)) }
            ) {
                Text(text = "Challenge", style = typography.button)
            }
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
        Text("Button")
    }

}


