package com.example.fuzzychainsaw.view

import android.graphics.Canvas

interface IProcess {
    fun draw(canvas: Canvas?, processBar: ProcessBar)
    fun updateProcess(old: Int, new: Int) {}
}