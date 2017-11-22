package com.pocketadvisor.pocketadvisor.recyclerchat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.pocketadvisor.pocketadvisor.R

/**
 * Created by Darren on 22-Nov-17.
 */
class HolderYou(v: View) : RecyclerView.ViewHolder(v) {

    var time: TextView? = null
    var chatText: TextView? = null

    init {
        time = v.findViewById(R.id.tv_time)
        chatText = v.findViewById(R.id.tv_chat_text)
    }
}