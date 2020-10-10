package com.c4rex.brikzapp.stagepreview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.c4rex.brikzapp.R
import com.c4rex.brikzapp.ui.BrikzAppTheme
import com.c4rex.brikzapp.ui.typography
import java.util.*

private const val STAGE = "STAGE"

fun launchStagePreviewActivity(context: Context, stage: Int) {
    context.startActivity(createStagePreviewActivity(context, stage))
}

@VisibleForTesting
fun createStagePreviewActivity(context: Context, stage: Int): Intent {
    val intent = Intent(context, StagePreviewActivity::class.java)
    intent.putExtra(STAGE, stage)
    return intent
}

data class StagePreviewActivityArg(
    val stage: Int
)

class StagePreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val args = StagePreviewActivityArg(
            stage = intent.getIntExtra(STAGE, -1)
        )

        setContent {
            BrikzAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    StagePreviewCard(args)
                }
            }
        }
    }

    @Composable
    private fun StagePreviewCard(args: StagePreviewActivityArg) {
        val padding = 20.dp
        val dm = resources.displayMetrics
        val stage = dm.widthPixels/dm.density

        Column(
                Modifier
                        .width(stage.dp)
                        .padding(20.dp,60.dp)
        ) {
            Spacer(Modifier.preferredSize(padding))
            Card(
                    modifier = Modifier.fillMaxHeight(),
                    elevation = 0.dp
            ) {
                Column() {
                    Image(
                            asset = imageResource(randomImage(args.stage)),
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .weight(3f)

                    )
                    Button(
                            onClick = {},
                            modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .weight(0.50f)
                                    .padding(0.dp,20.dp)
                                    .fillMaxWidth(),
                    ) {
                        Text(text = "Start", style = typography.button)
                    }
                }
            }
        }
    }

    private fun randomImage(stage : Int) : Int {
        val stage1 =
            intArrayOf(
                    R.drawable.example1,
                    R.drawable.example2
            )
        val stage2 =
            intArrayOf(
                    R.drawable.example3,
                    R.drawable.example4
            )
        val stage3 =
            intArrayOf(
                    R.drawable.example5,
                    R.drawable.example6
            )

        val ran = Random()
        val i: Int

        when (stage) {
            1 -> {
                i = ran.nextInt(stage1.size)
                return stage1[i]
            }
            2 -> {
                i = ran.nextInt(stage2.size)
                return stage2[i]
            }
            3 -> {
                i = ran.nextInt(stage3.size)
                return stage3[i]
            }
        }

        return 0
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure to quit the game?")
        builder.setMessage("You will lose the game")

        builder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
        }

        builder.setNegativeButton("No") { dialog, which ->
            moveTaskToBack(false)
        }

        builder.show()
    }
}
