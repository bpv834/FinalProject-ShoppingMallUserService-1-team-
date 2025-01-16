package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.data_hj.DummyData
import com.example.frume.databinding.FragmentUserProductInfoDescriptionBinding
import com.example.frume.databinding.ItemProductInfoImageBinding
import com.example.frume.databinding.ItemProductInfoImageCarouselBinding
import com.example.frume.fragment_main.ProductMainFragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.divider.MaterialDividerItemDecoration


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

        // carousel 실행
        settingRecyclerViewCarousel()
        // 구매버튼 리스너
        onClickButtonPurches()
        // recyclerViewImage 실행
        settingRecyclerViewImage()

        return userProductInfoDescriptionBinding.root
    }

    fun onClickButtonPurches() {
        userProductInfoDescriptionBinding.buttonUserProductInfoDescriptionBuy.setOnClickListener {
            val bottomSheetFragment = UserProductInfoDialogFragment(productMainFragment)
            bottomSheetFragment.show(homeActivity.supportFragmentManager, "tag")
        }
    }


    // RecyclerView를 구성하는 메서드
    fun settingRecyclerViewImage() {
        userProductInfoDescriptionBinding.apply {
            // 어뎁터
            recyclerViewUserProductInfoDescriptionDescriptionImage.adapter =
                RecyclerViewImageAdapter()
            // LayoutManager
            recyclerViewUserProductInfoDescriptionDescriptionImage.layoutManager =
                LinearLayoutManager(homeActivity)
            // 구분선
            val deco =
                MaterialDividerItemDecoration(homeActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerViewUserProductInfoDescriptionDescriptionImage.addItemDecoration(deco)
        }
    }

    // RecyclerView의 어뎁터
    inner class RecyclerViewImageAdapter :
        RecyclerView.Adapter<RecyclerViewImageAdapter.ViewHolderMain>() {
        // ViewHolder
        inner class ViewHolderMain(val itemProductInfoImageBinding: ItemProductInfoImageBinding) :
            RecyclerView.ViewHolder(itemProductInfoImageBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {

            val itemProductInfoImageBinding = ItemProductInfoImageBinding.inflate(layoutInflater, parent, false)

            val viewHolderImage = ViewHolderMain(itemProductInfoImageBinding)


            return viewHolderImage
        }

        override fun getItemCount(): Int {
            return DummyData.dummyImages.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.itemProductInfoImageBinding.imageViewItemProductInfoImage.setImageResource(DummyData.dummyImages[position])
        }
    }


    // 카로셀 어뎁터 적용
    private fun settingRecyclerViewCarousel() {
        userProductInfoDescriptionBinding.apply {
            recyclerViewUserProductInfoDescriptionCarouselImage.adapter = RecyclerViewCarouselAdapter()
            // 회전 목마용 LayoutManager
            recyclerViewUserProductInfoDescriptionCarouselImage.layoutManager =
                CarouselLayoutManager()
            // recyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            // recyclerView.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            // recyclerView.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy(), RecyclerView.VERTICAL)

            val snapHelper = CarouselSnapHelper()
            snapHelper.attachToRecyclerView(recyclerViewUserProductInfoDescriptionCarouselImage)
        }
    }

    // 카로셀 어뎁터 클래스
    inner class RecyclerViewCarouselAdapter : RecyclerView.Adapter<RecyclerViewCarouselAdapter.CarouselViewHolder>() {
        inner class CarouselViewHolder(val itemProductInfoImageCarouselBinding: ItemProductInfoImageCarouselBinding) :
            RecyclerView.ViewHolder(itemProductInfoImageCarouselBinding.root),
            OnClickListener {
            override fun onClick(v: View?) {
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
            val itemProductInfoImageCarouselBinding =
                ItemProductInfoImageCarouselBinding.inflate(layoutInflater, parent, false)
            val carouselViewHolder = CarouselViewHolder(itemProductInfoImageCarouselBinding)
            itemProductInfoImageCarouselBinding.root.setOnClickListener(carouselViewHolder)
            return carouselViewHolder
        }

        override fun getItemCount(): Int {
            return DummyData.dummyImages.size
            Log.d("test100", "imageSize : ${DummyData.dummyImages.size}")
        }

        override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
            holder.itemProductInfoImageCarouselBinding.imageViewItemProductInfoImageCarousel.setImageResource(
                DummyData.dummyImages[position]
            )
        }
    }

}