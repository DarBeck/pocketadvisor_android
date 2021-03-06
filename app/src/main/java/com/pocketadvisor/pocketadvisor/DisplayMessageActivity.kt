package com.pocketadvisor.pocketadvisor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the Intent that started this activity and extract the string
        val intent = intent
        val message = intent.getStringExtra("text_from_input")

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = message
    }
}
