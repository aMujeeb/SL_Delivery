package com.supplier.sl_delivery.ui.fragments.products

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.ui.activities.base.BaseFragment
import com.supplier.sl_delivery.ui.adapter.MainCategoriesAdapter
import com.supplier.sl_delivery.ui.fragments.product_items.ProductItemsFragment
import kotlinx.android.synthetic.main.fragment_main_products.*

/**
 * Created by Aurora on 2020-04-13.
 */
class ProductsFragment : BaseFragment(), IContractProductsView.IProductsView {

    private lateinit var mMainCategoryPresenter: IContractProductsView.IProductsPresenter<IContractProductsView.IProductsView>
    private lateinit var mMainCategoriesAdapter: MainCategoriesAdapter

    companion object {
        fun getInstance(): ProductsFragment {
            return ProductsFragment()
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
        return inflater.inflate(R.layout.fragment_main_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMainCategoryPresenter = ImplProductsPresenter()
        mMainCategoryPresenter.setView(this)

        mMainCategoriesAdapter = MainCategoriesAdapter(activity!!, mMainCategoryPresenter)
        mProductsList.layoutManager = GridLayoutManager(context, 3)
        mProductsList.adapter = mMainCategoriesAdapter
    }

    override fun refreshView() {
        Handler(Looper.getMainLooper()).post {
            mMainCategoriesAdapter.setItems()
            mMainCategoriesAdapter.notifyDataSetChanged()
        }
    }

    override fun showNoResults() {
        Handler(Looper.getMainLooper()).post {
            var mSnack = Snackbar.make(view!!, R.string.categories_error, Snackbar.LENGTH_LONG).setAction(
                R.string.retry
            ) { mMainCategoryPresenter.requestProducts() }
                .setActionTextColor(resources.getColor(R.color.colorAccent))

            var view  = mSnack.view
            view.setBackgroundColor(resources.getColor(R.color.pale_green))
            mSnack.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mMainCategoryPresenter.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        mMainCategoryPresenter.onDestroy()
    }

    override fun launchItemsView() {
        Handler(Looper.getMainLooper()).post {
            replaceFragment(ProductItemsFragment.getInstance(mMainCategoryPresenter), "product_items")
        }
    }
}
