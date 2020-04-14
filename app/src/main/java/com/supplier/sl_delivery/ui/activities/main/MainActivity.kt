package com.supplier.sl_delivery.ui.activities.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.databinding.ActivityMainBinding
import com.supplier.sl_delivery.ui.activities.base.BaseActivity
import com.supplier.sl_delivery.ui.adapter.MainMenuAdapter

import com.supplier.sl_delivery.ui.fragments.home.HomeFragment
import com.supplier.sl_delivery.ui.fragments.products.ProductsFragment
import com.supplier.sl_delivery.view_modals.MainViewModal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var        mMainViewBinder : ActivityMainBinding
    private lateinit var        mMainViewModal: MainViewModal

    private lateinit var        mMainMenuAdapter : MainMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainViewBinder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mMainViewModal = ViewModelProviders.of(this).get(MainViewModal :: class.java)
        mMainViewBinder.mainViewModal = mMainViewModal

        setSupportActionBar(toolbar)
        toolbar.title = title
        setupRecyclerView(mLstMainMenu)
        launchHomeFragment()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        mMainMenuAdapter = MainMenuAdapter(mMainViewModal, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mMainMenuAdapter
        mMainMenuAdapter.setMenuItems(mMainViewModal.getAllMainMenuItems())

        mMainViewModal.mSelectedMenuItem.observe(this, Observer {
            if(it == 0){
                launchHomeFragment()
            } else if(it == 1) {
                launchMainCategoryFragment()
            }
        })
    }

    private fun launchHomeFragment() {
        replaceRootFragment(HomeFragment.getInstance(), "home")
    }

    private fun launchMainCategoryFragment() {
        replaceRootFragment(ProductsFragment.getInstance(), "products")
    }

    override fun showProgress() {
        mProgressMain.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            mProgressMain.visibility = View.GONE
        }
    }
}
