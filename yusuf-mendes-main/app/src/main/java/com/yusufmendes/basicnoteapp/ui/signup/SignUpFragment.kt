package com.yusufmendes.basicnoteapp.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.databinding.FragmentSignUpBinding
import com.yusufmendes.basicnoteapp.util.constants.Constants.MIN_PASSWORD_LENGTH
import com.yusufmendes.basicnoteapp.util.extension.changeButtonState
import com.yusufmendes.basicnoteapp.util.extension.isEmailValid
import com.yusufmendes.basicnoteapp.util.extension.isPasswordValid
import com.yusufmendes.basicnoteapp.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        viewModel = ViewModelProviders.of(requireActivity()).get(SignUpViewModel::class.java)

        binding.textInputEditTextPassword.addTextChangedListener { text ->
            binding.signUpButton.changeButtonState(text.toString().isPasswordValid(MIN_PASSWORD_LENGTH))
        }
        binding.textInputEditTextEmail.addTextChangedListener{text ->
            binding.signUpButton.changeButtonState(text.toString().isEmailValid())
        }

        //sign up buttonuna tiklaninca yapilacak islemler
        binding.signUpButton.setOnClickListener {
            viewModel.fullName = binding.textInputEditTextFullName.text.toString().trim()
            viewModel.email = binding.textInputEditTextEmail.text.toString().trim()
            viewModel.password = binding.textInputEditTextPassword.text.toString().trim()

            if (viewModel.fullName.isEmpty()) {
                binding.textInputEditTextFullName.error = "This area can not be empty"
                binding.textInputEditTextFullName.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.email.isEmpty()) {
                binding.textInputEditTextEmail.error = "This area can not be empty"
                binding.textInputEditTextEmail.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.password.isEmpty()) {
                binding.textInputEditTextPassword.error = "This area can not be empty"
                binding.textInputEditTextPassword.requestFocus()
                return@setOnClickListener
            }
            observerLiveData()
        }

        //forgot password text'ine tiklaninca yapilacak islemler
        binding.signUpForgotPassword.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToForgotPasswordFragment()
            Navigation.findNavController(it).navigate(action)
        }
        //sign in now text'ine tiklaninca yapilacak islemler
        binding.signUpSignIn.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observerLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it != null) {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            } else {
                Toast.makeText(activity!!, "The Password must be at least 6 characters.", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.registerData()
    }
}
