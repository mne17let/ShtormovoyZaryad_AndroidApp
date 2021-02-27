package com.smallgroup.login.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.smallgroup.login.domain.model.Event
import com.smallgroup.login.repo.ResponseWrapper
import com.smallgroup.login.repo.SimpleRepo
import com.smallgroup.login.ui.BaseViewModel

class LoginViewModel: BaseViewModel() {

    var repo: SimpleRepo = SimpleRepo()

    val responseLiveData = MutableLiveData<Event<Unit>>()

    val email = MutableLiveData<String>("")
    val emailValidator = LiveDataValidator(email).apply {
        addRule("email is required") { it.isNullOrBlank() }
    }
    val password = MutableLiveData<String>("")
    val passwordValidator = LiveDataValidator(password).apply {
        addRule("password is required") { it.isNullOrBlank() }
        addRule("password length must be more 5") { it?.length != 5}
    }
    val valid = MediatorLiveData<Boolean>()

    init {
        valid.value = false
        valid.addSource(email) {validateForm()}
        valid.addSource(password) {validateForm()}
    }


    fun login(){
        requestWithLiveData(responseLiveData){
            api.login()
        }
    }

    fun validateForm(){
        val validators = listOf(emailValidator, passwordValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        valid.value = validatorResolver.isValid()
    }

}