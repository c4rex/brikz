package com.c4rex.brikzapp.recognition.rendering

import android.content.Context
import android.graphics.PixelFormat
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class RecognitionView(context: Context?,
                      renderer: RecognitionRenderer,
                      attrs: AttributeSet?)
    : GLSurfaceView(context, attrs)
{
    private val recognitionRenderer: RecognitionRenderer = renderer

    init {
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0)

        setRenderer(recognitionRenderer)
        renderMode = RENDERMODE_WHEN_DIRTY
        holder.setFormat(PixelFormat.TRANSLUCENT)
    }

    override fun onPause() {
        super.onPause()
        recognitionRenderer.onPause()
    }

    override fun onResume() {
        super.onResume()
        recognitionRenderer.onResume()
    }
}