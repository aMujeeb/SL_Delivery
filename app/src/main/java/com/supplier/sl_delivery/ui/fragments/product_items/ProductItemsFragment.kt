package com.supplier.sl_delivery.ui.fragments.product_items

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.ui.activities.base.BaseFragment
import com.supplier.sl_delivery.ui.adapter.CategoryItemsAdapter
import com.supplier.sl_delivery.ui.fragments.products.IContractProductsView
import kotlinx.android.synthetic.main.fragment_product_items.*

/**
 * Created by Aurora on 2020-04-14.
 */
class ProductItemsFragment : BaseFragment(), IContractProductItems.IProductsItemView {

    private lateinit var            mProductItemsPresenter : IContractProductItems.IProductsPresenter<IContractProductItems.IProductsItemView>
    private lateinit var            mProductsPresenter : IContractProductsView.IProductsPresenter<IContractProductsView.IProductsView>

    private lateinit var            mCategoryItemsAdapter : CategoryItemsAdapter

    companion object {
        fun getInstance(productsPresenter : IContractProductsView.IProductsPresenter<IContractProductsView.IProductsView>) : ProductItemsFragment {
            var instance = ProductItemsFragment()
            instance.mProductsPresenter = productsPresenter
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProductItemsPresenter = ImplCategoryItemsPresenter(mProductsPresenter)
        mProductItemsPresenter.setView(this)

        mCategoryItemsAdapter = CategoryItemsAdapter(activity!!, mProductItemsPresenter)
        mLstProductItems.layoutManager = GridLayoutManager(context, 4)
        mLstProductItems.adapter = mCategoryItemsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mProductItemsPresenter.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        mProductItemsPresenter.onDestroy()
    }

    override fun refreshList() {
        Handler(Looper.getMainLooper()).post {
            mCategoryItemsAdapter.setItems()
            mCategoryItemsAdapter.notifyDataSetChanged()
        }
    }

    override fun showNoResults() {
        Handler(Looper.getMainLooper()).post {
            var mSnack = Snackbar.make(view!!, R.string.categories_error, Snackbar.LENGTH_LONG).setAction(
                R.string.retry
            ) { mProductItemsPresenter.requestProductItems() }
                .setActionTextColor(resources.getColor(R.color.colorAccent))

            var view  = mSnack.view
            view.setBackgroundColor(resources.getColor(R.color.pale_green))
            mSnack.show()
        }
    }

    override fun isBackNavigationEnabled(): Boolean {
        return true
    }
}
