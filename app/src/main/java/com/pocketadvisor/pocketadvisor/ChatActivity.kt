package com.pocketadvisor.pocketadvisor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_chat.*
import android.widget.EditText

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

import com.pocketadvisor.pocketadvisor.recyclerchat.ChatData
import com.pocketadvisor.pocketadvisor.recyclerchat.ConversationRecyclerView
import org.json.JSONObject
import java.util.ArrayList

class ChatActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private lateinit var ws: WebSocket

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ConversationRecyclerView

    private inner class EchoWebSocketListener : WebSocketListener() {
        private val NORMAL_CLOSURE_STATUS = 1000

        fun printMsg(txt: String?) {
            println(txt)
        }

        override fun onOpen(webSocket: WebSocket?, response: Response?) {
            super.onOpen(webSocket, response)
            println("Receiving: $response")
        }

        override fun onMessage(webSocket: WebSocket?, text: String?) {
            super.onMessage(webSocket, text)

            outputMessage(text.toString())
            //println("Receiving: $text")
        }

        override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
            super.onClosing(webSocket, code, reason)
            //chat.outputMessage("Closing: $code / $reason")
            println("Closing: $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
            super.onFailure(webSocket, t, response)
            //chat.outputMessage("Error: ${t?.message}")
            println("Error: ${t?.message}")
        }

        fun sendMessage(webSocket: WebSocket?) {
            val jsonObj = JSONObject()
            jsonObj.put("from", "777")
            jsonObj.put("msg", "This is from Android!")
            println(jsonObj.toString())
            webSocket?.send(jsonObj.toString())
            webSocket?.send("hi")

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        start()

        mRecyclerView = recyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ConversationRecyclerView(this, setData())
        mRecyclerView.adapter = mAdapter

        mRecyclerView.postDelayed({ mRecyclerView.smoothScrollToPosition(mRecyclerView.adapter.itemCount - 1) }, 1000)


        val editText = findViewById<EditText>(R.id.enter_message)
        editText?.setHorizontallyScrolling(false)
        editText?.maxLines = 4

        editText.setOnClickListener {
            mRecyclerView.postDelayed({
                mRecyclerView.smoothScrollToPosition(mRecyclerView.adapter.itemCount - 1)
            }, 500)
        }

        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            // If the event is a key-down event on the "enter" button

            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Perform action on key press
                println("Key Pressed!")
                println(event)
                println(keyCode)
                sendText()
                return@OnKeyListener false
            }
            true

        })

    }

    fun sendText(view: View) {
        val message = enter_message.text.toString()
        if (message != "") {
            enter_message.setText("")
            println(message)
            val data = ArrayList<ChatData>()
            val item = ChatData()
            item.setText(message)
            item.setTime("")
            item.setType("2")
            data.add(item)
            mAdapter.addItem(data)
            mRecyclerView.smoothScrollToPosition(mRecyclerView.adapter.itemCount -1)
            sendMessage(message)

        }

    }
    private fun sendText() {
        val message = enter_message.text.toString()
        if (message != "") {
            enter_message.setText("")
            println(message)
            val data = ArrayList<ChatData>()
            val item = ChatData()
            item.setText(message)
            item.setTime("6:00pm")
            item.setType("2")
            data.add(item)
            println(data)
            mAdapter.addItem(data)
            mRecyclerView.smoothScrollToPosition(mRecyclerView.adapter.itemCount - 1)
            sendMessage(message)
        }
    }

    private fun sendMessage(message: String) {
        val jsonObj = JSONObject()
        jsonObj.put("from", "777")
        jsonObj.put("msg", message)
        println(jsonObj.toString())
        ws.send(jsonObj.toString())

    }

    fun recMessage(message: String?) {
    }

    private fun start() {
        val request = Request.Builder().addHeader("user", "777").url("wss://androidchatserver.mybluemix.net/").build()
        //request.build()
        val listener = EchoWebSocketListener()
        ws = client.newWebSocket(request, listener)


        //client.dispatcher().executorService().shutdown()
    }

    fun outputMessage(txt: String) {
        runOnUiThread {
            println(txt)
            val data = ArrayList<ChatData>()
            val item = ChatData()
            item.setText(txt)
            item.setTime("")
            item.setType("1")
            data.add(item)
            mAdapter.addItem(data)
            mRecyclerView.smoothScrollToPosition(mRecyclerView.adapter.itemCount - 1)
        }

    }

    fun setData(): ArrayList<ChatData> {
        val data = ArrayList<ChatData>()

//        val text = arrayOf("15 September", "Hi, Julia! How are you?", "Hi, Joe, looks great! :) ", "I'm fine. Wanna go out somewhere?", "Yes! Coffee maybe?", "Great idea! You can come 9:00 pm? :)))", "Ok!", "Ow my good, this Kit is totally awesome", "Can you provide other kit?", "I don't have much time, :`(")
//        val time = arrayOf("", "5:30pm", "5:35pm", "5:36pm", "5:40pm", "5:41pm", "5:42pm", "5:40pm", "5:41pm", "5:42pm")
//        val type = arrayOf("0", "2", "1", "1", "2", "1", "2", "2", "2", "1")

        val text = arrayOf("Hi, how may I help you today?")
        val time = arrayOf("")
        val type = arrayOf("1")

        for (i in text.indices) {
            val item = ChatData()
            item.setType(type[i])
            item.setText(text[i])
            item.setTime(time[i])
            data.add(item)
        }

        return data
    }
}
