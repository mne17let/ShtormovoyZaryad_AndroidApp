package com.smallgroup.login.ui.login

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smallgroup.login.domain.model.Event
import com.smallgroup.login.domain.model.User
import com.smallgroup.login.repo.ResponseWrapper
import com.smallgroup.login.repo.SimpleRepo
import com.smallgroup.login.ui.BaseViewModel

class SignUpViewModel: BaseViewModel() {

    var repo: SimpleRepo = SimpleRepo()

    val responseLiveData = MutableLiveData<Event<String>>()

    val email = MutableLiveData<String>("")
    val emailValidator = LiveDataValidator(email).apply {
        addRule("email is required") { it.isNullOrBlank() }
        addRule("email is not corrected") {!Patterns.EMAIL_ADDRESS.matcher(it).matches()}
    }

    val username = MutableLiveData<String>("")
    val usernameValidator = LiveDataValidator(username).apply {
        addRule("username is required") { it.isNullOrBlank() }
    }

    val password = MutableLiveData<String>("")
    val passwordValidator = LiveDataValidator(password).apply {
        addRule("password is required") { it.isNullOrBlank() }
    }

    val passwordCheck = MutableLiveData<String>("")
    val passwordCheckValidator = LiveDataValidator(password).apply {
        addRule("password is required") { it.isNullOrBlank() }
        addRule("passwords is not match") { !password.value.equals(it) }
    }

    val valid = MediatorLiveData<Boolean>()

    init {
        valid.value = false
        valid.addSource(email) {validateForm()}
        valid.addSource(username) {validateForm()}
        valid.addSource(password) {validateForm()}
        valid.addSource(passwordCheck) {validateForm()}
    }

    fun signUp(){
        requestWithLiveData(responseLiveData) {
            api.signUp(
                    User(
                    password.value.toString(),
                    password.value.toString(),
                    email.value.toString(),
                    username.value.toString()
                    )
            )
        }
    }

    private fun validateForm() {
        val validators = listOf(emailValidator, usernameValidator, passwordValidator, passwordCheckValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        valid.value = validatorResolver.isValid()
    }

}