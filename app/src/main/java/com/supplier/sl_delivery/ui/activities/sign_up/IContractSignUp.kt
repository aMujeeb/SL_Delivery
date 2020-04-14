package com.supplier.sl_delivery.ui.activities.sign_up

import com.supplier.sl_delivery.ui.activities.base.IBasePresenter
import com.supplier.sl_delivery.ui.activities.base.IBaseView

/**
 * Created by Aurora on 2020-04-12.
 */
interface IContractSignUp {

    interface ISignUpView : IBaseView {
        fun navigateToMain()
        fun setBusinessNameError(error : Int)
        fun setOwnerNameError(error: Int)
        fun setAddressError(error: Int)
        fun setContactNumberError(error: Int)
        fun setRegistrationNumberError(error: Int)
        fun setPasswordError(error: Int)
        fun setConfirmPasswordError(error: Int)
    }

    interface ISignUpPresenter<V : ISignUpView> : IBasePresenter<V> {
        fun setView(view : ISignUpView)
        fun setBusinessName(bName : String)
        fun setOwnerName(oName : String)
        fun setAddress(aName : String)
        fun setContactNumber(contact : String)
        fun setRegistrationNumber(regNo : String)
        fun setPassword(password : String)
        fun setConfirmPassword(cPassword : String)
        fun triggerUserRegister()
    }
}
