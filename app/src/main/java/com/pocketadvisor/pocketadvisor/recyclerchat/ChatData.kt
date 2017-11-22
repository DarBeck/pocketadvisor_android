package com.pocketadvisor.pocketadvisor.recyclerchat

/**
 * Created by Darren on 22-Nov-17.
 */
class ChatData {
    private var type:String = ""
    private var text:String = ""
    private var time:String = ""

    fun getText(): String {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getTime(): String {
        return time
    }

    fun setTime(time: String) {
        this.time = time
    }
}