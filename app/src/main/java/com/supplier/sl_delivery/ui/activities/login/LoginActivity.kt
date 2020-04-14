package com.supplier.sl_delivery.ui.activities.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.ui.activities.sign_up.SignUpActivity
import com.supplier.sl_delivery.ui.activities.base.BaseActivity
import com.supplier.sl_delivery.ui.activities.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Aurora on 2020-03-28.
 */
class LoginActivity : BaseActivity(), View.OnClickListener, IContractLogin.ILoginView {

    private lateinit var mLoginPresenter: IContractLogin.ILoginPresenter<IContractLogin.ILoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginPresenter = ImplLoginPresenter(this)
        mLoginPresenter.setView(this)

        mLblSignUp.setOnClickListener(this)
        mBtnLogin.setOnClickListener(this)

        mTxtUserName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mLoginPresenter.setUserName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mLoginPresenter.setPassword(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        mLoginPresenter.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoginPresenter.onDestroy()
    }

    override fun onClick(v: View?) {
        if (v == mBtnLogin) {
            mLoginPresenter.triggerLogin()
        } else if (v == mLblSignUp) {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    override fun showUserNameError(error: Int) {
        mTxtUserName.error = getString(error)
        mTxtUserName.requestFocus()
    }

    override fun showPasswordError(error: Int) {
        mTxtPassword.error = getString(error)
        mTxtPassword.requestFocus()
    }

    override fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    override fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            mLoginProgressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            mLoginProgressBar.visibility = View.GONE
        }
    }
}
