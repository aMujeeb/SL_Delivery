package com.supplier.sl_delivery.ui.activities.sign_up

import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.enums.AlertType
import com.supplier.sl_delivery.enums.Status
import com.supplier.sl_delivery.modals.Merchant
import com.supplier.sl_delivery.rest.responses.MerchantRegisterResponse
import com.supplier.sl_delivery.ui.activities.base.BasePresenter
import com.supplier.sl_delivery.utils.LoggerUtil
import com.supplier.sl_delivery.utils.SLDeliveryConstants
import io.reactivex.observers.DisposableObserver

/**
 * Created by Aurora on 2020-04-12.
 */
class ImplSignUpPresenter<V : IContractSignUp.ISignUpView> : BasePresenter<V>(),
    IContractSignUp.ISignUpPresenter<V> {

    private lateinit var mSignUpView: IContractSignUp.ISignUpView
    private var mMerchant = Merchant("", "")
    private var mDisposableObserver: DisposableObserver<MerchantRegisterResponse>? = null

    override fun setView(view: IContractSignUp.ISignUpView) {
        mSignUpView = view
    }

    override fun setBusinessName(bName: String) {
        mMerchant.businessName = bName
    }

    override fun setOwnerName(oName: String) {
        mMerchant.ownerName = oName
    }

    override fun setAddress(aName: String) {
        mMerchant.address = aName
    }

    override fun setContactNumber(contact: String) {
        mMerchant.contactNumber = contact
    }

    override fun setRegistrationNumber(regNo: String) {
        mMerchant.regNumber = regNo
    }

    override fun setPassword(password: String) {
        mMerchant.password = password
    }

    override fun setConfirmPassword(cPassword: String) {
        mMerchant.passwordConform = cPassword
    }

    override fun triggerUserRegister() {
        when {
            mMerchant.businessName.isNullOrEmpty() -> {
                mSignUpView.setBusinessNameError(R.string.business_name_error)
            }
            mMerchant.ownerName.isNullOrEmpty() -> {
                mSignUpView.setOwnerNameError(R.string.owner_name_error)
            }
            mMerchant.address.isNullOrEmpty() -> {
                mSignUpView.setAddressError(R.string.address_error)
            }
            !mMerchant.isUserNameValid -> {
                mSignUpView.setContactNumberError(R.string.contact_number_error)
            }
            !mMerchant.isRegisterNumberValid -> {
                mSignUpView.setRegistrationNumberError(R.string.nic_biz_reg_error)
            }
            !mMerchant.isPasswordCorrect -> {
                mSignUpView.setPasswordError(R.string.password_error)
            }
            !mMerchant.isConfirmPasswordMatching -> {
                mSignUpView.setConfirmPasswordError(R.string.password_confirm_error)
            }
            else -> {
                mSignUpView.showProgress()
                if (mDisposableObserver != null) {
                    mDisposableObserver!!.dispose()
                }
                createUserRegisterObserver()
                mMerchant.registerMerchant().subscribeWith(mDisposableObserver)
            }
        }
    }

    private fun createUserRegisterObserver(): DisposableObserver<MerchantRegisterResponse>? {
        mDisposableObserver = object : DisposableObserver<MerchantRegisterResponse>() {
            override fun onComplete() {
                LoggerUtil.logMessage("Completed Observer")
            }

            override fun onNext(t: MerchantRegisterResponse) {
                mSignUpView.hideProgress()
                if (t?.data != null) {
                    mSignUpView.navigateToMain()
                } else {
                    if(t?.status == Status.ERROR){
                        mSignUpView.displayGeneralErrorMessage(
                            t?.message!!,
                            AlertType.ALERT_TYPE_ERROR)
                    } else {
                        mSignUpView.displayGeneralErrorMessage(
                        R.string.user_register_error,
                        AlertType.ALERT_TYPE_ERROR)
                    }
                }
            }

            override fun onError(e: Throwable) {
                LoggerUtil.logMessage("Error Observer")
                mSignUpView.hideProgress()
                mSignUpView.displayGeneralErrorMessage(
                    R.string.user_register_error,
                    AlertType.ALERT_TYPE_ERROR
                )
            }
        }
        return mDisposableObserver
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDisposableObserver != null) {
            mDisposableObserver!!.dispose()
        }
    }
}
