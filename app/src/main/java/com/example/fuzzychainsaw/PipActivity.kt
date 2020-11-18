package com.example.fuzzychainsaw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)

        findViewById<Button>(R.id.button).setOnClickListener {
            enterPictureInPictureMode()
        }
    }
}