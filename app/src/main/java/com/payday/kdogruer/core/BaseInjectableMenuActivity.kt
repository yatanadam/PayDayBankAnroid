package com.payday.kdogruer.core


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.payday.kdogruer.R
import com.payday.kdogruer.databinding.ActivityBaseBinding
import com.payday.kdogruer.view.base.BaseActivity
import com.payday.kdogruer.view.base.LeftMenuClicksCallback
import com.payday.kdogruer.viewmodel.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * Base activity with usefull functions&dagger injections&drawer.
 *
 * Created by kaandogruer
 */

abstract class BaseInjectableMenuActivity<VModel : ViewModel, DataBinding : ViewDataBinding> : BaseActivity(), HasSupportFragmentInjector, LeftMenuClicksCallback {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    abstract val viewModelClass: Class<VModel>

    @Inject
    lateinit var factory: ViewModelFactory

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

    private fun createViewModel(): VModel {
        viewModel = ViewModelProviders.of(this, factory).get(viewModelClass)
        return viewModel
    }

    fun <T : ViewDataBinding> putContentView(id: Int): T {
        return DataBindingUtil.inflate(layoutInflater, id, dataBinding.llMainContent, true)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    //region Menu
    private var drawerToggle: ActionBarDrawerToggle? = null

    protected fun setNavMenu(title: String) {
        setSupportActionBar(binding.root.findViewById(R.id.toolbar))
        drawerToggle = setupDrawerToggle()
        supportActionBar?.let {
            it.title = title
            it.setHomeAsUpIndicator(R.drawable.menu_icon)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }
    fun setNavTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(this, drawerLayout, binding.root.findViewById(R.id.toolbar), R.string.drawer_open, R.string.drawer_close)
    }
    //endregion



}