package com.springer.patryk.decisionaid.view.base.viewpager

import android.os.Bundle
import android.view.View
import com.springer.patryk.decisionaid.view.base.BaseFragment
import kotlinx.android.synthetic.main.base_view_pager.*

/**
 * Created by Patryk Springer on 28.04.2019.
 */
abstract class BaseViewPagerFragment : BaseFragment() {

	abstract val mListOfFragments: MutableList<BaseFragment>

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (vp_base_pager == null) {
			throw IllegalArgumentException("Layout must include base_view_pager")
		}
		vp_base_pager.adapter = ViewPagerAdapter(childFragmentManager, mListOfFragments, context)
	}

	fun notifyAdapter() {
		vp_base_pager.adapter?.notifyDataSetChanged()
	}
}