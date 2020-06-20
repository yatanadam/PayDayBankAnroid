package com.payday.kdogruer.core

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.payday.kdogruer.PayDayApp
import com.payday.kdogruer.R
import com.payday.kdogruer.di.Injectable
import com.payday.kdogruer.utils.Constants
import com.payday.kdogruer.utils.GenericHelper
import com.payday.kdogruer.view.base.BaseActivity
import com.payday.kdogruer.viewmodel.BaseViewModel
import com.payday.kdogruer.viewmodel.ViewModelFactory
import java.io.Serializable
import javax.inject.Inject

/**
 * Base fragment with usefull functions&dagger injections.
 *
 * Created by kaandogruer
 */

abstract class BaseInjectableFragment<VModel : BaseViewModel, DataBinding : ViewDataBinding> : BaseFragment(), Injectable {
    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    @Inject
    lateinit var factory: ViewModelFactory

    abstract val viewModelClass: Class<VModel>
    lateinit var viewModel: VModel
    lateinit var binding: DataBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = createViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return binding.root
    }

    private fun createViewModel(): VModel {
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
        return viewModel
    }

    companion object {
        fun <T> newInstance(fragment: T, o: Any? = null): T {

            PayDayApp.isBackCount = 0

            val bundle = Bundle()
            if (o != null) {
                when (o) {
                    is String -> bundle.putString(Constants.FRAGMENT_DATA, o)
                    is Boolean -> bundle.putBoolean(Constants.FRAGMENT_DATA, o)
                    is Int -> bundle.putInt(Constants.FRAGMENT_DATA, o)
                    is Float -> bundle.putFloat(Constants.FRAGMENT_DATA, o)
                    is Byte -> bundle.putByte(Constants.FRAGMENT_DATA, o)
                    is ByteArray -> bundle.putByteArray(Constants.FRAGMENT_DATA, o)
                    is Char -> bundle.putChar(Constants.FRAGMENT_DATA, o)
                    is Short -> bundle.putShort(Constants.FRAGMENT_DATA, o)
                    is IntArray -> bundle.putIntArray(Constants.FRAGMENT_DATA, o)
                    is Parcelable -> bundle.putParcelable(Constants.FRAGMENT_DATA, o)
                    is Serializable -> bundle.putSerializable(Constants.FRAGMENT_DATA, o)
                }
            }

            (fragment as Fragment).arguments = bundle
            return fragment
        }
    }

    override fun startActivity(intent: Intent?) {
        activity?.startActivity(intent)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    fun onBackPressed() {
        activity?.onBackPressed()
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    fun showFullScreenLoading(mActicity: Activity?) {
        GenericHelper.getInstance().hideKeyboard(activity!!)
        mActicity?.let {
            (mActicity as BaseActivity).showFullScreenLoading()
        }
    }

    fun hideFullScreenLoading(mActicity: Activity?) {
        mActicity?.let {
            (mActicity as BaseActivity).hideFullScreenLoading()
        }
    }
}