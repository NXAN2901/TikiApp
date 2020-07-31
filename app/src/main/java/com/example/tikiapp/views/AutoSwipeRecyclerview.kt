package com.example.tikiapp.views

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class AutoSwipeRecyclerview @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var delayMillis = DEFAULT_DELAY_TIME

    private val autoScroller by lazy {
        AutoSlider(this)
    }

    fun pause() {
        autoScroller.removeMessages(MSG_SCROLL)
    }

    fun resume() {
        autoScroller.removeMessages(MSG_SCROLL)
        autoScroller.sendEmptyMessageDelayed(MSG_SCROLL, delayMillis)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        resume()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_UP -> resume()
            MotionEvent.ACTION_DOWN -> pause()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun createScroller(position: Int = 0): LinearSmoothScroller {
        return object : LinearSmoothScroller(context) {
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_END
            }
        }.apply { targetPosition = position }
    }

    private fun scrollNext() {
        (layoutManager as? LinearLayoutManager)?.let { manager ->
            manager.startSmoothScroll(createScroller(manager.findLastVisibleItemPosition() + 1))
        }
        autoScroller.sendEmptyMessageDelayed(MSG_SCROLL, delayMillis)

    }

    internal class AutoSlider(autoSwipeRecyclerview: AutoSwipeRecyclerview) : Handler() {

        private val autoScrollView = WeakReference<AutoSwipeRecyclerview>(autoSwipeRecyclerview)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            autoScrollView.get()?.scrollNext()
        }

    }

    companion object {
        const val DEFAULT_DELAY_TIME = 2000L
        const val MSG_SCROLL = 1
    }

}