package com.c4rex.brikzapp.countdown

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.c4rex.brikzapp.databinding.ActivityCountDownBinding
import com.c4rex.brikzapp.level.StageModel
import com.c4rex.brikzapp.player.PlayerModel


private const val TIME_START = "TIME_START"

fun launchCountDownActivity(context: Context, timeStart: Long) {
    context.startActivity(createCountDownActivity(context, timeStart))
}

@VisibleForTesting
fun createCountDownActivity(context: Context, timeStart: Long): Intent {
    val intent = Intent(context, CountDownActivity::class.java)
    intent.putExtra(TIME_START, timeStart)
    return intent
}

data class StageCountDownActivityArg(
    val timeStart: Long
)

class CountDownActivity : AppCompatActivity() {

    private lateinit var countdown_timer: CountDownTimer
    private var isRunning: Boolean = true;

    private lateinit var stopButton: Button
    private lateinit var timer: TextView

    var time_in_milli_seconds = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar!!.hide()

        val binding = ActivityCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val stage = intent.getParcelableExtra<StageModel>("stage")
        val player = intent.getParcelableExtra<PlayerModel>("player")

        time_in_milli_seconds = if (stage != null) stage.timeMilSec else (1 * 60000L)
        startTimer(time_in_milli_seconds, binding)

        binding.buttonStop.setOnClickListener {
            pauseTimer()
        }

    }

    private fun pauseTimer() {
        countdown_timer.cancel()
        isRunning = false
    }

    private fun startTimer(time_in_seconds: Long, binding: ActivityCountDownBinding) {
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
                updateTextUI(binding)
            }
        }
        countdown_timer.start()

        isRunning = true
        binding.buttonStop.text = "COMPLETE"
    }

    private fun updateTextUI(binding: ActivityCountDownBinding) {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60
        if (seconds < 10){
            binding.timer.text = "$minute:0$seconds"
        }else{
            binding.timer.text = "$minute:$seconds"
        }
    }
}