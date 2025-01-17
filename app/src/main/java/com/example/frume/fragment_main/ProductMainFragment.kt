package com.example.frume.fragment_main

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
import com.example.frume.util.ProductInfoType
import com.example.frume.util.UserInfoType
import com.google.android.material.transition.MaterialSharedAxis


class ProductMainFragment(val combinationFragment: CombinationFragment) : Fragment() {


    lateinit var productInfoType: ProductInfoType
    lateinit var fragmentProductMainBinding : FragmentProductMainBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentProductMainBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_main,container,false)
        homeActivity = activity as HomeActivity
        arguments?.getInt("ProductInfoType")
        Log.d("test100","arg : ${arguments}")

        if(arguments == null){
            replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_FRAGMENT,true,true,null)
        }else{
            settingUserInfoType()

            // 아규먼트를 이용해  화면 분기에 사용할 UserInfoType을 지정해줌 아규면트가 없다면
            replaceFragmentByArguments()
        }
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

    // 유저 보드 타입 값을 담는 메서드
    fun settingUserInfoType() {
        val tempType = arguments?.getInt("ProductInfoType")!!
            Log.d("test100", "tempType = ${tempType}")
            when (tempType) {
                // 상품 0
                ProductInfoType.USER_PRODUCT_INFO_TYPE.number->{
                    productInfoType = ProductInfoType.USER_PRODUCT_INFO_TYPE
                }

                // 상품 설명 1
                ProductInfoType.USER_PRODUCT_INFO_DESCRIPTION_TYPE.number ->{
                    productInfoType = ProductInfoType.USER_PRODUCT_INFO_DESCRIPTION_TYPE
                }
                // 상품 상세 정보 2
                ProductInfoType.USER_PRODUCT_INFO_DETAIL_TYPE.number->{
                    productInfoType = ProductInfoType.USER_PRODUCT_INFO_DETAIL_TYPE
                }
                // 상품 후기 3
                ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE.number-> {
                    productInfoType = ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE
                }
                // 상품 후기 작성 4
                ProductInfoType.USER_PRODUCT_INFO_WRITE_TYPE.number->{
                    productInfoType = ProductInfoType.USER_PRODUCT_INFO_WRITE_TYPE
                }
                // 상품 주문 다이얼로그 5
                ProductInfoType.USER_PRODUCT_INFO_DIALOG_TYPE.number->{
                    productInfoType = ProductInfoType.USER_PRODUCT_INFO_DIALOG_TYPE
                }
                // 상품 리스트 뷰
                ProductInfoType.USER_PRODUCT_SHOW_LIST_TYPE.number->{
                    productInfoType = ProductInfoType.USER_PRODUCT_SHOW_LIST_TYPE

            }
        }
    }

    // UserInfoType 필드로 분기하여 화면을 전환한다
    private fun replaceFragmentByArguments() {
        when (productInfoType) {
            // 상품 ( 탭레이아웃을 갖고 있는 프레그먼트)
            ProductInfoType.USER_PRODUCT_INFO_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_FRAGMENT,true,true,null)
            // 상품 설명
            ProductInfoType.USER_PRODUCT_INFO_DESCRIPTION_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_DESCRIPTION_FRAGMENT,true,true,null)
            // 상품 상세 설명
            ProductInfoType.USER_PRODUCT_INFO_DETAIL_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_DETAIL_FRAGMENT,true,true,null)
            // 상품 리뷰
            ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_REVIEW_FRAGMENT,true,true,null)
            // 상품 리뷰 쓰기
            ProductInfoType.USER_PRODUCT_INFO_WRITE_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_WRITE_REVIEW_FRAGMENT,true,true,null)
            // 상품 구매 바텀시트
            ProductInfoType.USER_PRODUCT_INFO_DIALOG_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_DIALOG_FRAGMENT,true,true,null)
            // 상품 리스트
            ProductInfoType.USER_PRODUCT_SHOW_LIST_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_SHOW_LIST_FRAGMENT,true,true,null)
        }

    }



            // 상품 0
            ProductInfoType.USER_PRODUCT_INFO_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_INFO_TYPE
            }
            // 상품 설명 1
            ProductInfoType.USER_PRODUCT_INFO_DESCRIPTION_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_INFO_DESCRIPTION_TYPE
            }
            // 상품 상세 정보 2
            ProductInfoType.USER_PRODUCT_INFO_DETAIL_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_INFO_DETAIL_TYPE
            }
            // 상품 후기 3
            ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE
            }
            // 상품 후기 작성 4
            ProductInfoType.USER_PRODUCT_INFO_WRITE_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_INFO_WRITE_TYPE
            }
            // 상품 주문 다이얼로그 5
            ProductInfoType.USER_PRODUCT_INFO_DIALOG_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_INFO_DIALOG_TYPE
            }
            // 상품 리스트 뷰
            ProductInfoType.USER_PRODUCT_SHOW_LIST_TYPE.number -> {
                productInfoType = ProductInfoType.USER_PRODUCT_SHOW_LIST_TYPE
            }

            // 검색 화면 이동
            ProductInfoType.HOME_SEARCH_VIEW.number -> {
                productInfoType = ProductInfoType.HOME_SEARCH_VIEW
            }
        }
    }


    // UserInfoType 필드로 분기하여 화면을 전환한다
    private fun replaceFragmentByArguments() {
        when (productInfoType) {
            // 상품 ( 탭레이아웃을 갖고 있는 프레그먼트)
            ProductInfoType.USER_PRODUCT_INFO_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_FRAGMENT, true, true, null)
            // 상품 설명
            ProductInfoType.USER_PRODUCT_INFO_DESCRIPTION_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_DESCRIPTION_FRAGMENT, true, true, null)
            // 상품 상세 설명
            ProductInfoType.USER_PRODUCT_INFO_DETAIL_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_DETAIL_FRAGMENT, true, true, null)
            // 상품 리뷰
            ProductInfoType.USER_PRODUCT_INFO_REVIEW_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_REVIEW_FRAGMENT, true, true, null)
            // 상품 리뷰 쓰기
            ProductInfoType.USER_PRODUCT_INFO_WRITE_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_WRITE_REVIEW_FRAGMENT, true, true, null)
            // 상품 구매 바텀시트
            ProductInfoType.USER_PRODUCT_INFO_DIALOG_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_INFO_DIALOG_FRAGMENT, true, true, null)
            // 상품 리스트
            ProductInfoType.USER_PRODUCT_SHOW_LIST_TYPE -> replaceFragment(ProductSubFragment.USER_PRODUCT_SHOW_LIST_FRAGMENT, true, true, null)
            // 홈-> 검색
            ProductInfoType.HOME_SEARCH_VIEW -> replaceFragment(ProductSubFragment.USER_HOME_SEARCH_VIEW_FRAGMENT, true, true, null)
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
    
}