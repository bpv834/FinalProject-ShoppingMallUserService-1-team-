package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductInfoWriteReviewBinding
import com.example.frume.fragment_main.ProductMainFragment
import com.example.frume.fragment_main.ProductSubFragment
import com.example.frume.fragment_main.UserInfoSubFragment


class UserProductInfoWriteReviewFragment(val productMainFragment: ProductMainFragment) : Fragment() {

    lateinit var fragmentUserProductInfoWriteReviewBinding: FragmentUserProductInfoWriteReviewBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentUserProductInfoWriteReviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_info_write_review, container, false)
        homeActivity = activity as HomeActivity
        // Inflate the layout for this fragment

        // 후기 작성 완료 버튼
        onClickButtonReviewSubmit()
        return fragmentUserProductInfoWriteReviewBinding.root
    }
    fun onClickButtonReviewSubmit() {
        fragmentUserProductInfoWriteReviewBinding.buttonUserProductInfoReviewConfirm.setOnClickListener {
            productMainFragment.removeFragment(ProductSubFragment.USER_PRODUCT_INFO_WRITE_REVIEW_FRAGMENT)
        }
    }

}