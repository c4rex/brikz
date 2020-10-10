package com.c4rex.brikzapp.countdown

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.compose.material.Surface
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.setContent
import com.c4rex.brikzapp.ui.BrikzAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.c4rex.brikzapp.ui.typography
import androidx.compose.foundation.Text
import androidx.compose.runtime.*

private const val TIME_STAGE = "TIME_STAGE"
var START_MILLI_SECONDS : Long = 60000

fun launchCountDownActivity(context: Context, timeStage: Long) {
    context.startActivity(createCountDownActivity(context, timeStage))
}

@VisibleForTesting
fun createCountDownActivity(context: Context, timeStage: Long): Intent {
    val intent = Intent(context, CountDownActivity::class.java)
    intent.putExtra(TIME_STAGE, timeStage)
    return intent
}

data class CountDownActivityArg(
    val timeStage: Long
)


class CountDownActivity : AppCompatActivity() {

    private lateinit var countdown: CountDownTimer
    private var isRunning: Boolean = false
    var timeInMilliSeconds : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = CountDownActivityArg(
            timeStage = intent.getLongExtra(TIME_STAGE, -1)
        )
        timeInMilliSeconds = args.timeStage

        setContent {
            BrikzAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CountDownView(args)
                }
            }
        }
    }

    @Composable
    private fun CountDownView(args: CountDownActivityArg) {
        val padding = 20.dp
        val dm = resources.displayMetrics
        val stage = dm.widthPixels/dm.density
        val counter = remember { mutableStateOf("") }

        startTimer(args.timeStage, counter)

        Column(
            Modifier
                .width(stage.dp)
                .padding(20.dp,60.dp)
        ) {
            Text(counter.value)
            Spacer(Modifier.preferredSize(padding))
            Button(
                onClick = {pauseTimer(counter)},
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(0.50f)
                    .padding(0.dp,20.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = "Complete", style = typography.button)
            }
        }
    }

    private fun startTimer(timeStage: Long, counter : MutableState<String>) {
        countdown = object : CountDownTimer(timeStage, 1000) {

            override fun onTick(p0: Long) {
                timeInMilliSeconds = p0
                updateTextUI(counter)
            }

            override fun onFinish() {

            }
        }

        countdown.start()
        isRunning = true
    }

    private fun pauseTimer(counter : MutableState<String>) {
        countdown.cancel()
        isRunning = false
        Toast.makeText(applicationContext,"Ha terminando faltando ${counter.value}",Toast.LENGTH_SHORT).show()
    }

    private fun updateTextUI(counter : MutableState<String>) {
        val minute = (timeInMilliSeconds / 1000) / 60
        val seconds = (timeInMilliSeconds / 1000) % 60
        counter.value = "$minute:$seconds"
    }


}