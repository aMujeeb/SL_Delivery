package com.supplier.sl_delivery.rest

import com.supplier.sl_delivery.modals.Merchant
import com.supplier.sl_delivery.rest.responses.CategoriesResponse
import com.supplier.sl_delivery.rest.responses.ItemsByCategoryResponse
import com.supplier.sl_delivery.rest.responses.LoginResponse
import com.supplier.sl_delivery.rest.responses.MerchantRegisterResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Aurora on 2020-04-10.
 */
interface IAPIClientService {

    @POST("merchant/registor")
    open fun registerMerchant(@Body merchant: Merchant): Observable<MerchantRegisterResponse?>?

    @POST("merchant/login")
    open fun loginMerchant(
        @Body merchant: Merchant
    ): Observable<LoginResponse?>?

    @GET("category")
    open fun getAllCategories(): Observable<CategoriesResponse?>?

    @GET("item/by-category/{name}")
    open fun getItemsByCategory(@Path("name") catName: String): Observable<ItemsByCategoryResponse>?
}
