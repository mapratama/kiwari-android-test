package kiwari.chats.android.interfaces

import kiwari.chats.android.models.ChatPojo
import java.util.ArrayList

interface ModelCallBacks {
    fun onModelUpdated(messages: ArrayList<ChatPojo>)
}
