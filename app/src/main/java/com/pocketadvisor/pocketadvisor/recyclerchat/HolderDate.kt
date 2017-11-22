package com.pocketadvisor.pocketadvisor.recyclerchat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.pocketadvisor.pocketadvisor.R

/**
 * Created by Darren on 22-Nov-17.
 */
class HolderDate(v: View) : RecyclerView.ViewHolder(v) {

    var date: TextView? = null

    init {
        date = v.findViewById(R.id.tv_date)
    }
}