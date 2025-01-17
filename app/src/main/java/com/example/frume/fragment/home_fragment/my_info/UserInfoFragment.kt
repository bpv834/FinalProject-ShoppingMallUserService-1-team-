package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoBinding
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.fragment_main.BottomNavSubFragmentName
import com.example.frume.fragment_main.UserInfoMainFragment
import com.example.frume.util.ProductInfoType
import com.example.frume.util.UserInfoType

class UserInfoFragment(val userInfoMain: UserInfoMainFragment) : Fragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserInfoBinding: FragmentUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)
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
            Log.d(
                "test100",
                "UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number : ${UserInfoType.USER_ORDER_HISTORY_TYPE.number}"
            )
            dataBundle.putInt("UserInfoType", UserInfoType.USER_ORDER_HISTORY_TYPE.number)

            // 구조 수정으로 인한 변경
            /*       bottomNavMainFragment.(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )*/

            // 구조 변경후 수정. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            userInfoMain.bottomNavMainFragment.replaceFragment(
                BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_FRAGMENT, true,
                true,
                dataBundle
            )
        }
    }

    // 배송지 관리 리스너
    fun onClickDeliverySpotManagement() {
        fragmentUserInfoBinding.textViewUserInfoShippingInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d(
                "test100",
                "UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number : ${UserInfoType.USER_ADDRESS_MANAGE_TYPE}"
            )

            dataBundle.putInt("UserInfoType", UserInfoType.USER_ADDRESS_MANAGE_TYPE.number)
            // 구조 수정으로 인해 변경
            /*   userInfoMain.combinationFragment.replaceFragment(
                   SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                   true,
                   true,
                   dataBundle
               )*/

            // 구조 변경후 수정. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            userInfoMain.bottomNavMainFragment.replaceFragment(
                BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_FRAGMENT, true,
                true,
                dataBundle
            )
        }
    }

    // 후기 텍스트뷰 클릭 리스너
    fun onClickTextViewUserReview() {
        fragmentUserInfoBinding.TextViewUserInfoReview.setOnClickListener {
            val dataBundle = Bundle()
            dataBundle.putInt(
                "ProductInfoType",
                ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE.number
            )
            // 구조 수정으로 인해 변경
            /*    userInfoMain.combinationFragment.replaceFragment(
                    SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,
                    true,
                    true,
                    dataBundle
                )*/

            // 구조 수정으로인한 수정후. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            userInfoMain.bottomNavMainFragment.replaceFragment(
                BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_FRAGMENT, true,
                true,
                dataBundle
            )
        }
    }

    // 회원 정보 및 탈퇴 리스너
    fun onClickUserInfoManagementOrLeave() {
        fragmentUserInfoBinding.textViewUserInfoAccountInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d(
                "test100",
                "UserInfoType.USER_INFO_MANAGE_FRAGMENT.number : ${UserInfoType.USER_INFO_MANAGE_TYPE.number}"
            )

            // 구조 수정으로 인해 변경
            /*dataBundle.putInt("UserInfoType", UserInfoType.USER_INFO_MANAGE_TYPE.number)
            userInfoMain.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )*/

            // 구조 변경후 수정. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            userInfoMain.bottomNavMainFragment.replaceFragment(
                BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_FRAGMENT, true,
                true,
                dataBundle
            )
        }

    }


}