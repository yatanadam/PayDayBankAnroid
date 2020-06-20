package com.payday.kdogruer.view.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.payday.kdogruer.core.BaseInjectableFragment
import com.payday.kdogruer.data.Status
import com.payday.kdogruer.data.local.entity.CustomerEntity
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.AuthRequestModel
import com.payday.kdogruer.databinding.FragmentLoginBinding

import com.payday.kdogruer.di.Injectable
import com.payday.kdogruer.view.main.MainActivity
import com.payday.kdogruer.viewmodel.CustomerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseInjectableFragment<CustomerViewModel, FragmentLoginBinding>(), Injectable,LoginCallback {
    override val layoutResourceId: Int = com.payday.kdogruer.R.layout.fragment_login
    override val viewModelClass: Class<CustomerViewModel> = CustomerViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.callback = this@LoginFragment
        binding.etMail.setText("Nadiah.Spoel@example.com")
        binding.etPass.setText("springs")
    }

    override fun onLoginClick() {
        viewModel.setAuthRequestModel(AuthRequestModel(binding.etPass.text.toString(),binding.etMail.text.toString()))
        viewModel.customerResponseLiveData.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    activity!!.finish()
                    startActivity(Intent(activity!!,MainActivity::class.java))
                }
            }
        })
    }

    override fun onSignUpClick() {
        //TODO("Not yet implemented")
    }

    override fun onForgotPasswordClick() {
        //TODO("Not yet implemented")
    }
}
