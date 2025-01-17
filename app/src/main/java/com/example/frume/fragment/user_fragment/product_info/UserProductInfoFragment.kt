package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductInfoBinding
import com.example.frume.fragment_main.ProductMainFragment
import com.example.frume.fragment_main.ProductSubFragment
import com.google.android.material.tabs.TabLayoutMediator


class UserProductInfoFragment(val productMainFragment: ProductMainFragment) : Fragment() {
    lateinit var fragmentUserProductInfoBinding: FragmentUserProductInfoBinding
    lateinit var homeActivity: HomeActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentUserProductInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_info, container, false)
        homeActivity = activity as HomeActivity
        // test
        Log.d("test100", "UserProductInfoFragment")
        // ViewPager2 및 TabLayout 초기화
        setupViewPagerAndTabs()
        // 뒤로가기 버튼 클릭 이벤트
        onClickNavigationIconBackStack()

        return fragmentUserProductInfoBinding.root
    }

    fun onClickNavigationIconBackStack() {
        fragmentUserProductInfoBinding.toolBarUserProductInfo.apply {
            setNavigationOnClickListener {
                productMainFragment.removeFragment(ProductSubFragment.USER_PRODUCT_INFO_FRAGMENT)
            }
        }
    }


    // ViewPager2와 TabLayout 초기화 메서드
    private fun setupViewPagerAndTabs() {
        // ViewPager2 어댑터 설정
        val adapter = ProductInfoPagerAdapter(this)


        fragmentUserProductInfoBinding.viewPagerUserProductInfo.adapter = adapter
       // 사용자가 스와이프하여 페이지를 전환할 수 없도록 설정
        fragmentUserProductInfoBinding.viewPagerUserProductInfo.isUserInputEnabled = false
        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(
            fragmentUserProductInfoBinding.tabLayoutUserProductInfo,
            fragmentUserProductInfoBinding.viewPagerUserProductInfo
        ) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }


    // TabLayout의 각 탭 제목 반환
    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "상품설명"
            1 -> "상세정보"
            2 -> "후기"
            3 -> "문의"
            else -> ""
        }
    }

    // ViewPagerAdapter
    inner class ProductInfoPagerAdapter(
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {

        private val fragments = listOf(
            UserProductInfoDescriptionFragment(productMainFragment),
            UserProductInfoDetailFragment(productMainFragment),
            UserProductInfoReviewFragment(productMainFragment),
            UserProductInfoDescriptionFragment(productMainFragment),
        )

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

}

