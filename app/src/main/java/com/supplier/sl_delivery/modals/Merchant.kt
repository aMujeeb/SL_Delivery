package com.supplier.sl_delivery.modals

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.supplier.sl_delivery.rest.APIClient
import com.supplier.sl_delivery.rest.responses.LoginResponse
import com.supplier.sl_delivery.rest.responses.MerchantRegisterResponse
import com.supplier.sl_delivery.view_modals.LoginViewModal
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Aurora on 2020-03-30.
 */
data class Merchant(var contactNumber: String?, var password: String?) {
    var businessName: String? = null
    var id: String? = null
    var ownerName: String? = null
    var regNumber: String? = null
    var address: String? = null
    var passwordConform: String? = null

    fun registerMerchant(): Observable<MerchantRegisterResponse?> {
        return APIClient.registerUser(this).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())
    }

    fun loginMerchant(): Observable<LoginResponse?> {
        return APIClient.loginUser(this).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        )
    }

    val isUserNameValid get() = !TextUtils.isEmpty(contactNumber) && contactNumber!!.length == 10
    val isPasswordCorrect get() = !TextUtils.isEmpty(password) && password!!.length > 5
    val isConfirmPasswordMatching get() = password == passwordConform
    val isRegisterNumberValid get() = !TextUtils.isEmpty(regNumber) && regNumber!!.length >= 10
}
