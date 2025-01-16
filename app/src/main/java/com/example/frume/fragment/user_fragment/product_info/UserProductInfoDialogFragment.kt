package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductInfoDialogBinding
import com.example.frume.fragment_main.ProductMainFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserProductInfoDialogFragment(val productMainFragment: ProductMainFragment) : BottomSheetDialogFragment() {

    lateinit var userProductInfoDialogBinding: FragmentUserProductInfoDialogBinding
    lateinit var homeActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeActivity = requireActivity() as HomeActivity  // Safe cast to HomeActivity

        userProductInfoDialogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_info_dialog, container, false)

        return userProductInfoDialogBinding.root
    }
}