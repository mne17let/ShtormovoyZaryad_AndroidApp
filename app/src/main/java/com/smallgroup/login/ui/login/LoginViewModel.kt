package com.smallgroup.login.ui.login

import androidx.lifecycle.*
import com.smallgroup.login.domain.model.Auth
import com.smallgroup.login.domain.model.LoginBody
import com.smallgroup.login.domain.model.Start
import com.smallgroup.login.repo.RetrofitRepo
import com.smallgroup.login.ui.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {

    var repo: RetrofitRepo = RetrofitRepo()

    val responseLiveData = MutableLiveData<Auth?>()

    val email = MutableLiveData<String>("")
    val emailValidator = LiveDataValidator(email).apply {
        addRule("email is required") { it.isNullOrBlank() }
    }
    val password = MutableLiveData<String>("")
    val passwordValidator = LiveDataValidator(password).apply {
        addRule("password is required") { it.isNullOrBlank() }
    }
    val valid = MediatorLiveData<Boolean>()

    init {
        valid.value = false
        valid.addSource(email) {validateForm()}
        valid.addSource(password) {validateForm()}
    }

    fun login() {
        viewModelScope.launch {
            val response = repo.login(LoginBody(
                    email.value.toString(),
                    password.value.toString()
            ))
            responseLiveData.postValue(response.value)
        }
    }

    fun validateForm(){
        val validators = listOf(emailValidator, passwordValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        valid.value = validatorResolver.isValid()
    }

}