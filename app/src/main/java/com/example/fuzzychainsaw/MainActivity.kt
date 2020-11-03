package com.example.fuzzychainsaw

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.example.fuzzychainsaw.util.FPSMonitor
import com.example.fuzzychainsaw.view.MultiColorProcess
import com.example.fuzzychainsaw.view.ProcessBar
import com.example.fuzzychainsaw.view.TestView
import com.example.fuzzychainsaw.view.cloud.Nebula
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var processBar: ProcessBar
    private lateinit var button: Button
    private lateinit var testView: TestView
    private lateinit var fpsview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fpsview = findViewById(R.id.fps)
        FPSMonitor.listener.add {
            fpsview.text = "FPS: $it"
        }
        FPSMonitor.start()
    }

    private fun animationSet() {
        val objectAnimator =
            ObjectAnimator.ofPropertyValuesHolder(
                testView,
                PropertyValuesHolder.ofFloat("scaleX", 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f),
                PropertyValuesHolder.ofFloat("alpha", 1f),
            ).apply {
                repeatCount = -1
                duration = 2000
            }
        objectAnimator.start()
    }
}