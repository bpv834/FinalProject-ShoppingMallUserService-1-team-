package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductInfoReviewBinding
import com.example.frume.fragment_main.ProductMainFragment


class UserProductInfoReviewFragment (val productMainFragment: ProductMainFragment): Fragment() {

    lateinit var fragmentUserProductInfoReviewBinding: FragmentUserProductInfoReviewBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserProductInfoReviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_info_review, container, false)
        homeActivity = activity as HomeActivity
        // test
        Log.d("test100", "UserProductInfoFragment")

        return fragmentUserProductInfoReviewBinding.root
    }


}