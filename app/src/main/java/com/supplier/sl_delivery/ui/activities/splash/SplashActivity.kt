package com.supplier.sl_delivery.ui.activities.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.ui.activities.base.BaseActivity
import com.supplier.sl_delivery.ui.activities.login.LoginActivity

/**
 * Created by Aurora on 2020-03-27.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this,
                LoginActivity:: class.java))
            finishAffinity()
        }, 3000)
    }
}
