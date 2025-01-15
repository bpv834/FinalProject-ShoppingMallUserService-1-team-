package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frume.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// 바텀시트
class UserProductInfoDialogFragment  :BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_product_info_dialog, container, false)
    }


}