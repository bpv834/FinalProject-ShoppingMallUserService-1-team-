package com.example.frume.fragment.home_fragment.user_home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserHomeBinding
import com.example.frume.fragment.BottomNavFragment
import com.example.frume.fragment.BottomNavSubFragmentName
/*import com.example.frume.fragment.FullSubFragmentName*/

class UserHomeFragment(val bottomNavFragment: BottomNavFragment) : Fragment() {
    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserHomeBinding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserHomeBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_user_home,container,false)
        homeActivity = activity as HomeActivity

        showSearchView()
        return fragmentUserHomeBinding.root
    }




    fun showSearchFragment() {
        bottomNavFragment.replaceFragment(BottomNavSubFragmentName.USER_PRODUCT_SHOW_LIST_FRAGMENT,true,true,null)
    }

    private fun showSearchView() {
        fragmentUserHomeBinding.toolbarUserHome.setOnMenuItemClickListener { item->
            when(item.itemId){
                R.id.menuSearch->{
                    Log.d("test400","tttt")
                    showSearchFragment()
                    true
                }

                else -> {
                    true
                }
            }
        }
    }



}