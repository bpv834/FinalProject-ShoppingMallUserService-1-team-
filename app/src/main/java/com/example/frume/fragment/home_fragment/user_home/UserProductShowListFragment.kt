package com.example.frume.fragment.home_fragment.user_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductShowListBinding
import com.example.frume.fragment.CombinationFragment


class UserProductShowListFragment() : Fragment() {

    lateinit var homeActivity: HomeActivity
    private var _binding: FragmentUserProductShowListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeActivity = activity as HomeActivity
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_show_list, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test()

    }


    private fun test() {
        val productName = arguments?.getString("search")
        binding.toolbarUserProductShowList.title = productName
    }


}