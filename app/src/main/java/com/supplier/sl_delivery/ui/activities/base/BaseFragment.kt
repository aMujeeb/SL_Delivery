package com.supplier.sl_delivery.ui.activities.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.supplier.sl_delivery.enums.AlertType

/**
 * Created by Aurora on 2020-04-12.
 */
open class BaseFragment : Fragment(), IBaseView {
    private var mBaseActivity: BaseActivity? = null
    private lateinit var onBackListener: OnBackListener

    private var mBackNavigationEnabled = false

    interface OnBackListener {
        fun onBackPress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBaseActivity = this!!.getSupportActivity()!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        toggleHomeMenu()
    }

    open fun getSupportActivity(): BaseActivity? {
        val activity = activity
        return if (activity != null && activity is BaseActivity) {
            activity
        } else {
            return null
        }
    }

    fun title(): String {
        return ""
    }

    fun addFragment(fragment: BaseFragment, tag: String) {
        val fm = fragmentManager
        val a = fm!!.backStackEntryCount
        fragment.setOnBackListener(object : OnBackListener {
            override fun onBackPress() {
                onResume()
            }
        })
        mBaseActivity?.pushFragment(fragment, tag)
    }

    fun pushFragment(fragment: BaseFragment, tag: String) {
        mBaseActivity?.pushFragment(fragment, tag)
    }

    fun replaceFragment(fragment: BaseFragment, tag: String) {
        mBaseActivity?.replaceFragment(fragment, tag)
    }

    fun replaceRootFragment(fragment: BaseFragment, tag: String) {
        mBaseActivity?.replaceRootFragment(fragment, tag)
    }

    fun setOnBackListener(onBackListener: OnBackListener) {
        this.onBackListener = onBackListener
    }

    fun setTitle(resId: Int) {
        if (resId != -1) {
            mBaseActivity?.setTitle(resId)
        }
    }

    protected fun hideKeyBoard(view: View) {
        mBaseActivity?.hideSoftKeyBoard(view)
    }

    fun setBackNavigationEnabled(enabled: Boolean) {
        this.mBackNavigationEnabled = enabled
        if (enabled) {
            mBaseActivity?.enableBackNavigation()
        } else {
            mBaseActivity?.disableBackNavigation()
        }
    }

    open fun isBackNavigationEnabled(): Boolean {
        return mBackNavigationEnabled
    }

    open fun onBackPressed() {

    }

    fun goBack() {
        if (this.mBackNavigationEnabled) {
            return
        }
        mBaseActivity?.onBackPressed()
    }

    protected fun toggleHomeMenu() {
        if (mBaseActivity == null) {
            return
        }
        setHasOptionsMenu(true)
        if (isBackNavigationEnabled()) {
            mBaseActivity?.showBackNavigation()
        } else {
            return
        }
    }

    override fun displayGeneralErrorMessage(errMessage: String, alertType: AlertType) {
        mBaseActivity?.displayGeneralErrorMessage(errMessage, alertType)
    }

    override fun displayGeneralErrorMessage(resId: Int, alertType: AlertType) {
        mBaseActivity?.displayGeneralErrorMessage(resId, alertType)
    }

    protected fun showBackNavigation() {
        mBackNavigationEnabled = true
        mBaseActivity?.showBackNavigation()
    }

    public override fun hideActionButton() {
        mBaseActivity?.hideActionButton()
    }

    public override fun showActionButton() {
        mBaseActivity?.showActionButton()
    }

    open fun floatingAction() {}

    override fun showProgress() {
        mBaseActivity?.showProgress()
    }

    override fun hideProgress() {
        mBaseActivity?.hideProgress()
    }

    open fun popBackStack() {
        mBaseActivity!!.popFragmentBackStack()
    }

    open fun navigateToHome() {
        mBaseActivity?.navigateToHome()
    }
}
