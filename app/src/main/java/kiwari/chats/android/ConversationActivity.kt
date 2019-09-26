package kiwari.chats.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import kiwari.chats.android.models.ChatPojo
import kiwari.chats.android.presenters.ChatPresenter
import kiwari.chats.android.utils.Constans
import kiwari.chats.android.utils.Utils
import kiwari.chats.android.views.ChatAdapter
import kiwari.chats.android.views.IChatView
import kotlinx.android.synthetic.main.activity_conversation.*
import java.util.*

class ConversationActivity : AppCompatActivity(), IChatView {

    private var mChatPresenter: ChatPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        mChatPresenter = ChatPresenter(this)
        mChatPresenter!!.setListener()
        recyclerView.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(toolbar)
        val logginUser = FirebaseAuth.getInstance().currentUser!!
        val opponentUser = if (logginUser.uid == Constans.jarjitProfile["uid"])
            Constans.ismailProfile else Constans.jarjitProfile

        Glide.with(this).load(opponentUser["avatar"])
                .apply(RequestOptions.circleCropTransform())
                .into(userPhoto)
        nameTextView.text = opponentUser["name"]

        backButton.setOnClickListener {
            finish()
        }

        sendButton.setOnClickListener {
            mChatPresenter!!.sendMessageToFirebase(chatEditText.text.toString())
        }
    }

    override fun clearEditText() {
        chatEditText.setText("")
    }

    override fun onDestroy() {
        super.onDestroy()
        mChatPresenter!!.onDestroy()
    }

    override fun updateList(list: ArrayList<ChatPojo>) {
        val chatAdapter = ChatAdapter(this, list)
        recyclerView.adapter = chatAdapter
        recyclerView.scrollToPosition(list.size - 1)
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.conversation_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_logout)
            logout()

        return super.onOptionsItemSelected(item)
    }
}
