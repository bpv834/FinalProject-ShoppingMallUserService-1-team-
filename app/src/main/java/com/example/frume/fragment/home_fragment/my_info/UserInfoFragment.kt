package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.frume.databinding.FragmentUserInfoBinding
import com.example.frume.fragment.screen_manager.HomeMainFragment

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
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
        binding.textViewUserInfoAccountInfo.setOnClickListener {
            setLayout()
        }
    }

    private fun setLayout() {
        val dataBundle = Bundle()
        dataBundle.putString("Fragment", "Info")
        Log.d("UserInfoFragment", "Sending dataBundle: $dataBundle")
        (parentFragment?.parentFragment as? HomeMainFragment)?.switchToFragment(dataBundle)
    }

}