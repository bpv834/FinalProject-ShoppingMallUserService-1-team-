package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.dataHJ.DummyData
import com.example.frume.databinding.FragmentUserProductInfoDescriptionBinding
import com.example.frume.databinding.ItemProductInfoImageCarouselBinding
import com.example.frume.fragment_main.ProductMainFragment


class UserProductInfoDescriptionFragment(val productMainFragment: ProductMainFragment) :
    Fragment() {

    lateinit var userProductInfoDescriptionBinding: FragmentUserProductInfoDescriptionBinding
    lateinit var homeActivity: HomeActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        userProductInfoDescriptionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_product_info_description,
            container,
            false
        )
        homeActivity = activity as HomeActivity
        // Inflate the layout for this fragment

        // carousel 실행
        setupCarousel()
        return userProductInfoDescriptionBinding.root
    }

    // Carousel 설정 메서드
    private fun setupCarousel() {
        Log.d("test100","setupCarousel()")
        val dummyImages = DummyData.dummyImages
        Log.d("test100","dummyImages: $dummyImages " )

        val adapter = CarouselAdapter()
        userProductInfoDescriptionBinding.recyclerViewUserProductInfoDescriptionDescriptionImage.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }


    }
    inner class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>(){
        inner class CarouselViewHolder(val itemProductInfoImageCarouselBinding: ItemProductInfoImageCarouselBinding) : RecyclerView.ViewHolder(itemProductInfoImageCarouselBinding.root),
            OnClickListener {
            override fun onClick(v: View?) {
                userProductInfoDescriptionBinding.imageViewCarouselUserProductInfoDescription.setImageResource(DummyData.dummyImages[adapterPosition])
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
            val itemProductInfoImageCarouselBinding = ItemProductInfoImageCarouselBinding.inflate(layoutInflater, parent, false)
            val carouselViewHolder = CarouselViewHolder(itemProductInfoImageCarouselBinding)
            itemProductInfoImageCarouselBinding.root.setOnClickListener(carouselViewHolder)
            return carouselViewHolder
        }

        override fun getItemCount(): Int {
            return DummyData.dummyImages.size
        }

        override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
            holder.itemProductInfoImageCarouselBinding.imageViewUserProductInfoDescriptionCarousel.setImageResource(DummyData.dummyImages[position])
        }
    }

}