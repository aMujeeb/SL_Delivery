package com.supplier.sl_delivery.ui.activities.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.supplier.sl_delivery.enums.AlertType
import com.supplier.sl_delivery.R
import kotlinx.android.synthetic.main.dialog_general_error.view.*
import kotlin.system.exitProcess

/**
 * Created by Aurora on 2020-04-11.
 */
open class BaseActivity : AppCompatActivity(), IBaseView {

    private var mFragmentManager: FragmentManager = supportFragmentManager
    private var mIsBackNavigationEnabled: Boolean = false
    protected var mSelectedFragment: BaseFragment? = null
    protected lateinit var mTitle: CharSequence

    private var mAlertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
    }

    private fun setUpView() {}

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
    }

    open fun onActionButtonClick() {
        mSelectedFragment!!.floatingAction()
    }

    fun replaceFragment(fragment: BaseFragment, tag: String) {
        mSelectedFragment = fragment
        mTitle = title
        var transaction: FragmentTransaction = mFragmentManager.beginTransaction()
        transaction.replace(R.id.item_detail_container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun pushFragment(fragment: BaseFragment, tag: String) {
        mSelectedFragment = fragment
        mTitle = title
        var transaction: FragmentTransaction = mFragmentManager.beginTransaction()
        transaction.add(R.id.item_detail_container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun replaceRootFragment(fragment: BaseFragment, tag: String) {
        clearFragmentBackStackList()
        mSelectedFragment = fragment
        mTitle = title
        var transaction: FragmentTransaction = mFragmentManager.beginTransaction()
        transaction.replace(R.id.item_detail_container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun getFragmentByTag(tag: String): BaseFragment? {
        val fragment = mFragmentManager.findFragmentByTag(tag)
        return if (fragment != null) {
            fragment as BaseFragment?
        } else null
    }

    private fun clearFragmentBackStackList() {
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        for (i in 0 until mFragmentManager.backStackEntryCount) {
            mFragmentManager.popBackStackImmediate()
        }
    }

    open fun popFragmentBackStack() {
        mFragmentManager.popBackStack()
    }

    private fun getBackStackCount(): Int {
        return mFragmentManager.backStackEntryCount
    }

    protected fun exitApplication() {
        finishAffinity()
        exitProcess(0)
    }

    fun disableBackNavigation() {
        mIsBackNavigationEnabled = false
    }

    fun enableBackNavigation() {
        mIsBackNavigationEnabled = true
    }

    fun isBackNavigationEnabled(): Boolean {
        return mIsBackNavigationEnabled
    }

    override fun onBackPressed() {
        if (getBackStackCount() == 0) {
            if (mSelectedFragment != null) {
                mSelectedFragment!!.onBackPressed()
            }
            disableBackNavigation()
            super.onBackPressed()
            //return
        } else {
            if (mSelectedFragment != null) {
                if (mSelectedFragment!!.isBackNavigationEnabled()) {
                    title = mTitle
                    mSelectedFragment!!.onBackPressed()
                    mSelectedFragment = mFragmentManager.fragments[mFragmentManager.fragments.size - 1] as BaseFragment
                    if (getBackStackCount() == 1) {
                        Handler(Looper.getMainLooper()).post(Runnable {
                            hideBackNavigation()
                        })
                    }
                    super.onBackPressed()
                }
            } else {
                super.onBackPressed()
            }
        }
    }

    fun hideSoftKeyBoard(view: View?) {
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun displayGeneralErrorMessage(errMessage: String, alertType: AlertType) {
        val alertBuilder = AlertDialog.Builder(this)
        var inflater: LayoutInflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.dialog_general_error, null)
        alertBuilder.setView(dialogView)
        dialogView.mLblMessage.text = errMessage
        when (alertType) {
            AlertType.ALERT_TYPE_ALERT -> {
                dialogView.mLblHeader.text = resources.getString(R.string.alert)
            }
            AlertType.ALERT_TYPE_ERROR -> {
                dialogView.mLblHeader.text = resources.getString(R.string.error)
            }
            AlertType.ALERT_TYPE_INFORMATION -> {
                dialogView.mLblHeader.text = resources.getString(R.string.information)
            }
        }
        dialogView.mBtnOk.setOnClickListener {
            if (mAlertDialog != null) {
                mAlertDialog!!.dismiss()
            }
        }
        mAlertDialog = alertBuilder.create();
        mAlertDialog!!.show()
    }

    override fun displayGeneralErrorMessage(resId: Int, alertType: AlertType) {
        displayGeneralErrorMessage(resources.getString(resId), alertType)
    }

    override fun showProgress() {}
    override fun hideProgress() {}
    open fun showBackNavigation() {}
    open fun hideBackNavigation() {}
    override fun hideActionButton() {}
    override fun showActionButton() {}
    open fun navigateToHome() {}
}
