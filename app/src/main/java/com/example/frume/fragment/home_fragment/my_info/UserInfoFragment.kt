package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoBinding
import com.example.frume.fragment.BottomNavFragment
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.fragment_main.ProductSubFragment
import com.example.frume.util.ProductInfoType
import com.example.frume.util.UserInfoType

class UserInfoFragment(val bottomNavFragment: BottomNavFragment) : Fragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserInfoBinding: FragmentUserInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)
        homeActivity = activity as HomeActivity
        // 주문 내역 리스너 실행
        onClickOrderHistory()
        // 배송지 관리 리스너 실행
        onClickDeliverySpotManagement()
        // 회원 관리 및 탈퇴 리스너 실행
        onClickUserInfoManagementOrLeave()
        // 후기 리스너 실행
        onClickTextViewUserReview()
        return fragmentUserInfoBinding.root
    }



    // 주문 내역 리스너
    fun onClickOrderHistory() {
        fragmentUserInfoBinding.textViewUserInfoOrderHistory.setOnClickListener {
            val dataBundle = Bundle()
            Log.d("test100","UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number : ${UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number}")
            dataBundle.putInt("UserInfoType", UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number)
           bottomNavFragment.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )
        }
    }

    // 배송지 관리 리스너
    fun onClickDeliverySpotManagement() {
        fragmentUserInfoBinding.textViewUserInfoShippingInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d("test100","UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number : ${UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT}")

            dataBundle.putInt("UserInfoType", UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number)
            bottomNavFragment.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )
        }
    }

    // 후기 텍스트뷰 클릭 리스너
    fun onClickTextViewUserReview() {
        fragmentUserInfoBinding.TextViewUserInfoReview.setOnClickListener {
            val dataBundle = Bundle()
            dataBundle.putInt("ProductInfoType",ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE.number)
            bottomNavFragment.combinationFragment.replaceFragment(SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,true,true,dataBundle)
        }
    }

    // 회원 정보 및 탈퇴 리스너
    fun onClickUserInfoManagementOrLeave() {
        fragmentUserInfoBinding.textViewUserInfoAccountInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d("test100","UserInfoType.USER_INFO_MANAGE_FRAGMENT.number : ${UserInfoType.USER_INFO_MANAGE_FRAGMENT.number}")

            dataBundle.putInt("UserInfoType", UserInfoType.USER_INFO_MANAGE_FRAGMENT.number)
            bottomNavFragment.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )
        }

    }


}