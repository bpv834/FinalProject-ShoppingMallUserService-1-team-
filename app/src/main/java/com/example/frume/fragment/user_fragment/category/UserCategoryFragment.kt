package com.example.frume.fragment.user_fragment.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frume.R

import com.example.frume.databinding.FragmentUserCategoryBinding


class UserCategoryFragment : Fragment() {
    private var _binding: FragmentUserCategoryBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickItem()
    }

    private fun onClickItem() {
        binding.textViewUserCategoryStrawberry.setOnClickListener {

        }
    }

}