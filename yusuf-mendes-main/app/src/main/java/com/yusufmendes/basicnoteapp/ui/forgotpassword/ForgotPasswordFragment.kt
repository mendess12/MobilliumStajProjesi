package com.yusufmendes.basicnoteapp.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.databinding.FragmentForgotPasswordBinding
import com.yusufmendes.basicnoteapp.util.extension.changeButtonState
import com.yusufmendes.basicnoteapp.util.extension.isEmailValid
import com.yusufmendes.basicnoteapp.viewmodel.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forgot_password.*

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)
        viewModel =
            ViewModelProviders.of(requireActivity()).get(ForgotPasswordViewModel::class.java)

        binding.forgotPasswordScreenEmailEditText.addTextChangedListener { text ->
            binding.forgotPasswordResetButton.changeButtonState(text.toString().isEmailValid())
        }

        //reset password buttonuna tiklaninca yapilacak islemler
        binding.forgotPasswordResetButton.setOnClickListener {

            viewModel.email = binding.forgotPasswordScreenEmailEditText.text.toString()

            if (viewModel.email.isEmpty()) {
                binding.forgotPasswordScreenEmailEditText.error = "This area can not be empty"
                binding.forgotPasswordScreenEmailEditText.requestFocus()
                return@setOnClickListener
            }
            observerLiveData()
        }

        include.setOnClickListener {
            //val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
            Navigation.findNavController(it).popBackStack()
        }
    }

    //bottomSheet islemleri
    fun bottomSheet() {
        val action =
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToBottomSheetDialog()
        Navigation.findNavController(binding.forgotPasswordConstraintLayout).navigate(action)
    }

    fun observerLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it != null) {
                bottomSheet()
            }
        }
        viewModel.forgotPasswordData()
    }

}
