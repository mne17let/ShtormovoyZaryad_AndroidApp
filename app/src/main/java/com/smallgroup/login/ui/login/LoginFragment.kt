package com.smallgroup.login.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.smallgroup.login.R
import com.smallgroup.login.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private var fragmentBinding: FragmentLoginBinding? = null

    interface OnLoginFragmentListener{
        fun login()
        fun openRegistrationForm()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener = activity as OnLoginFragmentListener?
        val binding = FragmentLoginBinding.bind(view)
        val loginViewModel: LoginViewModel by viewModels()

        binding.viewModel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        fragmentBinding = binding


        binding.regButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                    activity, "Регистрация",
                    Toast.LENGTH_SHORT).show()
            listener?.openRegistrationForm()
        })

        binding.loginButton.setOnClickListener(View.OnClickListener {
            listener?.login()
        })

    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }



//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//
//        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
//        val listener = activity as OnLoginFragmentListener?
//        binding = FragmentLoginBinding.bind(rootView)
//
//        val reg = rootView.findViewById<Button>(R.id.reg_button)
//        reg.setOnClickListener(View.OnClickListener {
//            Toast.makeText(
//                    activity, "Регистрация",
//                    Toast.LENGTH_SHORT).show()
//            listener?.openRegistrationForm()
//        })
//
//        val login = rootView.findViewById<Button>(R.id.login_button)
//        login.setOnClickListener(View.OnClickListener {
//            listener?.login()
//        })
//
//        return binding
//    }

}