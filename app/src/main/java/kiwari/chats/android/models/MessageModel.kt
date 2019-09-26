package kiwari.chats.android.models

import com.google.firebase.database.DataSnapshot
import kiwari.chats.android.interfaces.ModelCallBacks
import java.util.*


class MessageModel(internal var mModelCallBacks: ModelCallBacks) {

    private var mMessages: ArrayList<ChatPojo>? = null

    fun addMessages(dataSnapshot: DataSnapshot) {
        if (mMessages == null) {
            mMessages = ArrayList()
        }

        val chatPojo = ChatPojo(dataSnapshot)
        mMessages!!.add(chatPojo)
        mModelCallBacks.onModelUpdated(mMessages!!)
    }
}