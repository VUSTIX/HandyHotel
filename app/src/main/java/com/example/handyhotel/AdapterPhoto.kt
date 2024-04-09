package com.example.handyhotel

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class AdapterPhoto(private val photos: List<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return photos.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val photoView = ImageView(container.context)
        photoView.setImageResource(photos[position])
        container.addView(photoView)
        return photoView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}