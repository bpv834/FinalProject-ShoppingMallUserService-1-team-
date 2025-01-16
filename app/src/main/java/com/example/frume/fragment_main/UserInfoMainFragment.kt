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
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoMainBinding
import com.example.frume.fragment.CombinationFragment
import com.example.frume.fragment.home_fragment.my_info.UserAddressManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserCancelAndReturnFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoModifyFragment
import com.example.frume.fragment.home_fragment.my_info.UserOderDetailFragment
import com.example.frume.fragment.home_fragment.my_info.UserOrderHistoryFragment
import com.example.frume.fragment.home_fragment.my_info.UserPwModifyFragment
import com.example.frume.util.UserInfoType
import com.google.android.material.transition.MaterialSharedAxis


class UserInfoMainFragment(val combinationFragment: CombinationFragment) : Fragment() {

    // 게시 판 타입값
    lateinit var userInfoType: UserInfoType

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
        arguments?.getInt("UserInfoType")
        settingUserInfoType()
        Log.d("test100", "bundle 값: ${arguments?.getInt("UserInfoType")}")
        homeActivity = activity as HomeActivity
        replaceFragmentByArguments()
        return fragmentUserInfoMainFragment.root
    }

    fun replaceFragmentByArguments() {
        when (userInfoType) {
            UserInfoType.USER_ORDER_HISTORY_FRAGMENT -> {
                replaceFragment(UserInfoSubFragment.USER_ORDER_HISTORY_FRAGMENT, false, true, null)
            }

            UserInfoType.USER_ORDER_DETAIL_FRAGMENT -> {

            }

            UserInfoType.USER_CANCEL_AND_RETURN_FRAGMENT -> {

            }

            UserInfoType.USER_INFO_MANAGE_FRAGMENT -> {
                // 회원 정보관리
                 replaceFragment(UserInfoSubFragment.USER_INFO_MANAGE_FRAGMENT, false, true, null)
            }

            UserInfoType.USER_INFO_MODIFY_FRAGMENT -> {
                // 정보 수정
            }

            UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT -> {
                 replaceFragment(UserInfoSubFragment.USER_ADDRESS_MANAGE_FRAGMENT, false, true, null)

            }
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
        Log.d("test111", "old : $oldFragment")
        Log.d("test111", "new : $newFragment")
        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }
        // 프래그먼트 객체
        newFragment = when (fragmentName) {

            UserInfoSubFragment.USER_ORDER_HISTORY_FRAGMENT -> UserOrderHistoryFragment()
            UserInfoSubFragment.USER_ORDER_DETAIL_FRAGMENT -> UserOderDetailFragment()
            UserInfoSubFragment.USER_CANCEL_AND_RETURN_FRAGMENT -> UserCancelAndReturnFragment()
            UserInfoSubFragment.USER_INFO_MANAGE_FRAGMENT -> UserInfoManageFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_INFO_MODIFY_FRAGMENT -> UserInfoModifyFragment(this@UserInfoMainFragment)
            UserInfoSubFragment.USER_ADDRESS_MANAGE_FRAGMENT -> UserAddressManageFragment()
            UserInfoSubFragment.USER_PW_MODIFY_FRAGMENT -> UserPwModifyFragment(this@UserInfoMainFragment)
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

    // 게시판 타입 값을 담는 메서드
    fun settingUserInfoType() {
        val tempType = arguments?.getInt("UserInfoType")!!
        Log.d("test100", "tempType = ${tempType}")
        when (tempType) {

            UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number -> {
                // 0
                userInfoType = UserInfoType.USER_ORDER_HISTORY_FRAGMENT
            }

            UserInfoType.USER_ORDER_DETAIL_FRAGMENT.number -> {
                // 1
                userInfoType = UserInfoType.USER_ORDER_DETAIL_FRAGMENT
            }

            UserInfoType.USER_CANCEL_AND_RETURN_FRAGMENT.number -> {
                // 2
                userInfoType = UserInfoType.USER_CANCEL_AND_RETURN_FRAGMENT
            }

            UserInfoType.USER_INFO_MANAGE_FRAGMENT.number->{
                // 3
                userInfoType = UserInfoType.USER_INFO_MANAGE_FRAGMENT
            }

            UserInfoType.USER_INFO_MODIFY_FRAGMENT.number -> {
                // 4
                userInfoType = UserInfoType.USER_INFO_MODIFY_FRAGMENT
            }

            UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number -> {
                // 5
                userInfoType = UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT
            }

        }
    }


}






enum class UserInfoSubFragment(var number: Int, var str: String) {
    // 주문 내역 및 배송조회
    USER_ORDER_HISTORY_FRAGMENT(0, "UserOrderHistoryFragment"),

    // 주문 상세 내역
    USER_ORDER_DETAIL_FRAGMENT(1, "UserOrderDetailFragment"),

    // 주문 반품 및 취소
    USER_CANCEL_AND_RETURN_FRAGMENT(2, "UserCancelAndReturnFragment"),

    // 회원정보 관리 및 탈퇴
    USER_INFO_MANAGE_FRAGMENT(3, "UserInfoManageFragment"),

    // 회원 정보 수정
    USER_INFO_MODIFY_FRAGMENT(4, "UserInfoModifyFragment"),

    // 배송지 관리
    USER_ADDRESS_MANAGE_FRAGMENT(5, "UserAddressManageFragment"),

    // 비밀번호 변경하기
    USER_PW_MODIFY_FRAGMENT(6,"UserPwModifyFragment"),
}