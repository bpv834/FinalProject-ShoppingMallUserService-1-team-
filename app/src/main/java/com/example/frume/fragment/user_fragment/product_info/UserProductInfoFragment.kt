package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductInfoBinding


class UserProductInfoFragment : Fragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserProductInfoBinding: FragmentUserProductInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user_product_info, container, false)

        Log.d("test100", "UserProductInfoFragment 진입 ")

        fragmentUserProductInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_info, container, false)

        homeActivity = activity as HomeActivity

        setupToolbar()
        displayProductDetails()

        return fragmentUserProductInfoBinding.root
    }

    private fun setupToolbar() {
        val productName = arguments?.getString("productName") ?: "상품 정보 없음"
        fragmentUserProductInfoBinding.toolBarUserProductInfo.title = productName

        // 툴바 뒤로가기 버튼 설정
        fragmentUserProductInfoBinding.toolBarUserProductInfo.setNavigationOnClickListener {
            homeActivity.supportFragmentManager.popBackStack()
        }
    }

    private fun displayProductDetails() {
        val productName = arguments?.getString("productName") ?: "상품명 없음"

        // 필요 시 다른 데이터를 처리하고 표시할 수 있습니다.
        fragmentUserProductInfoBinding.toolBarUserProductInfo.title = productName
    }
}
