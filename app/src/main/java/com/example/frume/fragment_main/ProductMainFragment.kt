package com.example.frume.fragment_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentProductMainBinding
import com.example.frume.fragment.CombinationFragment


class ProductMainFragment(val combinationFragment: CombinationFragment) : Fragment() {

    lateinit var fragmentProductMainBinding : FragmentProductMainBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentProductMainBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_main,container,false)
        homeActivity = activity as HomeActivity
        return fragmentProductMainBinding.root
    }


}

enum class ProductSubFragment(var number:Int, var str:String){
    // 상품
    USER_PRODUCT_INFO_FRAGMENT(0,"UserProductInfoFragment"),
    // 상품 설명
    USER_PRODUCT_INFO_DESCRIPTION_FRAGMENT(1, "UserProductInfoDescription"),
    // 상품 상세 정보
    USER_PRODUCT_INFO_DETAIL_FRAGMENT(2,"UserProductInfoDetailFragment"),
    // 상품 후기
    USER_PRODUCT_INFO_REVIEW_FRAGMENT(3,"UserProductInfoReviewFragment"),
    // 상품 후기 작성
    USER_PRODUCT_INFO_WRITE_REVIEW_FRAGMENT(4,"UserProductWriteReviewFragment"),
    // 상품 주문 다이얼로그
    USER_PRODUCT_INFO_DIALOG_FRAGMENT(5,"UserProductInfoDialogFragment")

}