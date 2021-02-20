package com.smallgroup.login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.smallgroup.login.R
import com.smallgroup.login.databinding.FragmentLoginBinding
import com.smallgroup.login.databinding.FragmentRegistrationBinding

class RegistrationFragmnet : Fragment(R.layout.fragment_registration) {

    private var fragmentBinding: FragmentRegistrationBinding? = null

    interface OnRegistrFragmentListener{
        fun registration()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener = activity as OnRegistrFragmentListener?
        val binding = FragmentRegistrationBinding.bind(view)
        val signUoViewModel: SignUpViewModel by viewModels()

        binding.viewModel = signUoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding = binding

        binding.buttonSignUp.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                    activity, "Нажата кнпка Зарегистрироваться",
                    Toast.LENGTH_SHORT).show()

            listener?.registration()
        })
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}