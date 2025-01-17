package com.example.frume.fragment_main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoMainBinding
import com.example.frume.fragment.home_fragment.my_info.UserAddressManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserCancelAndReturnFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoModifyFragment
import com.example.frume.fragment.home_fragment.my_info.UserOderDetailFragment
import com.example.frume.fragment.home_fragment.my_info.UserOrderHistoryFragment
import com.example.frume.fragment.home_fragment.my_info.UserPwModifyFragment
import com.example.frume.util.UserInfoType
import com.google.android.material.transition.MaterialSharedAxis
import com.google.firebase.firestore.auth.User


class UserInfoMainFragment(val bottomNavMainFragment: BottomNavMainFragment) : Fragment() {

    // 게시 판 타입값  -> 데이터번들로 받은것
    // 기본값은 유저 정보 넣음
    var userInfoType: UserInfoType = UserInfoType.USER_INFO_TYPE

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null

    lateinit var fragmentUserInfoMainFragment: FragmentUserInfoMainBinding
    lateinit var homeActivity: HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserInfoMainFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_info_main,
            container,
            false
        )

        // 다음부턴 bundle값에 따라 화면 분기
        arguments?.getInt("UserInfoType", UserInfoType.USER_INFO_TYPE.number)
        settingUserInfoType()
        Log.d("test100", "bundle 값: ${arguments?.getInt("UserInfoType")}")
        homeActivity = activity as HomeActivity
        replaceFragmentByArguments() // 여기서 받은 값에 따라 화면을 넘김.
        return fragmentUserInfoMainFragment.root
    }

    private fun replaceFragmentByArguments() {
        when (userInfoType) {
            // 유저 정보 0값
            UserInfoType.USER_INFO_TYPE -> {
                replaceFragment(UserInfoSubFragment.USER_INFO_FRAGMENT, false, false, null)
            }
            // 회원 주문 내역
            UserInfoType.USER_ORDER_HISTORY_TYPE -> {
                replaceFragment(UserInfoSubFragment.USER_ORDER_HISTORY_FRAGMENT, false, true, null)
            }

            // 주문 상세정보
            UserInfoType.USER_ORDER_DETAIL_TYPE -> {

            }

            // 주문 반품 및 취소
            UserInfoType.USER_CANCEL_AND_RETURN_TYPE -> {

            }

            // 회원 정보 수정
            UserInfoType.USER_INFO_MANAGE_TYPE -> {
                replaceFragment(UserInfoSubFragment.USER_INFO_MANAGE_FRAGMENT, false, true, null)
            }

            // 정보 수정
            UserInfoType.USER_INFO_MODIFY_TYPE -> {
            }

            // 회원 배송지 관리
            UserInfoType.USER_ADDRESS_MANAGE_TYPE -> {
                replaceFragment(UserInfoSubFragment.USER_ADDRESS_MANAGE_FRAGMENT, false, true, null)
            }

            // 비밀번호 변경하기
            UserInfoType.USER_INFO_MODIFY_PW_TYPE -> TODO()
        }

    }

    // 프래그먼트를 교체하는 함수
    fun replaceFragment(
        fragmentName: UserInfoSubFragment,
        isAddToBackStack: Boolean,
        animate: Boolean,
        dataBundle: Bundle?
    ) {
        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }
        // 프래그먼트 객체
        newFragment = when (fragmentName) {

            UserInfoSubFragment.USER_ORDER_HISTORY_FRAGMENT -> UserOrderHistoryFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_ORDER_DETAIL_FRAGMENT -> UserOderDetailFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_CANCEL_AND_RETURN_FRAGMENT -> UserCancelAndReturnFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_INFO_MANAGE_FRAGMENT -> UserInfoManageFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_INFO_MODIFY_FRAGMENT -> UserInfoModifyFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_ADDRESS_MANAGE_FRAGMENT -> UserAddressManageFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_PW_MODIFY_FRAGMENT -> UserPwModifyFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_INFO_FRAGMENT -> UserInfoFragment(this@UserInfoMainFragment)
        }

        // bundle 객체가 null이 아니라면
        if (dataBundle != null) {
            newFragment?.arguments = dataBundle
        }

        // 프래그먼트 교체
        homeActivity.supportFragmentManager.commit {
            if (animate) {
                // 만약 이전 프래그먼트가 있다면
                if (oldFragment != null) {
                    oldFragment?.exitTransition =
                        MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                    oldFragment?.reenterTransition =
                        MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
                }
                newFragment?.exitTransition =
                    MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                newFragment?.reenterTransition =
                    MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
                newFragment?.enterTransition =
                    MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                newFragment?.returnTransition =
                    MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
            }
            replace(R.id.containerUserInfoMain, newFragment!!)
            if (isAddToBackStack) {
                addToBackStack(fragmentName.str)
            }
        }
    }

    // 프래그먼트를 BackStack에서 제거하는 메서드
    fun removeFragment(fragmentName: UserInfoSubFragment) {
        homeActivity.supportFragmentManager.popBackStack(
            fragmentName.str,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    // 유저 보드 타입 값을 담는 메서드
    fun settingUserInfoType() {

        // 기본값으로 UserInfoType.USER_INFO_TYPE.number (0 값) 을 넣음 처음 화면을 키면 bundle이 없기때문에 기본값으로 0을 설정
        // 따라서 처음 UserInfoMainFragment에 오면 0값으로 인해 유저 정보를 열 수 있다.
        val tempType = arguments?.getInt("UserInfoType", UserInfoType.USER_INFO_TYPE.number)
        Log.d("test100", "tempType = ${tempType}")
        when (tempType) {
            UserInfoType.USER_INFO_TYPE.number->{
                // 0 값, 유저 정보
                userInfoType = UserInfoType.USER_INFO_TYPE
            }

            UserInfoType.USER_ORDER_HISTORY_TYPE.number -> {
                // 1 값, 유저 주문 내역
                userInfoType = UserInfoType.USER_ORDER_HISTORY_TYPE
            }

            UserInfoType.USER_ORDER_DETAIL_TYPE.number -> {
                // 2 값, 유저 주문 상세
                userInfoType = UserInfoType.USER_ORDER_DETAIL_TYPE
            }

            UserInfoType.USER_CANCEL_AND_RETURN_TYPE.number -> {
                // 3 값, 유저 주문 환불, 취소
                userInfoType = UserInfoType.USER_CANCEL_AND_RETURN_TYPE
            }

            UserInfoType.USER_INFO_MANAGE_TYPE.number -> {
                // 4 값, 유저 정보 관리
                userInfoType = UserInfoType.USER_INFO_MANAGE_TYPE
            }

            UserInfoType.USER_INFO_MODIFY_TYPE.number -> {
                // 5 값, 유저 정보 변경 및 회원 탈퇴
                userInfoType = UserInfoType.USER_INFO_MODIFY_TYPE
            }

            UserInfoType.USER_ADDRESS_MANAGE_TYPE.number -> {
                // 6 값, 유저 배송지 관리
                userInfoType = UserInfoType.USER_ADDRESS_MANAGE_TYPE
            }
        }
    }
}


enum class UserInfoSubFragment(var number: Int, var str: String) {
    // 유저 정보
    USER_INFO_FRAGMENT(0, "UserOrderHistoryFragment"),

    // 주문 내역 및 배송조회
    USER_ORDER_HISTORY_FRAGMENT(1, "UserOrderHistoryFragment"),

    // 주문 상세 내역
    USER_ORDER_DETAIL_FRAGMENT(2, "UserOrderDetailFragment"),

    // 주문 반품 및 취소
    USER_CANCEL_AND_RETURN_FRAGMENT(3, "UserCancelAndReturnFragment"),

    // 회원정보 관리 및 탈퇴
    USER_INFO_MANAGE_FRAGMENT(4, "UserInfoManageFragment"),

    // 회원 정보 수정
    USER_INFO_MODIFY_FRAGMENT(5, "UserInfoModifyFragment"),

    // 배송지 관리
    USER_ADDRESS_MANAGE_FRAGMENT(6, "UserAddressManageFragment"),

    // 비밀번호 변경하기
    USER_PW_MODIFY_FRAGMENT(7, "UserPwModifyFragment"),
}