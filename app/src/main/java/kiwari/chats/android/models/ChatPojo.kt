package kiwari.chats.android.models

import com.google.firebase.database.DataSnapshot
import java.util.HashMap

class ChatPojo(dataSnapshot: DataSnapshot) {
    var msgKey: String? = null
    var timeStamp: Long = 0
    var message: String? = null
    var senderId: String? = null
    var senderName: String? = null
    var senderPhotoURL: String? = null

    init {
        val hashMap = dataSnapshot.value as HashMap<String, Any>
        this.msgKey = dataSnapshot.key
        this.message = hashMap["text"].toString()
        this.senderId = hashMap["senderId"].toString()
        this.senderName = hashMap["senderName"].toString()
        this.senderPhotoURL = hashMap["senderPhotoURL"].toString()
        this.timeStamp = java.lang.Long.parseLong(hashMap ["time"].toString())
    }

}

