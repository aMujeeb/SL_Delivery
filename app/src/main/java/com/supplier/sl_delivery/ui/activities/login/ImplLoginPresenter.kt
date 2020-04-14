package com.supplier.sl_delivery.ui.activities.login

import android.content.Context
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.enums.AlertType
import com.supplier.sl_delivery.modals.Merchant
import com.supplier.sl_delivery.rest.responses.LoginResponse
import com.supplier.sl_delivery.ui.activities.base.BasePresenter
import com.supplier.sl_delivery.utils.LoggerUtil
import com.supplier.sl_delivery.utils.SLDeliveryConstants
import com.supplier.sl_delivery.utils.shared_pref.SharedPreferenceGenerator
import com.supplier.sl_delivery.utils.shared_pref.SharedPreferenceUtil
import io.reactivex.observers.DisposableObserver

/**
 * Created by Aurora on 2020-04-12.
 */
class ImplLoginPresenter<V : IContractLogin.ILoginView>(var mContext : Context) : BasePresenter<V>(),
    IContractLogin.ILoginPresenter<V> {

    private lateinit var mLoginView: IContractLogin.ILoginView
    private var mMerchant: Merchant = Merchant("", "")
    private var mDisposableObserver: DisposableObserver<LoginResponse>? = null

    override fun setView(view: IContractLogin.ILoginView) {
        mLoginView = view
    }

    override fun triggerLogin() {
        if (!mMerchant.isUserNameValid) {
            mLoginView.showUserNameError(R.string.mobile_number_error)
        } else if (!mMerchant.isPasswordCorrect) {
            mLoginView.showPasswordError(R.string.password_error)
        } else {
            mLoginView.showProgress()
            if(mDisposableObserver != null){
                mDisposableObserver!!.dispose()
            }
            mMerchant.loginMerchant().subscribeWith(createLoginObserver())
        }
    }

    override fun setUserName(name: String) {
        mMerchant.contactNumber = name
    }

    override fun setPassword(password: String) {
        mMerchant.password = password
    }

    private fun createLoginObserver(): DisposableObserver<LoginResponse>? {
        mDisposableObserver = object : DisposableObserver<LoginResponse>() {
            override fun onComplete() {
                LoggerUtil.logMessage("Completed Observer")
            }

            override fun onNext(t: LoginResponse) {
                mLoginView.hideProgress()
                if(t?.data != null) {
                    LoggerUtil.logMessage("Login Success Received :" + t.data!!.accessToken)
                    t.data!!.accessToken?.let {
                        SharedPreferenceUtil.saveStringValue(SharedPreferenceGenerator.getInstance(mContext), SLDeliveryConstants.ACCESS_TOKEN ,
                            it
                        )
                    }
                    mLoginView.navigateToMain()
                } else {
                    mLoginView.displayGeneralErrorMessage(R.string.login_error, AlertType.ALERT_TYPE_ERROR)
                }
            }

            override fun onError(e: Throwable) {
                LoggerUtil.logMessage("Error Observer")
                mLoginView.hideProgress()
                mLoginView.displayGeneralErrorMessage(R.string.login_error, AlertType.ALERT_TYPE_ERROR)
            }
        }
        return mDisposableObserver
    }

    override fun onDestroy() {
        if (mDisposableObserver != null) {
            mDisposableObserver!!.dispose()
        }
    }
}
