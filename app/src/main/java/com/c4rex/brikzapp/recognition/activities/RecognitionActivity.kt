package com.c4rex.brikzapp.recognition.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.c4rex.brikzapp.lead.activity.WinActivity
import com.c4rex.brikzapp.level.LevelModel
import com.c4rex.brikzapp.level.StageModel
import com.c4rex.brikzapp.player.PlayerModel
import com.c4rex.brikzapp.recognition.rendering.RecognitionDriver
import com.c4rex.brikzapp.recognition.rendering.RecognitionRenderer
import com.c4rex.brikzapp.recognition.rendering.RecognitionView
import com.wikitude.NativeStartupConfiguration
import com.wikitude.WikitudeSDK
import com.wikitude.common.WikitudeError
import com.wikitude.common.camera.CameraSettings
import com.wikitude.common.rendering.RenderExtension
import com.wikitude.rendering.ExternalRendering
import com.wikitude.tracker.ImageTarget
import com.wikitude.tracker.ImageTracker
import com.wikitude.tracker.ImageTrackerListener

private const val WIKITUDE_SDK_KEY =
    "fMDNaXoA8Z1eDtjZGu/CGc+mXY63haWAJ0quob4+UxlFom3tg1Tj9Uohy5Q173rZcHAxCRA4Mq8v/lAyCAAeP2FavusKqMme7fq/ymFML5sX8R/QyHaY4Q4zWWK1qWSZ+CUQ6sfq9TUA23kiz8I31eyWwOlvn6hgrtU+8J20OyZTYWx0ZWRfX6Ih20CRoexxFPWMczruJ4AZ8YN/I1vm7PfmuARlYiNB/VJOxVmDZ+dESSfhSzl2IL8IDLJ8y9/1vuApU/0ktG8ClRl8gAJD3O8wZOOfHZR6+SZV6s1WRBIjEZ67hg8cd+CVQv7ijOr+Vbd14gjhVWplfF8UC4BhSvsdxOHSBBQIPS9H7UrQ7YlcEc8BF7jzRc/WJaCIjflnL+U76qFRjq8n//OCZdYu8NU3Dw6jy6lm286+spIDnzcF56puIf+qFHRehYnBTUOb6ztSIvId961Fa6VHwzHfU5llgll6bC2Escj7QluRDPIqg4hrvWNJ5ve8UcIZ27VsZgfBgybAJ1E6FG/MFf+TBfKJaCRu1CtkR1dI7fNZdVrZIpgwMC4tOJjlrwh4ytYz6vPAKeAAkwOs7vFc0/2Y3y+0BWsJYCgWjnrBokAKYDS9ZLTV7dMFFOt4bPv7+5gAt3Hrtao/A5jjwn/esnadIKBVjUcGIx7VVOAMVgCkqGAqfRyEC3wVavFHRS0Wd6/1IqQus5303YtEBC04EnUz4opZS5ghKYyFqo2QWl/E0bF9buxfAw5syGyzl0wjcKKehKzty5MvQcKzzjcJ4AuHda9hYq0m5ZGsZkG6WHSTpDHvr10IGAm8ajSjrsZFiPI7tq46C1qrkrYvYTX338i5ZUD4ILbPpu66YiA68CgrOXI="

private val TAG: String? = RecognitionActivity::class.qualifiedName

class RecognitionActivity : AppCompatActivity(), ImageTrackerListener, ExternalRendering {

    private lateinit var wikitudeSDK: WikitudeSDK
    private lateinit var recognitionRenderer : RecognitionRenderer
    private lateinit var recognitionView : RecognitionView
    private lateinit var driver : RecognitionDriver
    private lateinit var countdown_timer: CountDownTimer
    private var countdown_is_running = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 1);
        }

        wikitudeSDK = WikitudeSDK(this)
        val startupConfiguration = NativeStartupConfiguration()
        startupConfiguration.licenseKey = WIKITUDE_SDK_KEY
        startupConfiguration.cameraPosition = CameraSettings.CameraPosition.BACK
        startupConfiguration.cameraResolution = CameraSettings.CameraResolution.AUTO

        wikitudeSDK.onCreate(applicationContext, this, startupConfiguration)
        val targetCollectionResource = wikitudeSDK.trackerManager.createTargetCollectionResource("file:///android_asset/tracker.zip")
        wikitudeSDK.trackerManager.createImageTracker(targetCollectionResource, this, null)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        createTimer(5000L)
    }
    private fun createTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                countdown_is_running = false;
                gotoPageW()
            }

            override fun onTick(p0: Long) {
                countdown_is_running = true;
            }
        }
    }

    private fun startTimer() {
        if (!countdown_is_running) {
            countdown_timer.start()
        }
    }

    private fun pauseTimer() {
        countdown_is_running = false;
        countdown_timer.cancel()
    }

    override fun onResume() {
        super.onResume()
        wikitudeSDK.onResume()
        recognitionView.onResume()
        driver.start()
    }

    override fun onPause() {
        super.onPause()
        recognitionView.onPause()
        driver.stop()
        wikitudeSDK.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        wikitudeSDK.onDestroy()
    }

    override fun onTargetsLoaded(p0: ImageTracker?) {}

    override fun onErrorLoadingTargets(p0: ImageTracker?, p1: WikitudeError?) {}

    override fun onImageRecognized(p0: ImageTracker?, p1: ImageTarget?) {
        if (!countdown_is_running) {
            startTimer()
        }
    }

    override fun onImageTracked(p0: ImageTracker?, p1: ImageTarget?) {}

    override fun onImageLost(p0: ImageTracker?, p1: ImageTarget?) {
        if (countdown_is_running) {
            pauseTimer()
        }
    }

    override fun onExtendedTrackingQualityChanged(p0: ImageTracker?, p1: ImageTarget?, p2: Int, p3: Int) {}

    override fun onRenderExtensionCreated(renderExtension: RenderExtension?) {

        recognitionRenderer = RecognitionRenderer(renderExtension)
        recognitionView = RecognitionView(applicationContext, recognitionRenderer, null)
        driver = RecognitionDriver(recognitionView, 30)

        setContentView(recognitionView)
        val ll = LinearLayout(this)

        ll.addView(ComposeView(this).apply {
            setContent {
                showInstructions()
            }
        }
        )

        addContentView(
            ll, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    override fun onBackPressed() {
        pauseTimer()
        finish()
    }

    private fun gotoPageW() {
        val stage = intent.getParcelableExtra<StageModel>("stage")
        val player = intent.getParcelableExtra<PlayerModel>("player")
        val level = intent.getParcelableExtra<LevelModel>("level")

        val intent = Intent(this@RecognitionActivity, WinActivity::class.java)
        intent.putExtra("stage", stage)
        intent.putExtra("player", player)
        intent.putExtra("level", level)

        ContextCompat.startActivity(this, intent, null)
        finish()
    }

    @Composable
    fun showInstructions(){
        Card(
            backgroundColor = Color.Black,
            contentColor = Color.White,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("Point the camera on the object to complete the stage.")
            }
        }
    }
}
