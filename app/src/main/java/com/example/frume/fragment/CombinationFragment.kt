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
import com.example.frume.databinding.FragmentCombinationBinding
import com.google.android.material.transition.MaterialSharedAxis


class CombinationFragment : Fragment() {

    lateinit var combinationBinding: FragmentCombinationBinding
    lateinit var mainActivity : MainActivity

    // 현재 Fragment와 다음 Fragment를 담을 변수(애니메이션 이동 때문에...)
    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        combinationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_combination, container, false)
        mainActivity= activity as MainActivity
        // Inflate the layout for this fragment

        replaceFragment(SubMainFragmentName.FULL_MAIN_FRAGMENT,false, false, null)
        return inflater.inflate(R.layout.fragment_combination, container, false)
    }



    // 프래그먼트를 교체하는 함수
    fun replaceFragment(fragmentName: SubMainFragmentName, isAddToBackStack:Boolean, animate:Boolean, dataBundle: Bundle?){
        // newFragment가 null이 아니라면 oldFragment 변수에 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }
        // 프래그먼트 객체
        newFragment = when(fragmentName){
            // 게시글 목록 화면
            SubMainFragmentName.FULL_MAIN_FRAGMENT -> {
                FullScreenFragment(this@CombinationFragment)
            }
            // 게시글 작성 화면
            SubMainFragmentName.NAV_MAIN_FRAGMENT -> {
                BottomNavFragment(this@CombinationFragment)
            }

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

            replace(R.id.containerCombination, newFragment!!)
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
enum class SubMainFragmentName(var number:Int, var str:String){
    // 풀 스크린
    FULL_MAIN_FRAGMENT(1, "FullScreenFragment"),
    // 네비 스크린
    NAV_MAIN_FRAGMENT(2, "BottomNavFragment"),

}