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
        addRule("Поле не заполнено.") { it.isNullOrBlank() }
        addRule("Неправильно указана почта.") {!Patterns.EMAIL_ADDRESS.matcher(it).matches()}
    }

    val username = MutableLiveData<String>()
    val usernameValidator = LiveDataValidator(username).apply {
        addRule("Поле не заполнено.") { it.isNullOrBlank() }
        addRule("Минимальная длина никнейма - 3 сивола.") { it?.length!! < 3}
        addRule("Введенный никнейм не допустим.") { "^_+\$".toRegex().matches(
            it.toString()
        )}
        addRule("Введенный никнейм не допустим.") { !"^[A-Za-z0-9_]+\$".toRegex().matches(
            it.toString()
        ) }
    }

    val password = MutableLiveData<String>()
    val passwordValidator = LiveDataValidator(password).apply {
        addRule("Поле не заполнено") { it.isNullOrBlank() }
        addRule("Пароль может содержать только латинские буквы, цифры и _.") { !"^[A-Za-z0-9]+$".toRegex().matches(
                it.toString()
        ) }
        addRule("Пароль не должен сожержать только цифры.") { "^[0-9]+$".toRegex().matches(
                it.toString()
        ) }
        addRule("Пароль не может быть таким же как и никнейм.") {it.equals(username.value)}
        addRule("Минимальная длинная пароля - 5 символов.") { it?.length!! <= 5}
    }

    val passwordCheck = MutableLiveData<String>()
    val passwordCheckValidator = LiveDataValidator(passwordCheck).apply {
        addRule("Полес не заполнено.") { it.isNullOrBlank() }
        addRule("Пароли не совпадают.") {!it.equals(password.value)}
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