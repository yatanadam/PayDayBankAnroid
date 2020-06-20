package com.payday.kdogruer.view.main

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.payday.kdogruer.core.BaseInjectableFragment
import com.payday.kdogruer.data.local.entity.CustomerEntity
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.AuthRequestModel
import com.payday.kdogruer.databinding.FragmentLoginBinding
import com.payday.kdogruer.databinding.FragmentMainBinding

import com.payday.kdogruer.di.Injectable
import com.payday.kdogruer.view.main.MainActivity
import com.payday.kdogruer.viewmodel.CustomerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : BaseInjectableFragment<CustomerViewModel, FragmentMainBinding>(), Injectable {
    override val layoutResourceId: Int = com.payday.kdogruer.R.layout.fragment_main
    override val viewModelClass: Class<CustomerViewModel> = CustomerViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.customer = (activity as MainActivity).binding.customer
    }

}
