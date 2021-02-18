package com.smallgroup.login.ui.login

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smallgroup.login.repo.SimpleRepo

class LoginViewModel: ViewModel() {

    var repo: SimpleRepo = SimpleRepo()

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val valid = MediatorLiveData<Boolean>()

    init {
        valid.value = false
        valid.apply {
            addSource(email){
                isFieldValid()
            }
        }
    }

    fun login(){
        repo.login("login", "pass")
    }

    fun isFieldValid() {
        //TODO
    }

}