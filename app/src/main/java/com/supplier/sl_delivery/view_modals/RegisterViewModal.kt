package com.supplier.sl_delivery.view_modals

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.supplier.sl_delivery.modals.Merchant
import com.supplier.sl_delivery.rest.responses.MerchantRegisterResponse
import com.supplier.sl_delivery.ui.actions.ActionLiveData
import com.supplier.sl_delivery.utils.LoggerUtil
import com.supplier.sl_delivery.utils.SLDeliveryConstants

/**
 * Created by Aurora on 2020-04-11.
 */
class RegisterViewModal : ViewModel() {

    private var mMerchantRegisterResponse: MutableLiveData<MerchantRegisterResponse>? = null
    val mRegisterButtonAction = ActionLiveData<Int>()
    var mProgress = MutableLiveData<Int>()
    var mMerchant = Merchant(null, null)

    fun init() {
        mProgress.value = 8
        mMerchantRegisterResponse = MutableLiveData()
        if (mMerchantRegisterResponse != null) {
            return
        }
    }

    val businessNameTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.businessName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    val ownerNameTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.ownerName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    val addressTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.address = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    val contactNumberTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.contactNumber = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    val nicOrBusinessRegisterTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.regNumber = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    val passwordTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.password = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    val passwordConfirmTextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mMerchant.passwordConform = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        }

    fun onRegisterClicked(v: View) {
        LoggerUtil.logMessage("Register Clicked")
        when {
            mMerchant.businessName.isNullOrEmpty() -> {
                mRegisterButtonAction.value = SLDeliveryConstants.BUSINESS_NAME_ERROR
            }
            mMerchant.ownerName.isNullOrEmpty() -> {
                mRegisterButtonAction.value = SLDeliveryConstants.OWNER_NAME_ERROR
            }
            mMerchant.address.isNullOrEmpty() -> {
                mRegisterButtonAction.value = SLDeliveryConstants.ADDRESS_ERROR
            }
            !mMerchant.isUserNameValid -> {
                mRegisterButtonAction.value = SLDeliveryConstants.MOBILE_NUMBER_ERROR
            }
            mMerchant.regNumber.isNullOrEmpty() -> {
                mRegisterButtonAction.value = SLDeliveryConstants.BUSINESS_REG_ERROR
            }
            !mMerchant.isPasswordCorrect -> {
                mRegisterButtonAction.value = SLDeliveryConstants.PASSWORD_ERROR
            }
            /*!mMerchant.isPasswordCorrect -> {
                mRegisterButtonAction.value = SLDeliveryConstants.PASSWORD_CONFIRM_ERROR
            }*/
            !mMerchant.isConfirmPasswordMatching -> {
                mRegisterButtonAction.value = SLDeliveryConstants.PASSWORD_NOT_MATCHING_ERROR
            }
            else -> {
                mProgress.value = 0
               /* //mMerchantRegisterResponse = mMerchant.registerMerchant()
                if(mMerchantRegisterResponse != null && mMerchantRegisterResponse!!.value != null){
                    if(mMerchantRegisterResponse!!.value!!.status == SLDeliveryConstants.ERROR) {
                        LoggerUtil.logMessage("Register Error")
                        mRegisterButtonAction.value = SLDeliveryConstants.UN_SUCCESS
                    } else {
                        mRegisterButtonAction.value = SLDeliveryConstants.SUCCESS
                    }
                } else {
                    mRegisterButtonAction.value = SLDeliveryConstants.UN_SUCCESS
                    mProgress.value = 8
                }*/
            }
        }
    }
}
