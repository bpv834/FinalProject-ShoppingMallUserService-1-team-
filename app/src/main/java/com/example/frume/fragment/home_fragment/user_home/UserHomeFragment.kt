package com.example.frume.fragment.home_fragment.user_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.data.Storage
import com.example.frume.databinding.FragmentUserHomeBinding
import com.example.frume.fragment.BottomNavFragment
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.util.ProductInfoType
import com.google.android.material.tabs.TabLayoutMediator

/*import com.example.frume.fragment.FullSubFragmentName*/

class UserHomeFragment(
    val bottomNavFragment: BottomNavFragment
) : Fragment() {
    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserHomeBinding: FragmentUserHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_home, container, false)
        homeActivity = activity as HomeActivity


        return fragmentUserHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSearchView()
        setLayout()
    }


    fun showSearchFragment() {
        homeActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.containerCombination, UserSearchFragment())
            .addToBackStack("UserHomeFragment")
            .commit()
    }

    fun test() {
        val dataBundle = Bundle()
        dataBundle.putInt("UserInfoType", ProductInfoType.HOME_SEARCH_VIEW.number)
        bottomNavFragment.combinationFragment.replaceFragment(
            SubMainFragmentName.NAV_MAIN_FRAGMENT,
            true,
            true,
            dataBundle
        )
    }


    private fun showSearchView() {
        fragmentUserHomeBinding.toolbarUserHome.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuSearch -> {
                      showSearchFragment()
                   // test()
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    // 화면 구성
    private fun setLayout() {
        val category = Storage.categoryList // 카테고리 리스트
        // 탭레이아웃 어댑터 연결
        fragmentUserHomeBinding.viewpagerUserHomeMain.adapter = HomeTabAdapter(this, category)
        TabLayoutMediator(fragmentUserHomeBinding.tabUserHome, fragmentUserHomeBinding.viewpagerUserHomeMain) { tab, pos ->
            tab.text = category[pos] // 탭 레이아웃 텍스트 넣어주기
        }.attach()
    }


}