package com.payday.kdogruer.core

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.payday.kdogruer.R
import com.payday.kdogruer.databinding.ActivityBaseBinding
import com.payday.kdogruer.view.base.BaseActivity
import com.payday.kdogruer.viewmodel.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base activity with usefull functions&dagger injections.
 *
 * Created by kaandogruer
 */

abstract class BaseInjectableActivity<VModel : ViewModel, DataBinding : ViewDataBinding> : BaseActivity(), HasSupportFragmentInjector {
    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    @Inject
    lateinit var factory: ViewModelFactory

    abstract val viewModelClass: Class<VModel>
    lateinit var viewModel: VModel
    lateinit var binding: DataBinding
    lateinit var dataBinding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)
        viewModel = createViewModel()
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResourceId, dataBinding.llMainContent, true)

    }

    //abstract fun getLayoutID(): Int

    private fun createViewModel(): VModel {
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
        return viewModel
    }

    fun <T : ViewDataBinding> putContentView(id: Int): T {
        return DataBindingUtil.inflate(layoutInflater, id, dataBinding.llMainContent, true)
    }

    //abstract fun initViewModelClass(): Class<VModel>

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }

    protected fun setNavMenu(mTitle: String) {
        setSupportActionBar(binding.root.findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.title = mTitle
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        lockDrawer()

    }


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}