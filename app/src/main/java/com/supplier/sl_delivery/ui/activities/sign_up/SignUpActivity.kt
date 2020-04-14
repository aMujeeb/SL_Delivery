package com.supplier.sl_delivery.ui.activities.sign_up

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.supplier.sl_delivery.enums.AlertType
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.ui.activities.base.BaseActivity
import com.supplier.sl_delivery.ui.activities.main.MainActivity
import com.supplier.sl_delivery.utils.SLDeliveryConstants
import com.supplier.sl_delivery.view_modals.RegisterViewModal
import kotlinx.android.synthetic.main.activity_user_register.*

/**
 * Created by Aurora on 2020-03-29.
 */
class SignUpActivity : BaseActivity(), IContractSignUp.ISignUpView, View.OnClickListener {

    private lateinit var        mSignUpPresenter : IContractSignUp.ISignUpPresenter<IContractSignUp.ISignUpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        mSignUpPresenter = ImplSignUpPresenter()
        mSignUpPresenter.setView(this)

        mBtnSignUp.setOnClickListener(this)

        mTxtBusinessName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setBusinessName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtOwnerName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setOwnerName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtAddress.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setAddress(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtContactNumber.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setContactNumber(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtNICBusiness.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setRegistrationNumber(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setPassword(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        mTxtConfirmPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mSignUpPresenter.setConfirmPassword(s.toString())
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

    override fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    override fun setBusinessNameError(error: Int) {
        mTxtBusinessName.error = getString(error)
        mTxtBusinessName.requestFocus()
    }

    override fun setOwnerNameError(error: Int) {
        mTxtOwnerName.error = getString(error)
        mTxtOwnerName.requestFocus()
    }

    override fun setAddressError(error: Int) {
        mTxtAddress.error = getString(error)
        mTxtAddress.requestFocus()
    }

    override fun setContactNumberError(error: Int) {
        mTxtContactNumber.error = getString(error)
        mTxtContactNumber.requestFocus()
    }

    override fun setRegistrationNumberError(error: Int) {
        mTxtNICBusiness.error = getString(error)
        mTxtNICBusiness.requestFocus()
    }

    override fun setPasswordError(error: Int) {
        mTxtPassword.error = getString(error)
        mTxtPassword.requestFocus()
    }

    override fun setConfirmPasswordError(error: Int) {
        mTxtConfirmPassword.error = getString(error)
        mTxtConfirmPassword.requestFocus()
    }

    override fun onClick(v: View?) {
        if(v == mBtnSignUp){
            mSignUpPresenter.triggerUserRegister()
        }
    }

    override fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            mRegisterProgress.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            mRegisterProgress.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSignUpPresenter.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        mSignUpPresenter.onDestroy()
    }
}
