package kiwari.chats.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kiwari.chats.android.presenters.LoginPresenter
import kiwari.chats.android.views.ILoginView
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), ILoginView {

    private var mLoginPresenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginPresenter = LoginPresenter(this)

        submitButton.setOnClickListener {
            mLoginPresenter!!.firebaseAuthLogin(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
            )
        }

        if (FirebaseAuth.getInstance().currentUser != null)
            authSuccessful()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun authSuccessful() {
        startActivity(Intent(this, ConversationActivity::class.java))
        finish()
    }

    override fun validateForm(): Boolean {
        return emailEditText.text.toString().isNotEmpty()
                && passwordEditText.text.toString().isNotEmpty()
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }
}
