package com.c4rex.brikzapp.recognition.rendering

import java.util.*

class RecognitionDriver(private val recognitionView: RecognitionView, fps: Int) {
    private val fps: Int = fps
    private var renderTimer: Timer? = null
    fun start() {
        if (renderTimer != null) {
            renderTimer!!.cancel()
        }
        renderTimer = Timer()
        renderTimer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                recognitionView.requestRender()
            }
        }, 0, 1000 / fps.toLong())
    }

    fun stop() {
        renderTimer!!.cancel()
        renderTimer = null
    }

}
