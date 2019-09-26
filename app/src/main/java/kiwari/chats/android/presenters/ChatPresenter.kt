package kiwari.chats.android.presenters

import com.google.firebase.database.DataSnapshot
import kiwari.chats.android.interfaces.FirebaseCallBacks
import kiwari.chats.android.interfaces.ModelCallBacks
import kiwari.chats.android.models.ChatPojo
import kiwari.chats.android.models.MessageModel
import kiwari.chats.android.utils.Constans
import kiwari.chats.android.utils.FirebaseManager
import kiwari.chats.android.views.IChatView
import java.util.*


class ChatPresenter(private var mIChatView: IChatView?) : FirebaseCallBacks, ModelCallBacks {

    private val mModel: MessageModel = MessageModel(this)
    private val firebaseManager: FirebaseManager = FirebaseManager.getInstance(Constans.ROOM_NAME, this)!!

    fun sendMessageToFirebase(message: String) {
        if (message.trim() != "") {
            firebaseManager.sendMessageToFirebase(message)
        }
        mIChatView!!.clearEditText()
    }

    fun setListener() {
        firebaseManager.addMessageListeners()
    }

    fun onDestroy() {
        firebaseManager.removeListeners()
        firebaseManager.destroy()
        mIChatView = null
    }

    override fun onNewMessage(dataSnapshot: DataSnapshot?) {
        mModel.addMessages(dataSnapshot!!)
    }

    override fun onModelUpdated(messages: ArrayList<ChatPojo>) {
        if (messages.size > 0) {
            mIChatView!!.updateList(messages)
        }
    }
}
