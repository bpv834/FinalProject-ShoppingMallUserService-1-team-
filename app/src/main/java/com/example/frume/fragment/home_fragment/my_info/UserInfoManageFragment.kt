package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoManageBinding
import com.example.frume.fragment.screen_manager.HomeMainFragment

class UserInfoManageFragment : Fragment() {
    private var _binding: FragmentUserInfoManageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoManageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickModify()
    }
    private fun onClickModify() {
        binding.buttonUserInfoManageModifyUserInfo.setOnClickListener {
            setLayout()
        }
    }

    private fun setLayout() {
        val dataBundle = Bundle().apply {
            putString("Fragment", "InfoModify")
            putBoolean("Full", true)
        }

        Log.d("UserInfoFragment", "Sending dataBundle: $dataBundle")
        (parentFragment?.parentFragment as? HomeMainFragment)?.switchToFragment(dataBundle)
    }
}