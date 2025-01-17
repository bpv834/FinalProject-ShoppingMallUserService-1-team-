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
import com.example.frume.databinding.FragmentBottomNavMainBinding
import com.example.frume.fragment.CombinationFragment
import com.example.frume.fragment.home_fragment.user_home.UserHomeFragment
import com.example.frume.fragment.home_fragment.user_home.UserSearchFragment
import com.example.frume.fragment.user_fragment.category.UserCategoryDetailFragment
import com.example.frume.fragment.user_fragment.category.UserCategoryFragment
import com.example.frume.fragment.user_fragment.user_cart.UserCartFragment
import com.example.frume.util.BottomNavSubType
import com.google.android.material.transition.MaterialSharedAxis


class BottomNavMainFragment(val combinationFragment: CombinationFragment) : Fragment() {

    // 바텀상태값  -> 데이터번들로 받은것, 네비게이션 아이템으로 변경하는것
    var bottomNavType: BottomNavSubType = BottomNavSubType.BOTTOM_NAV_SUB_HOME_TYPE

    // 바텀네브메인이 콤비랑 엮여야 함
    lateinit var fragmentBottomNavMainBinding: FragmentBottomNavMainBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBottomNavMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_nav_main, container, false)
        homeActivity = activity as HomeActivity

        Log.d("test100", "bottomNavType : $bottomNavType")
        // 화면 분기하여 프레그먼트 실행하는 메서드. 처음엔 상단에 type을 Home으로 하기에 시작하자마자 Home 이 실행됨
        replaceFragmentByArguments()
        // bottomNav 클릭 리스너 메서드
        onClickBottomNavigationItem()
        return fragmentBottomNavMainBinding.root

    }
    // bottomNav 클릭 리스너
    fun onClickBottomNavigationItem() {
        fragmentBottomNavMainBinding.bottomNavMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_category -> {
                    replaceFragment(
                        BottomNavSubFragmentName.BOTTOM_NAV_SUB_CATEGORY_FRAGMENT,
                        true,
                        false,
                        null
                    )
                }

                R.id.menu_home -> {
                    replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_HOME_FRAGMENT, true, false, null)
                }

                R.id.menu_profile -> {
                    replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT, true, false, null)
                }

                R.id.menu_cart -> {
                    replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_CART_FRAGMENT, true, false, null)
                }
            }
            true
        }
    }


    // 프래그먼트를 교체하는 함수
    fun replaceFragment(
        fragmentName: BottomNavSubFragmentName,
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
            BottomNavSubFragmentName.BOTTOM_NAV_SUB_CATEGORY_FRAGMENT -> UserCategoryFragment(this@BottomNavMainFragment)
            BottomNavSubFragmentName.BOTTOM_NAV_SUB_HOME_FRAGMENT -> UserHomeFragment(this@BottomNavMainFragment)
            BottomNavSubFragmentName.BOTTOM_NAV_SUB_CART_FRAGMENT -> UserCartFragment(this@BottomNavMainFragment)
            BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT -> UserInfoMainFragment(this@BottomNavMainFragment)
            BottomNavSubFragmentName.BOTTOM_NAV_SUB_HOME_SEARCH_FRAGMENT -> UserSearchFragment(this@BottomNavMainFragment)
            BottomNavSubFragmentName.BOTTOM_NAV_SUB_CATEGORY_DETAIL_FRAGMENT -> UserCategoryDetailFragment(this@BottomNavMainFragment)
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

            replace(R.id.containerNavMain, newFragment!!)
            if (isAddToBackStack) {
                addToBackStack(fragmentName.str)
            }
        }
    }

    // 프래그먼트를 BackStack에서 제거하는 메서드
    fun removeFragment(fragmentName: BottomNavSubFragmentName) {
        homeActivity.supportFragmentManager.popBackStack(
            fragmentName.str,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    // UserInfoType 필드로 분기하여 화면을 전환한다
    private fun replaceFragmentByArguments() {
        when (bottomNavType) {
            BottomNavSubType.BOTTOM_NAV_SUB_CATEGORY_TYPE -> replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_HOME_FRAGMENT,true,true,null)
            BottomNavSubType.BOTTOM_NAV_SUB_HOME_TYPE -> replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_HOME_FRAGMENT,false,false,null)
            BottomNavSubType.BOTTOM_NAV_SUB_CART_TYPE -> replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_CART_FRAGMENT,true,true,null)
            BottomNavSubType.BOTTOM_NAV_SUB_USER_INFO_MAIN_TYPE -> replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT,true,true,null)
            BottomNavSubType.BOTTOM_NAV_SUB_SEARCH_TYPE -> replaceFragment(BottomNavSubFragmentName.BOTTOM_NAV_SUB_HOME_SEARCH_FRAGMENT,true,true,null)
        }

    }


}

enum class BottomNavSubFragmentName(var number: Int, var str: String) {
    // 카테고리
    BOTTOM_NAV_SUB_CATEGORY_FRAGMENT(0, "카테고리"),

    // 홈
    BOTTOM_NAV_SUB_HOME_FRAGMENT(1, "홈"),

    // 장바구니
    BOTTOM_NAV_SUB_CART_FRAGMENT(2, "장바구니"),

    // 내정보 -> UserInfoFragment 에서 UserInfoMainFragment를 부르는것으로 변경 hj 25.01.17
    BOTTOM_NAV_SUB_USER_INFO_MAIN_FRAGMENT(3, "유저 메인"),

    // 서치 뷰 -> 원래 이것도 UserInfoMain에서 분기 값에 따라 화면을 나눠야 하기때문에 HomeMainFragment에서 홈, 서치뷰를 관리 해야 하는데 두개뿐이라 안나눔 25.01.17 hj
    BOTTOM_NAV_SUB_HOME_SEARCH_FRAGMENT(4,"검색 화면"),

    // 카테고리 디테일 -> 원래 이것도 CategoryMain 에서 분기값에 따라 카테고리 화면을 띄울지 카테고리 디테일을 띄울 지 해줘야 하는데, 두개뿐이라 여기 만듦 25.01.17 hj
    BOTTOM_NAV_SUB_CATEGORY_DETAIL_FRAGMENT(5,"카테고리 상세")
}
