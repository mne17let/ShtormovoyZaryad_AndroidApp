package com.smallgroup.login.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.smallgroup.login.R


class LoginFragment : Fragment(R.layout.fragment_login) {

    interface OnLoginFragmentListener{
        fun login()
        fun openRegistrationForm()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        val listener = activity as OnLoginFragmentListener?

        val reg = rootView.findViewById<Button>(R.id.reg_button)
        reg.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                    activity, "Регистрация",
                    Toast.LENGTH_SHORT).show()
            listener?.openRegistrationForm()
        })

        val login = rootView.findViewById<Button>(R.id.login_button)
        login.setOnClickListener(View.OnClickListener {
            listener?.login()
        })

        return rootView
    }

}