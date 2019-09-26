package kiwari.chats.android.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kiwari.chats.android.R
import kiwari.chats.android.models.ChatPojo
import kiwari.chats.android.utils.Utils
import java.util.ArrayList
import kotlinx.android.synthetic.main.chat_item.view.*

class ChatAdapter(private val context: Context, private val chatList: ArrayList<ChatPojo>) :
        RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val currentUid = FirebaseAuth.getInstance().uid

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatPojo = chatList[position]
        if (currentUid == chatPojo.senderId) {
            holder.contentRightLayout.visibility = View.VISIBLE
            holder.contentLeftLayout.visibility = View.GONE
            holder.contentRightTextView.text = chatPojo.message
            holder.dateRightTextView.text = Utils.convertTime(chatPojo.timeStamp)
            holder.userRightTextView.text = chatPojo.senderName
        }
        else {
            holder.contentRightLayout.visibility = View.GONE
            holder.contentLeftLayout.visibility = View.VISIBLE
            holder.contentLeftTextView.text = chatPojo.message
            holder.dateLeftTextView.text = Utils.convertTime(chatPojo.timeStamp)
            holder.userLeftTextView.text = chatPojo.senderName
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val contentLeftLayout: LinearLayout = view.contentLeftLayout
        val contentLeftTextView: TextView = view.contentLeftTextView
        val dateLeftTextView: TextView = view.dateLeftTextView
        val userLeftTextView: TextView = view.userLeftTextView
        val contentRightLayout: RelativeLayout = view.contentRightLayout
        val contentRightTextView: TextView = view.contentRightTextView
        val dateRightTextView: TextView = view.dateRightTextView
        val userRightTextView: TextView = view.userRightTextView
    }
}
