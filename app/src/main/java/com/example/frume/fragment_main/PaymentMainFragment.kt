package com.example.frume.fragment_main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentPaymentMainBinding
import com.example.frume.fragment.CombinationFragment
import com.example.frume.fragment.user_fragment.user_cart.UserCartFragment
import com.example.frume.fragment.user_fragment.user_cart.UserCartFragment1
import com.example.frume.fragment.user_fragment.user_payment.UserPaymentScreenFragment
import com.example.frume.util.UserPaymentType
import com.google.android.material.transition.MaterialSharedAxis

class PaymentMainFragment(val combinationFragment: CombinationFragment) : Fragment() {

    // 게시판 타입 값
    lateinit var userPaymentType: UserPaymentType

    lateinit var fragmentPaymentMainFragment: FragmentPaymentMainBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPaymentMainFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_payment_main, container, false)

        // settingUserPaymentType 호출하여 arguments 값 처리
        // settingUserPaymentType()

        homeActivity = activity as HomeActivity

        // replaceFragmentByArguments 호출하여 fragment 변경
        // replaceFragmentByArguments()

        return fragmentPaymentMainFragment.root
    }

    /*// 게시판 타입 값을 담는 메서드
    fun settingUserPaymentType() {
        val tempType = arguments?.getInt("UserPaymentType") ?: 0 // Null이 아닌 기본값으로 처리
        Log.d("test100", "tempType = $tempType")

        userPaymentType = when (tempType) {
            UserPaymentType.USER_CART_FRAGMENT.number -> UserPaymentType.USER_CART_FRAGMENT
            UserPaymentType.USER_CART_FRAGMENT1.number -> UserPaymentType.USER_CART_FRAGMENT1
            UserPaymentType.USER_PAYMENT_SCREEN_FRAGMENT.number -> UserPaymentType.USER_PAYMENT_SCREEN_FRAGMENT
            else -> UserPaymentType.USER_CART_FRAGMENT // 기본값 설정
        }
    }

    // 프래그먼트를 교체하는 함수
    fun replaceFragmentByArguments() {
        when (userPaymentType) {
            UserPaymentType.USER_CART_FRAGMENT -> {
                replaceFragment(PaymentSubFragment.USER_CART_FRAGMENT, false, true, null)
            }

            UserPaymentType.USER_CART_FRAGMENT1 -> {
                replaceFragment(PaymentSubFragment.USER_CART_FRAGMENT1, false, true, null)
            }

            UserPaymentType.USER_PAYMENT_SCREEN_FRAGMENT -> {
                replaceFragment(PaymentSubFragment.USER_PAYMENT_SCREEN_FRAGMENT, false, true, null)
            }
        }
    }

    // 프래그먼트를 교체하는 함수
    fun replaceFragment(
        fragmentName: PaymentSubFragment,
        isAddToBackStack: Boolean,
        animate: Boolean,
        dataBundle: Bundle?
    ) {
        Log.d("test111", "old : $oldFragment")
        Log.d("test111", "new : $newFragment")

        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }

        // 프래그먼트 객체
        newFragment = when (fragmentName) {
            PaymentSubFragment.USER_CART_FRAGMENT -> UserCartFragment()
            PaymentSubFragment.USER_CART_FRAGMENT1 -> UserCartFragment1()
            PaymentSubFragment.USER_PAYMENT_SCREEN_FRAGMENT -> UserPaymentScreenFragment()
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
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                }


                newFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                newFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
            }
enum class PaymentSubFragment(var number:Int, var str:String){

    // 결제
    USER_PAYMENT_SCREEN_FRAGMENT(3,"UserPaymentScreenFragment"),


            replace(R.id.containerPaymentMain, newFragment!!)
            if (isAddToBackStack) {
                addToBackStack(fragmentName.str)
            }
        }
    }

    // 프래그먼트를 BackStack에서 제거하는 메서드
    fun removeFragment(fragmentName: PaymentSubFragment) {
        homeActivity.supportFragmentManager.popBackStack(fragmentName.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}*/

    enum class PaymentSubFragment(var number: Int, var str: String) {

        USER_PAYMENT_SCREEN_FRAGMENT(3, "UserPaymentScreenFragment"),
    }
}
