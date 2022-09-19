package com.yusufmendes.basicnoteapp.ui.profile

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.databinding.FragmentProfileBinding
import com.yusufmendes.basicnoteapp.di.RetrofitClient
import com.yusufmendes.basicnoteapp.model.profile.ProfileResponse
import com.yusufmendes.basicnoteapp.util.extension.changeButtonState
import com.yusufmendes.basicnoteapp.util.extension.isEmailValid
import com.yusufmendes.basicnoteapp.util.storage.SharedPrefManager
import com.yusufmendes.basicnoteapp.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    val retrofitClient = RetrofitClient()
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
        viewModel = ViewModelProviders.of(requireActivity()).get(ProfileViewModel::class.java)

        binding.profileEmailEditText.addTextChangedListener{text ->
            binding.profileSaveButton.changeButtonState(text.toString().isEmailValid())
        }

        //sunucudan kullanıcının fullname ve email verilerini cekip editTextlerde gosterme islemleri
        viewLifecycleOwner.lifecycleScope.launch {
            retrofitClient.retrofitInstance()
                .getUser("Bearer " + SharedPrefManager.getInstance(requireContext()).data.accessToken)
                .enqueue(object : Callback<ProfileResponse> {
                    override fun onResponse(
                        call: Call<ProfileResponse>,
                        response: Response<ProfileResponse>,
                    ) {
                        val fullName = response.body()!!.profileData.fullName
                        val email = response.body()!!.profileData.email
                        binding.profileFullNameEditText.setText(fullName)
                        binding.profileEmailEditText.setText(email)
                    }

                    override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                        Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                })
        }

        //save buttonuna tiklayinca yapilacak islemler
        binding.profileSaveButton.setOnClickListener {
            //snackBar()
            viewModel.fullName = binding.profileFullNameEditText.text.toString()
            viewModel.email = binding.profileEmailEditText.text.toString()

            viewModel.updateMe()
            val action = ProfileFragmentDirections.actionProfileFragmentToNotesFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        //change password text'ine tiklayinca yapilacak islemler
        binding.profileChangePassword.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment()
            Navigation.findNavController(it).navigate(action)
        }
        //sign out text'ine tiklayinca yapilacak islemler
        binding.profileSignOut.setOnClickListener {
            SharedPrefManager.getInstance(requireActivity()).clear()
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
        //icon menu'ye tiklayinca yapilacak islemler
        binding.notesScreenIconMenu.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToNotesFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    //profile ekranı icin hata mesajı
    fun snackBar() {
        val snackbar = Snackbar.make(
            profileScreenConstraintLayout,
            R.string.toast_message,
            Snackbar.LENGTH_LONG
        )
        snackbar.setBackgroundTint(resources.getColor(R.color.green))
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
}
