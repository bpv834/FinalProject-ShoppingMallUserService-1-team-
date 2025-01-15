package com.example.frume.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentBottomNavBinding
import com.example.frume.fragment.home_fragment.my_info.UserInfoFragment
import com.example.frume.fragment.home_fragment.user_home.UserHomeFragment
import com.example.frume.fragment.home_fragment.user_home.UserProductShowListFragment
import com.example.frume.fragment.user_fragment.category.UserCategoryFragment
import com.example.frume.fragment.user_fragment.user_cart.UserCartFragment
import com.google.android.material.transition.MaterialSharedAxis


class BottomNavFragment(val combinationFragment: CombinationFragment) : Fragment() {

    lateinit var bottomNavBinding: FragmentBottomNavBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomNavBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_nav,container,false)
        homeActivity = activity as HomeActivity
        // Inflate the layout for this fragment
        openHome()
        onClickBottomNavigationItem()
        return bottomNavBinding.root
    }



    // 첫 프레그트로 Home 으로 설정, 바텀 nav 아이템이 home에 클릭되도록 설정
    fun openHome() {
        replaceFragment(BottomNavSubFragmentName.USER_HOME_FRAGMENT,true,true,null)
        bottomNavBinding.bottomNavMain.selectedItemId = R.id.menu_home

    }

    fun onClickBottomNavigationItem() {
        bottomNavBinding.bottomNavMain.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.menu_category->{
                    replaceFragment(BottomNavSubFragmentName.USER_CATEGORY_FRAGMENT,true,false,null)
                }
                R.id.menu_home->{
                    replaceFragment(BottomNavSubFragmentName.USER_HOME_FRAGMENT,true,false,null)
                }
                R.id.menu_profile->{
                    replaceFragment(BottomNavSubFragmentName.USER_INFO_FRAGMENT,true,false,null)
                }
                R.id.menu_cart->{
                    replaceFragment(BottomNavSubFragmentName.USER_CART_FRAGMENT,true,false,null)
                }
            }
            true
        }

    }


    // 프래그먼트를 교체하는 함수
    fun replaceFragment(fragmentName: BottomNavSubFragmentName, isAddToBackStack:Boolean, animate:Boolean, dataBundle: Bundle?){
        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.

        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }
        // 프래그먼트 객체
        newFragment = when(fragmentName){
            // 로그인
            BottomNavSubFragmentName.USER_HOME_FRAGMENT -> UserHomeFragment(this@BottomNavFragment)
            BottomNavSubFragmentName.USER_INFO_FRAGMENT -> UserInfoFragment(this)
            BottomNavSubFragmentName.USER_CATEGORY_FRAGMENT -> UserCategoryFragment()
            BottomNavSubFragmentName.USER_CART_FRAGMENT -> UserCartFragment()
            BottomNavSubFragmentName.USER_PRODUCT_SHOW_LIST_FRAGMENT -> UserProductShowListFragment()
        }

        // bundle 객체가 null이 아니라면
        if(dataBundle != null){
            newFragment?.arguments = dataBundle
        }

        // 프래그먼트 교체
        homeActivity.supportFragmentManager.commit {

            if(animate) {
                // 만약 이전 프래그먼트가 있다면
                if(oldFragment != null){
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
                }

                newFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                newFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
            }

            replace(R.id.containerBottomNav, newFragment!!)
            if(isAddToBackStack){
                addToBackStack(fragmentName.str)
            }
        }
    }

    // 프래그먼트를 BackStack에서 제거하는 메서드
    fun removeFragment(fragmentName: SubMainFragmentName){
        homeActivity.supportFragmentManager.popBackStack(fragmentName.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}

// 하위 프래그먼트들의 이름
enum class BottomNavSubFragmentName(var number:Int, var str:String){
    // 카테고리
    USER_CATEGORY_FRAGMENT(0,"UserCategoryFragment"),
    // 홈
    USER_HOME_FRAGMENT(1, "UserHomeFragment"),
    // 내정보
    USER_INFO_FRAGMENT(2,"UserInfoFragment"),
    // 장바구니
    USER_CART_FRAGMENT(3,"UserCartFragment"),
    // 상품 리스트 뷰
    USER_PRODUCT_SHOW_LIST_FRAGMENT(4,"userProductShowListFragment")
}

