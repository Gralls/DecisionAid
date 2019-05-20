package com.springer.patryk.decisionaid.view.base.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.springer.patryk.decisionaid.view.base.BaseFragment

/**
 * Created by Patryk Springer on 28.04.2019.
 */
class ViewPagerAdapter(
	fragmentManager: FragmentManager?,
	private val mPages: List<BaseFragment>,
	private val mContext: Context?
) : FragmentPagerAdapter(fragmentManager) {


	override fun getItem(position: Int): Fragment {
		return mPages[position]
	}

	override fun getCount(): Int = mPages.size
}
