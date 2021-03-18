package com.smallgroup.login.ui.login

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smallgroup.login.domain.model.AuthUser
import com.smallgroup.login.domain.model.User
import com.smallgroup.login.repo.RetrofitRepo
import com.smallgroup.login.ui.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel: BaseViewModel() {

    var repo: RetrofitRepo = RetrofitRepo()

    val responseLiveData = MutableLiveData<AuthUser?>()

    val email = MutableLiveData<String>()
    val emailValidator = LiveDataValidator(email).apply {
        addRule("email is required") { it.isNullOrBlank() }
        addRule("email is not corrected") {!Patterns.EMAIL_ADDRESS.matcher(it).matches()}
    }

    val username = MutableLiveData<String>()
    val usernameValidator = LiveDataValidator(username).apply {
        addRule("username is required") { it.isNullOrBlank() }
        addRule("username is short (less 3 chars)") { it?.length!! < 3}
        addRule("username is not correct") { !"^[A-Za-z0-9_]+\$".toRegex().matches(
                it.toString()
        ) }
    }

    val password = MutableLiveData<String>()
    val passwordValidator = LiveDataValidator(password).apply {
        addRule("password is required") { it.isNullOrBlank() }
        addRule("password is not corrected") { !"^[A-Za-z0-9]+$".toRegex().matches(
                it.toString()
        ) }
        addRule("password is short (less 8 chars)") { it?.length!! < 8}
    }

    val passwordCheck = MutableLiveData<String>()
    val passwordCheckValidator = LiveDataValidator(passwordCheck).apply {
        addRule("password is required") { it.isNullOrBlank() }
        addRule("passwrods is not matched") {!it.equals(password.value)}
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
        viewModelScope.launch {
            val response = repo.signUp(
                    User(
                            password.value.toString(),
                            email.value.toString(),
                            username.value.toString()
                    )
            )
            responseLiveData.postValue(response.value)
        }
    }

    private fun validateForm() {
        val validators = listOf(emailValidator, usernameValidator, passwordValidator, passwordCheckValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        valid.value = validatorResolver.isValid()
    }

}