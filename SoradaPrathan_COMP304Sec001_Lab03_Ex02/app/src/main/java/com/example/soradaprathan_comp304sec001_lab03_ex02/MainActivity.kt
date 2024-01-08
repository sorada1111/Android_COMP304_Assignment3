package com.example.soradaprathan_comp304sec001_lab03_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val oneDayInMillis = 24 * 60 * 60 * 1000 // Number of milliseconds in one day
        val longDateTextView = findViewById<LongDateTextView>(R.id.long_date_textView)
        // minus 1 day for current date
        longDateTextView.longDate = (System.currentTimeMillis() - oneDayInMillis).toString()
    }
}