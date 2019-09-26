package kiwari.chats.android.views


interface ILoginView {
    fun showToast(message: String)
    fun validateForm(): Boolean
    fun authSuccessful()
    fun showProgressbar()
    fun hideProgressbar()
}
