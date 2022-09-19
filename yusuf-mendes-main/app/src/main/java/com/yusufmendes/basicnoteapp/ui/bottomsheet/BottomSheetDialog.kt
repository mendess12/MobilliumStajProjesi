package com.yusufmendes.basicnoteapp.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yusufmendes.basicnoteapp.R

class BottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.success_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val succsessLogin: Button = view.findViewById(R.id.successLogin)
        succsessLogin.setOnClickListener {
            Toast.makeText(activity, "basarili", Toast.LENGTH_LONG).show()
            val action = BottomSheetDialogDirections.actionBottomSheetDialogToLoginFragment()
            findNavController().navigate(action)
        }
    }
}
