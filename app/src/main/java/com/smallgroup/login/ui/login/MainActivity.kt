package com.smallgroup.login.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smallgroup.login.R

class MainActivity : AppCompatActivity(R.layout.activity_main),
        LoginFragment.OnLoginFragmentListener, RegistrationFragment.OnRegistrFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            var fragment = LoginFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                    fragment,
                    fragment.javaClass.simpleName)
            .commit()
        }
    }

    override fun login() {
        Toast.makeText(
                this, "Вход выполнен",
                Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun openRegistrationForm() {
        var fragment = RegistrationFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            fragment,
            fragment.javaClass.simpleName)
        .commit()
    }

    override fun registration() {
        var fragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            fragment,
            fragment.javaClass.simpleName)
        .commit()
    }
}