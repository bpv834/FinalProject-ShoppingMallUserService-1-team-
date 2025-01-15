package com.example.frume.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentBottomNavBinding
import com.example.frume.fragment.home_fragment.my_info.UserInfoFragment
import com.example.frume.fragment.home_fragment.user_home.UserHomeFragment
import com.example.frume.fragment.user_fragment.UserLoginFragment
import com.google.android.material.transition.MaterialSharedAxis


class BottomNavFragment(val combinationFragment: CombinationFragment) : Fragment() {

    lateinit var bottomNavBinding: FragmentBottomNavBinding
    lateinit var mainActivity: MainActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomNavBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_nav,container,false)
        mainActivity = activity as MainActivity
        // Inflate the layout for this fragment
        openHome()
        onClickBottomNavigation()
        return bottomNavBinding.root
    }



    fun openHome() {
        replaceFragment(BottomNavSubFragmentName.USER_HOME_FRAGMENT,false,true,null)
    }

    fun onClickBottomNavigation() {
        bottomNavBinding.bottomNavMain.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.menu_profile->{
                    replaceFragment(BottomNavSubFragmentName.USER_INFO_FRAGMENT,false,false,null)
                }
            }
            true
        }

    }


    // 프래그먼트를 교체하는 함수
    fun replaceFragment(fragmentName: BottomNavSubFragmentName, isAddToBackStack:Boolean, animate:Boolean, dataBundle: Bundle?){
        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }
        // 프래그먼트 객체
        newFragment = when(fragmentName){
            // 로그인
            BottomNavSubFragmentName.USER_HOME_FRAGMENT -> UserHomeFragment()
            BottomNavSubFragmentName.USER_INFO_FRAGMENT -> UserInfoFragment()
        }

        // bundle 객체가 null이 아니라면
        if(dataBundle != null){
            newFragment?.arguments = dataBundle
        }

        // 프래그먼트 교체
        mainActivity.supportFragmentManager.commit {

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
        mainActivity.supportFragmentManager.popBackStack(fragmentName.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}

// 하위 프래그먼트들의 이름
enum class BottomNavSubFragmentName(var number:Int, var str:String){
    // 홈
    USER_HOME_FRAGMENT(1, "UserHomeFragment"),
    // 내정보
    USER_INFO_FRAGMENT(2,"UserInfoFragment")
}

