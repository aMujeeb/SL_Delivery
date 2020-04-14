package com.supplier.sl_delivery.rest

import androidx.lifecycle.MutableLiveData
import com.supplier.sl_delivery.modals.Merchant
import com.supplier.sl_delivery.rest.responses.CategoriesResponse
import com.supplier.sl_delivery.rest.responses.ItemsByCategoryResponse
import com.supplier.sl_delivery.rest.responses.LoginResponse
import com.supplier.sl_delivery.rest.responses.MerchantRegisterResponse
import com.supplier.sl_delivery.utils.LoggerUtil
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Aurora on 2020-04-10.
 */
class APIClient {
    companion object {
        private var mSLDeliveryClient: IAPIClientService =
            APIRestFactory.apiInstance(WebApiRequestInterceptor())

        fun registerUser(mMerchant: Merchant): Observable<MerchantRegisterResponse?> {
            return mSLDeliveryClient.registerMerchant(mMerchant)!!
        }

        fun loginUser(mMerchant: Merchant): Observable<LoginResponse?> {
            return mSLDeliveryClient.loginMerchant(mMerchant)!!
        }

        fun getAllCategoriesList() : Observable<CategoriesResponse?>? {
            return mSLDeliveryClient.getAllCategories()
        }

        fun getAllItemsUnderCategory(catName : String) : Observable<ItemsByCategoryResponse>? {
            return mSLDeliveryClient.getItemsByCategory(catName)
        }
    }
}
