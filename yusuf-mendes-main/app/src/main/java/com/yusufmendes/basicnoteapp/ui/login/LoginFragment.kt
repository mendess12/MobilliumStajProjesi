package com.yusufmendes.basicnoteapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.databinding.FragmentLoginBinding
import com.yusufmendes.basicnoteapp.util.constants.Constants.MIN_PASSWORD_LENGTH
import com.yusufmendes.basicnoteapp.util.storage.SharedPrefManager
import com.yusufmendes.basicnoteapp.util.extension.changeButtonState
import com.yusufmendes.basicnoteapp.util.extension.isEmailValid
import com.yusufmendes.basicnoteapp.util.extension.isPasswordValid
import com.yusufmendes.basicnoteapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java)

        //enabled özelliği
        binding.loginScreenEmailEditText.addTextChangedListener { text ->
            binding.loginButton.changeButtonState(text.toString().isEmailValid())
        }

        binding.loginScreenPasswordEditText.addTextChangedListener{ text ->
            binding.loginButton.changeButtonState(text.toString().isPasswordValid(MIN_PASSWORD_LENGTH))
        }


        if (SharedPrefManager.getInstance(requireActivity()).data.accessToken.isNotEmpty()) {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_loginFragment_to_notesFragment)
        }

        //login buttonuna iklaninca yapilacak islemler
        loginButton.setOnClickListener {

            viewModel.email = binding.loginScreenEmailEditText.text.toString().trim()
            viewModel.password = binding.loginScreenPasswordEditText.text.toString().trim()

            if (viewModel.email.isEmpty()) {
                binding.loginScreenEmailEditText.error = "This area can not be empty"
                binding.loginScreenEmailEditText.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.password.isEmpty()) {
                binding.loginScreenPasswordEditText.error = "This area can not be empty"
                binding.loginScreenPasswordEditText.requestFocus()
                return@setOnClickListener
            }
            viewModel.theData.observe(viewLifecycleOwner) {
                if (it != null) {
                    val action = LoginFragmentDirections.actionLoginFragmentToNotesFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                } else {
                    snackBar()
                }
            }
            viewModel.loginData()
        }

        //forgot password text'ine iklaninca yapilacak islemler
        loginForgotPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            Navigation.findNavController(it).navigate(action)
        }

        //sign up now text'ine tiklaninca yapilacak islemler
        loginSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    //login ekranı icin hata mesajı
    fun snackBar() {

        val snackbar =
            Snackbar.make(loginScreenConstraintLayout, R.string.toast_message, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(resources.getColor(R.color.red))
        snackbar.setTextColor(resources.getColor(R.color.white))

        val view: View = snackbar.view
        //snackbar'ın location'ı ayarlandı
        val linearParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearParams.setMargins(0, 0, 0, 0)
        view.layoutParams = linearParams
        snackbar.show()
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(requireActivity()).isLoggedIn) {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_loginFragment_to_notesFragment)
        }
    }
}
