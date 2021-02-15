package com.smallgroup.login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smallgroup.login.R

class RegistrationFragmnet : Fragment(R.layout.fragment_registration) {

    interface OnRegistrFragmentListener{
        fun registration()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_registration, container, false)
        val listener = activity as OnRegistrFragmentListener?

        val login_button = rootView.findViewById<Button>(R.id.login_button)
        login_button.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                    activity, "Нажата кнпка Зарегистрироваться",
                    Toast.LENGTH_SHORT).show()


            listener?.registration()
        })

        return rootView
    }

}