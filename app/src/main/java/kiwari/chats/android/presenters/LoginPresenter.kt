package kiwari.chats.android.presenters

import com.google.firebase.auth.FirebaseAuth
import kiwari.chats.android.views.ILoginView


class LoginPresenter(internal var iLoginView: ILoginView) {

    fun firebaseAuthLogin(email: String, password: String) {
        if (iLoginView.validateForm().not()) {
            iLoginView.showToast("Please fill the form")
            return
        }

        iLoginView.showProgressbar()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    iLoginView.hideProgressbar()
                    if (!task.isSuccessful) {
                        val message = task.exception!!.message ?: "Please check your connection"
                        iLoginView.showToast(message)
                    } else {
                        iLoginView.authSuccessful()
                    }
                }
    }
}
