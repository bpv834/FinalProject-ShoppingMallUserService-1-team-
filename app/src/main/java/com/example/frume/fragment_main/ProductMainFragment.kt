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
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentProductMainBinding
import com.example.frume.fragment.CombinationFragment
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.fragment.home_fragment.my_info.UserAddressManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserCancelAndReturnFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoModifyFragment
import com.example.frume.fragment.home_fragment.my_info.UserOderDetailFragment
import com.example.frume.fragment.home_fragment.my_info.UserOrderHistoryFragment
import com.example.frume.fragment.home_fragment.my_info.UserPwModifyFragment
import com.example.frume.fragment.home_fragment.user_home.UserProductShowListFragment
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoDescriptionFragment
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoDetailFragment
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoDialogFragment
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoFragment
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoReviewFragment
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoWriteReviewFragment
import com.google.android.material.transition.MaterialSharedAxis


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
        // test
        Log.d("test100","productMainFragment")

        replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_FRAGMENT,true,true,null)
        return fragmentProductMainBinding.root
    }

    // 프래그먼트를 교체하는 함수
    fun replaceFragment(
        fragmentName: ProductSubFragment,
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
            ProductSubFragment.USER_PRODUCT_INFO_FRAGMENT -> UserProductInfoFragment(this@ProductMainFragment)
            ProductSubFragment.USER_PRODUCT_INFO_DESCRIPTION_FRAGMENT -> UserProductInfoDescriptionFragment(this@ProductMainFragment)
            ProductSubFragment.USER_PRODUCT_INFO_DETAIL_FRAGMENT -> UserProductInfoDetailFragment(this@ProductMainFragment)
            ProductSubFragment.USER_PRODUCT_INFO_REVIEW_FRAGMENT -> UserProductInfoReviewFragment(this@ProductMainFragment)
            ProductSubFragment.USER_PRODUCT_INFO_WRITE_REVIEW_FRAGMENT -> UserProductInfoWriteReviewFragment(this@ProductMainFragment)
            ProductSubFragment.USER_PRODUCT_INFO_DIALOG_FRAGMENT -> UserProductInfoDialogFragment(this)
            ProductSubFragment.USER_PRODUCT_SHOW_LIST_FRAGMENT -> UserProductShowListFragment()
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
            replace(R.id.containerProductMain, newFragment!!)
            if (isAddToBackStack) {
                addToBackStack(fragmentName.str)
            }
        }
    }

    // 프래그먼트를 BackStack에서 제거하는 메서드
    fun removeFragment(fragmentName: ProductSubFragment) {
        homeActivity.supportFragmentManager.popBackStack(
            fragmentName.str,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
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
    USER_PRODUCT_INFO_DIALOG_FRAGMENT(5,"UserProductInfoDialogFragment"),
    // 상품 리스트 뷰
    USER_PRODUCT_SHOW_LIST_FRAGMENT(6,"userProductShowListFragment")
    // 구매 뷰


}