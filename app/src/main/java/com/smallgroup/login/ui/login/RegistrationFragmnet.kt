package com.smallgroup.login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.smallgroup.login.R
import com.smallgroup.login.databinding.FragmentLoginBinding
import com.smallgroup.login.databinding.FragmentRegistrationBinding
import com.smallgroup.login.domain.model.Status

class RegistrationFragmnet : Fragment(R.layout.fragment_registration) {

    private var fragmentBinding: FragmentRegistrationBinding? = null
    val signUoViewModel: SignUpViewModel by viewModels()
    var listener: OnRegistrFragmentListener? = null

    interface OnRegistrFragmentListener{
        fun registration()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener = activity as OnRegistrFragmentListener?
        val binding = FragmentRegistrationBinding.bind(view)


        binding.viewModel = signUoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding = binding

        observeSignUp()

        binding.buttonSignUp.setOnClickListener {
            signUoViewModel.signUp()
        }
    }

    fun observeSignUp(){
        signUoViewModel.responseLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> viewOnSuccess(it.data)
                Status.ERROR -> viewOnError(it.error, it.data)
            }
        })
    }

    private fun viewOnError(error: Error?, data: String?) {
        //TODO
        Toast.makeText(
                activity, "Ошибка error: $error data: $data",
                Toast.LENGTH_SHORT).show()
    }

    fun viewOnSuccess(data: String?){
        //TODO
        Toast.makeText(
                activity, "Регистрация + $data",
                Toast.LENGTH_SHORT).show()
        listener?.registration()
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}