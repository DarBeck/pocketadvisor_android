package com.pocketadvisor.pocketadvisor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.click_btn)

        btn.setOnClickListener {
            println("Second Button was clicked!")
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
    }

    //    Calls when the user clicks the Send button
    fun sendClick(view: View) {
        // Redirect user to activity screen
        println("Button Clicked!")


        val text = findViewById<EditText>(R.id.editText)
        val message = text.text.toString()
        text.setText("")
        textView2.text = message
    }
    }

