package kiwari.chats.android.views

import kiwari.chats.android.models.ChatPojo
import java.util.ArrayList


interface IChatView {
    fun updateList(list: ArrayList<ChatPojo>)
    fun clearEditText()
    fun logout()
}