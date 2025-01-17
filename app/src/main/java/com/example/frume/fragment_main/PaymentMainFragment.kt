package com.example.frume.fragment_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentPaymentMainBinding
import com.example.frume.fragment.CombinationFragment


class PaymentMainFragment(val combinationFragment: CombinationFragment) : Fragment() {

    lateinit var paymentMainBinding: FragmentPaymentMainBinding
    lateinit var mainActivity: MainActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        paymentMainBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_main,container,false)
        mainActivity = activity as MainActivity
        return paymentMainBinding.root
    }

}

enum class PaymentSubFragment(var number:Int, var str:String){

    // 결제
    USER_PAYMENT_SCREEN_FRAGMENT(3,"UserPaymentScreenFragment"),

}