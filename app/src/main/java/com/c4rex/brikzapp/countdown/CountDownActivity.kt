package com.c4rex.brikzapp.countdown

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.c4rex.brikzapp.R
import kotlinx.android.synthetic.main.activity_count_down.*

private const val TIME_START = "TIME_START"

fun launchCountDownActivity(context: Context, timeStart: Long) {
    context.startActivity(createCountDownActivity(context, timeStart))
}

@VisibleForTesting
fun createCountDownActivity(context: Context, timeStart: Long): Intent {
    val intent = Intent(context, StageCountDownActivityArg::class.java)
    intent.putExtra(TIME_START, timeStart)
    return intent
}

data class StageCountDownActivityArg(
    val timeStart: Long
)

class CountDownActivity : AppCompatActivity() {

    private lateinit var countdown_timer: CountDownTimer
    private var isRunning: Boolean = true;
    var time_in_milli_seconds = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_count_down)

        time_in_milli_seconds = 1 *60000L
        startTimer(time_in_milli_seconds)

        buttonStop.setOnClickListener {
            pauseTimer()
        }
    }

    private fun pauseTimer() {
        countdown_timer.cancel()
        isRunning = false
    }

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                Toast.makeText(
                    applicationContext,
                    "You did not complete the challenge, try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }
        countdown_timer.start()

        isRunning = true
        buttonStop.text = "COMPLETE"
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60
        if (seconds < 10){
            timer.text = "$minute:0$seconds"
        }else{
            timer.text = "$minute:$seconds"
        }

    }
}