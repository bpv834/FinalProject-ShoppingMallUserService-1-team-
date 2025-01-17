package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoBinding
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.fragment_main.BottomNavSubFragmentName
import com.example.frume.fragment_main.UserInfoMainFragment
import com.example.frume.fragment_main.UserInfoSubFragment
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
            // bottomNavMainFragment.(SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,true,true,dataBundle)

            // 구조 변경후 수정. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            // 화면 전환 방법 1. 같은 mainFragment에 존재할때 가능하다
            // 화면 전환 방법 2. 서로 다른 MainFragment 에 존재할 때 ex) UserMainFragment 하위에 있는 파일과, BottomNavMain에 존재하는 하위파일

            // 1번 방법(2번 방법 꼭 숙지해주세요)
            userInfoMain.replaceFragment(UserInfoSubFragment.USER_ORDER_HISTORY_FRAGMENT,true,true,null)

            // 2번 방법
            // UserInfoMain 에다가 번들 값을 넘겨주고, UserInfoMain은 replaceByArgument()를 이용해 번들값으로 자동으로 주문내역화면을 엽니다.
            // 중요한건 번들값을 bottomNavMain을 타서 BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT 값을 통해 UserInfoMain으로 접근하고
            // 거기서 번들값으로 주문내역화면을 띄웁니다.

            // userInfoMain.bottomNavMainFragment.replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT, true, true, dataBundle)
        }
    }

    // 배송지 관리 리스너
    fun onClickDeliverySpotManagement() {
        fragmentUserInfoBinding.textViewUserInfoShippingInfo.setOnClickListener {

            val dataBundle = Bundle()
            dataBundle.putInt("UserInfoType", UserInfoType.USER_ADDRESS_MANAGE_TYPE.number)

            // Log.d("test100", "UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number : ${UserInfoType.USER_ADDRESS_MANAGE_TYPE}")

            // 구조 수정으로 인해 변경
            // userInfoMain.combinationFragment.replaceFragment(SubMainFragmentName.USER_INFO_MAIN_FRAGMENT, true, true, dataBundle)

            // 구조 변경후 수정. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)

            //userInfoMain.replaceFragment(UserInfoSubFragment.USER_ADDRESS_MANAGE_FRAGMENT,true,true,null)
             userInfoMain.bottomNavMainFragment.replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT, true, true, dataBundle)
        }
    }

    // 후기 텍스트뷰 클릭 리스너
    fun onClickTextViewUserReview() {
        fragmentUserInfoBinding.TextViewUserInfoReview.setOnClickListener {
            val dataBundle = Bundle()
            dataBundle.putInt("UserInfoType", ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE.number)

            // 구조 수정으로 인해 변경
            //userInfoMain.combinationFragment.replaceFragment(SubMainFragmentName.PRODUCT_MAIN_FRAGMENT, true, true, dataBundle)

            // 주문내역, 배송지관리랑 접근법이 다름
            // 주문내역, 배송지 관리는 userInfoType을 넘겼다
            // 하지만 리뷰는 bottomNavMain에 없고, productMain에 있어서, 번들값을, 다른곳에 넘겼다.
            // 리뷰는 콤비에 있는 replace를 통해 ProductMain에 번들을 넘겼다.
            // 하지만 주문내역과 배송지관리는 BottomNavMain에 번들값을 넘겼다.
            // 구조 수정으로인한 수정후. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            userInfoMain.bottomNavMainFragment.combinationFragment.replaceFragment(SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,true,true,dataBundle)
        }
    }

    // 회원 정보 관리 및 탈퇴 리스너
    fun onClickUserInfoManagementOrLeave() {
        fragmentUserInfoBinding.textViewUserInfoAccountInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d(
                "test100",
                "현재 위치: UserInfoFragment///UserInfoType.USER_INFO_MANAGE_FRAGMENT.number : ${UserInfoType.USER_INFO_MANAGE_TYPE.number}"
            )
            dataBundle.putInt("UserInfoType", UserInfoType.USER_INFO_MANAGE_TYPE.number)

            // 구조 수정으로 인해 변경
            // userInfoMain.combinationFragment.replaceFragment(SubMainFragmentName.USER_INFO_MAIN_FRAGMENT, true, true, dataBundle)

            // 구조 변경후 수정. 변경점 : ( userInfFragment와 UserInfoMain 과 BottomNavMain이 연결됨. 이전엔 바텀네비와만 연결돼있었음 (UserInfo, bottomNav)
            // 같은 userInfoMain에 있기때문에 replace로도 접근가능하고, dataBundle로도 가능해 보인다. 밑에 코드와 주석 참조

            userInfoMain.replaceFragment(UserInfoSubFragment.USER_INFO_MANAGE_FRAGMENT,true,true,null)
            //userInfoMain.bottomNavMainFragment.replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT,true,true,dataBundle)

        }

    }


}