package kiwari.chats.android.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kiwari.chats.android.interfaces.FirebaseCallBacks
import java.util.*


class FirebaseManager private constructor(roomName: String, private var mCallbacks: FirebaseCallBacks?) : ChildEventListener {

    private val mMessageReference: DatabaseReference

    init {
        mMessageReference = FirebaseDatabase.getInstance().reference.child(roomName)
    }

    fun addMessageListeners() {
        mMessageReference.addChildEventListener(this)
    }

    fun removeListeners() {
        mMessageReference.removeEventListener(this)
    }

    override fun onChildAdded(p0: DataSnapshot, s: String?) {
        mCallbacks!!.onNewMessage(p0)
    }

    override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onChildRemoved(dataSnapshot: DataSnapshot) {

    }

    override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

    }

    override fun onCancelled(databaseError: DatabaseError) {

    }

    fun sendMessageToFirebase(message: String) {
        val map = HashMap<String, Any>()
        map["text"] = message
        map["time"] = System.currentTimeMillis()

        val user = FirebaseAuth.getInstance().currentUser!!
        map["senderId"] = user.uid
        map["senderName"] = user.displayName!!
        map["senderPhotoURL"] = user.photoUrl.toString()

        val keyToPush = mMessageReference.push().key
        mMessageReference.child(keyToPush!!).setValue(map)
    }

    fun destroy() {
        sFirebaseManager = null
        mCallbacks = null
    }

    companion object {
        @Volatile
        private var sFirebaseManager: FirebaseManager? = null

        @Synchronized
        fun getInstance(roomName: String, callBacks: FirebaseCallBacks): FirebaseManager? {
            if (sFirebaseManager == null) {
                synchronized(FirebaseManager::class.java) {
                    sFirebaseManager = FirebaseManager(roomName, callBacks)
                }
            }
            return sFirebaseManager
        }
    }
}
