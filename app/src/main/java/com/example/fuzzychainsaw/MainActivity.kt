package com.example.fuzzychainsaw

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fuzzychainsaw.view.MultiColorProcess
import com.example.fuzzychainsaw.view.ProcessBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var processBar: ProcessBar
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        processBar = findViewById(R.id.process)
        processBar.apply {
            process = MultiColorProcess(mapOf(
                    0..19 to intArrayOf(0x8000FFE5.toInt(), 0x80E7FFAA.toInt()),
                    20..59 to intArrayOf(0xFF00FFE5.toInt(), 0xFFE7FFAA.toInt()),
                    60..79 to intArrayOf(0xCCFE579B.toInt(), 0xCCF9FF19.toInt()),
                    80..100 to intArrayOf(0xFFFE579B.toInt(), 0xFFF9FF19.toInt())))
        }
        button = findViewById(R.id.button)
        button.setOnClickListener {
            mock()
        }
        mock()
    }

    // 模拟网络下载进度条
    private fun mock() {
        var percentage = 10
        CoroutineScope(Dispatchers.IO).launch {
            while (percentage <= 100) {
                processBar.percentage = percentage
                Thread.sleep(500)
                percentage += 10
            }
        }
    }
}