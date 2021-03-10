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

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

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
            Toast.makeText(
                    activity, "Регистрация + $it",
                    Toast.LENGTH_SHORT).show()
            listener?.registration()
        })
    }


    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}