package com.example.frume.fragment.user_fragment.category

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
import com.example.frume.databinding.FragmentUserCategoryBinding
import com.example.frume.fragment.CombinationFragment
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.fragment_main.BottomNavMainFragment
import com.example.frume.util.UserInfoType
import com.example.frume.util.ProductCategoryDetailType

class UserCategoryFragment(val bottomNavMainFragment: BottomNavMainFragment) : Fragment() {

    lateinit var userCategoryBinding: FragmentUserCategoryBinding
    lateinit var homeActivity: HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeActivity = activity as HomeActivity
        userCategoryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_category, container, false)

        Log.d("test100","UserCategoryFragment!!!")

        // 클릭 리스너. 과일 이름을 클릭하면 넘어가도록한다.
        onClickCategory()

        return userCategoryBinding.root
    }

    // 과일 선택
    fun onClickCategory(){
        userCategoryBinding.textViewUserCategoryStrawberry.setOnClickListener {navigateToCategoryDetail(0)}
        
        userCategoryBinding.textViewUserCategoryApple.setOnClickListener {navigateToCategoryDetail(1)}
        
        userCategoryBinding.textViewUserCategoryTangerine.setOnClickListener {navigateToCategoryDetail(2)}
        
        userCategoryBinding.textViewUserCategoryGrape.setOnClickListener {navigateToCategoryDetail(3)}
        
        userCategoryBinding.textViewUserCategoryMango.setOnClickListener {navigateToCategoryDetail(4)}
        
        userCategoryBinding.textViewUserCategoryBlueberry.setOnClickListener {navigateToCategoryDetail(5)}
        
        userCategoryBinding.textViewUserCategoryKiwi.setOnClickListener {navigateToCategoryDetail(6)}
        
        userCategoryBinding.textViewUserCategoryOrange.setOnClickListener {navigateToCategoryDetail(7)}
        
        userCategoryBinding.textViewUserCategorySingle.setOnClickListener {navigateToCategoryDetail(8)}
        
        userCategoryBinding.textViewUserCategoryBulk.setOnClickListener {navigateToCategoryDetail(9)}
        
        userCategoryBinding.textViewUserCategoryPackage.setOnClickListener {navigateToCategoryDetail(10)}
        
        userCategoryBinding.textViewUserCategorySale.setOnClickListener { navigateToCategoryDetail(11)}



    }

    fun navigateToCategoryDetail(categoryNumber: Int){

        Log.d("test100", "Navigating to detail with category number: $categoryNumber")

        val dataBundle = Bundle().apply {
            putInt("ProductCategoryDetailType", categoryNumber)  // 숫자만 전달
        }


        bottomNavMainFragment.combinationFragment.replaceFragment(
            SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,
            true,
            true,
            dataBundle
        )


    }
}