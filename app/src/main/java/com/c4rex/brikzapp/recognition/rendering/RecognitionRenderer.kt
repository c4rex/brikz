package com.c4rex.brikzapp.recognition.rendering

import android.opengl.GLSurfaceView
import com.wikitude.common.rendering.RenderExtension
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class RecognitionRenderer(private val renderExtension: RenderExtension?) :  GLSurfaceView.Renderer {

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        renderExtension?.onSurfaceCreated(gl, config)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        renderExtension?.onSurfaceChanged(gl, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        renderExtension?.onDrawFrame(gl)
    }

    fun onResume() {
        renderExtension?.onResume()
    }

    fun onPause() {
        renderExtension?.onPause()
    }
}
