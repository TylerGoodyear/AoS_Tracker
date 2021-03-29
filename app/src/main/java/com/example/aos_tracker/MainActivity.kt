package com.example.aos_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_vp_inc).setOnClickListener {
            val showCountTextView = findViewById<TextView>(R.id.editTextNumber_vp2)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount++
            showCountTextView.text = currentCount.toString()
        }
        findViewById<Button>(R.id.button_cp_inc).setOnClickListener {
            val showCountTextView = findViewById<TextView>(R.id.editTextNumber_cp)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount++
            showCountTextView.text = currentCount.toString()
        }
        findViewById<Button>(R.id.button_vp_dec).setOnClickListener {
            val showCountTextView = findViewById<TextView>(R.id.editTextNumber_vp2)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            showCountTextView.text = currentCount.toString()
        }
        findViewById<Button>(R.id.button_cp_dec).setOnClickListener {
            val showCountTextView = findViewById<TextView>(R.id.editTextNumber_cp)
            var currentCount = showCountTextView.text.toString().toInt()
            currentCount = (currentCount - 1).coerceAtLeast(0)
            showCountTextView.text = currentCount.toString()
        }
    }


}