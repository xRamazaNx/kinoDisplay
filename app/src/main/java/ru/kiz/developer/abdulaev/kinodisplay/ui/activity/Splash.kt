package ru.kiz.developer.abdulaev.kinodisplay.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewGroup.MarginLayoutParams
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView
import ru.kiz.developer.abdulaev.kinodisplay.R
import ru.kiz.developer.abdulaev.kinodisplay.ui.dp

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        // имитация длительной загрузки
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MovieViewerActivity::class.java))
            finish()
        }, 1000)
    }

    private fun getLayout(): View = LinearLayout(this).apply {
        layoutParams = MarginLayoutParams(MATCH_PARENT, MATCH_PARENT)
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        addView(TextView(this@Splash).apply {
            val namePadding = dp(16)
            setPadding(namePadding, namePadding, namePadding, namePadding)
            textSize = 24f
            text = getString(R.string.app_name)
            this.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        })
        addView(CircleImageView(this@Splash).apply {
            setImageResource(R.mipmap.ic_launcher)
            alpha = 0.1f
            animate()
                .alpha(1f)
                .setDuration(500)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
            this.layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER)
        })
    }
}