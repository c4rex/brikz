package com.c4rex.brikzapp.countdown

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.c4rex.brikzapp.databinding.ActivityCountDownBinding
import com.c4rex.brikzapp.level.LevelModel
import com.c4rex.brikzapp.level.StageModel
import com.c4rex.brikzapp.player.PlayerModel
import com.c4rex.brikzapp.recognition.activities.RecognitionActivity

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
    var time_in_milli_seconds = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val stage = intent.getParcelableExtra<StageModel>("stage")
        val player = intent.getParcelableExtra<PlayerModel>("player")
        val level = intent.getParcelableExtra<LevelModel>("level")

        time_in_milli_seconds = if (stage != null) stage.timeMilSec else (1 * 60000L)
        startTimer(time_in_milli_seconds, binding)

        binding.buttonStop.setOnClickListener {
            val leftTime = binding.timer.text
            pauseTimer()
            val intent = Intent(this@CountDownActivity, RecognitionActivity::class.java)
            intent.putExtra("level", level)
            intent.putExtra("player", player)
            intent.putExtra("stage", stage)
            intent.putExtra("timeleft", leftTime)
            startActivity(intent)
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