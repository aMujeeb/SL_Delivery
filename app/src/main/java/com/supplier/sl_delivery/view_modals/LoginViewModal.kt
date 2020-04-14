package com.supplier.sl_delivery.view_modals

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.supplier.sl_delivery.modals.Merchant
import com.supplier.sl_delivery.rest.responses.LoginResponse
import com.supplier.sl_delivery.ui.actions.ActionLiveData
import com.supplier.sl_delivery.utils.LoggerUtil
import com.supplier.sl_delivery.utils.SLDeliveryConstants

/**
 * Created by Aurora on 2020-03-30.
 */
class LoginViewModal : ViewModel() {

    private var mMutableLoginResponse: MutableLiveData<LoginResponse>
    var mProgress = MutableLiveData<Int>()

    init {
        mProgress.value = 8
        mMutableLoginResponse = MutableLiveData()
    }

    var mMerchant = Merchant(null, null)

    val emailTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                LoggerUtil.logMessage("User Name : " + s.toString())
                mMerchant.contactNumber = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

    val passwordTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                LoggerUtil.logMessage("User Password : " + s.toString())
                mMerchant.password = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

    /*fun onLoginClicked() {
        LoggerUtil.logMessage("Login Clicked")
        if (mMerchant.isPasswordCorrect && mMerchant.isPasswordCorrect) {
            mProgress.value = 0
            getLoginResponse()
            mMutableLoginResponse = mMerchant.loginMerchant()
        } else if (!mMerchant.isUserNameValid) {
            mLoginButtonAction.sendAction(SLDeliveryConstants.USERNAME_ERROR)
        } else if (!mMerchant.isPasswordCorrect) {
            mLoginButtonAction.sendAction(SLDeliveryConstants.PASSWORD_ERROR)
        }
    }*/

    fun getLoginResponse() : LiveData<LoginResponse>? {
        /*if (mMutableLoginResponse != null && mMutableLoginResponse.value != null) {
            mLoginButtonAction.value = SLDeliveryConstants.SUCCESS
        } else {
            //mLoginButtonAction.value = SLDeliveryConstants.UN_SUCCESS
            mProgress.value = 8
        }*/
        if(mMutableLoginResponse == null)
            return null
        return mMutableLoginResponse
    }
}
