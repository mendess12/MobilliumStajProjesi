package com.yusufmendes.basicnoteapp.ui.changepassword

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
import com.yusufmendes.basicnoteapp.databinding.FragmentChangePasswordBinding
import com.yusufmendes.basicnoteapp.di.RetrofitClient
import com.yusufmendes.basicnoteapp.util.constants.Constants.MIN_PASSWORD_LENGTH
import com.yusufmendes.basicnoteapp.util.extension.changeButtonState
import com.yusufmendes.basicnoteapp.util.extension.isPasswordValid
import com.yusufmendes.basicnoteapp.viewmodel.ChangePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding
    val retrofitClient = RetrofitClient()
    private lateinit var viewModel: ChangePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangePasswordBinding.bind(view)
        viewModel =
            ViewModelProviders.of(requireActivity()).get(ChangePasswordViewModel::class.java)

        binding.changeNewPasswordEditText.addTextChangedListener { text ->
            binding.changeSaveButton.changeButtonState(text.toString()
                .isPasswordValid(MIN_PASSWORD_LENGTH))
        }

        binding.changeRetypeNewPasswordEditText.addTextChangedListener { text ->
            binding.changeSaveButton.changeButtonState(text.toString()
                .isPasswordValid(MIN_PASSWORD_LENGTH))
        }

        binding.changeSaveButton.setOnClickListener {

            viewModel.password = binding.changePasswordEditText.text.toString().trim()
            viewModel.newPassword = binding.changeNewPasswordEditText.text.toString().trim()
            viewModel.retypeNewPassword =
                binding.changeRetypeNewPasswordEditText.text.toString().trim()

            if (viewModel.password.isEmpty()) {
                binding.changePasswordEditText.error = "This area can not be empty"
                binding.changePasswordEditText.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.newPassword.isEmpty()) {
                binding.changeNewPasswordEditText.error = "This area can not be empty"
                binding.changeNewPasswordEditText.requestFocus()
                return@setOnClickListener
            }
            if (viewModel.retypeNewPassword.isEmpty()) {
                binding.changeRetypeNewPasswordEditText.error = "This area can not be empty"
                binding.changeRetypeNewPasswordEditText.requestFocus()
                return@setOnClickListener
            }
            viewModel.liveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    val action =
                        ChangePasswordFragmentDirections.actionChangePasswordFragmentToNotesFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                } else {
                    Toast.makeText(activity,
                        "The new password and retype new password is not match!",
                        Toast.LENGTH_LONG).show()
                }
            }
            viewModel.changePassword()
        }

        binding.notesScreenIconMenu.setOnClickListener {
            val action =
                ChangePasswordFragmentDirections.actionChangePasswordFragmentToNotesFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
