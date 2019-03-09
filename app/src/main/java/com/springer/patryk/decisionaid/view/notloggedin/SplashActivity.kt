package com.springer.patryk.decisionaid.view.notloggedin

import android.content.Intent
import android.os.Bundle
import com.springer.patryk.decisionaid.R
import com.springer.patryk.decisionaid.view.base.BaseActivity

class SplashActivity : BaseActivity() {
    override val mLayoutResId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, NotLoggedInActivity::class.java))
    }
}