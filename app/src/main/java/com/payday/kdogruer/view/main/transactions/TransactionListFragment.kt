package com.payday.kdogruer.view.main.transactions

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.lifecycle.Observer
import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseListFragment
import com.payday.kdogruer.data.local.entity.TransactionEntity
import com.payday.kdogruer.data.local.enum.TransactionCategory
import com.payday.kdogruer.databinding.FragmentTransactionListBinding
import com.payday.kdogruer.databinding.ItemTransactionListBinding
import com.payday.kdogruer.di.Injectable
import com.payday.kdogruer.utils.Constants
import com.payday.kdogruer.view.components.animation.ScaleAnimToShowFrame
import com.payday.kdogruer.view.components.piechart.PieSlice
import com.payday.kdogruer.view.main.MainActivity
import com.payday.kdogruer.viewmodel.TransactionsViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by kaandogruer.
 */


class TransactionListFragment : BaseListFragment<FragmentTransactionListBinding, ItemTransactionListBinding, TransactionEntity, TransactionsViewModel>(), Injectable,TransactionListCallback {
    override val layoutResourceId = R.layout.fragment_transaction_list
    override val viewModelClass: Class<TransactionsViewModel> = TransactionsViewModel::class.java
    override val viewHolderLayoutId: Int = R.layout.item_transaction_list

    private val mData by lazy { arguments?.getString(Constants.FRAGMENT_DATA) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setNavTitle(getString(R.string.transactions))
        binding.rvTransactions.adapter = adapter
        binding.callback = this
        getTransactionList()
    }

    lateinit var totalTransactions : ArrayList<TransactionEntity>
    //get transaction list from server and save db
    fun getTransactionList() {
        //showLoading()
        viewModel.setTransactionRequest(mData!!)
        viewModel.transactionsResponseLiveData
                .observe(this, Observer {
                    binding.resource = it
                    if (it.data != null && it.data.size > 0) {
                        adapter.items = ArrayList(it.data)
                        totalTransactions = ArrayList(it.data)
                        getTransactionListByRange(indexOfMonth)
                        viewModel.transactionsResponseLiveData.removeObservers(this)
                    }
                })
    }
    var indexOfMonth = 0
    fun getTransactionListByRange(index:Int) {
        //showLoading()
        // Create a convert function, String -> LocalDateTime
        var dateStart = totalTransactions.get(index).transactionDate!!
        val c = Calendar.getInstance()
        c.time = dateStart
        c.add(Calendar.MONTH, -1)
        var dateEnd = c.time
        adapter.items!!.clear()
        binding.tvHeader.setText(SimpleDateFormat("MMMM").format(dateStart))
        viewModel.getTansactionRange(dateEnd,dateStart)!!.observe(this, Observer {
            if(it.size >0) {
                adapter.items = ArrayList(it)
                fillPie()
            }
        })
    }

    override fun onMonthChangeClick() {
        if(indexOfMonth+1 < totalTransactions!!.size) {
            indexOfMonth = indexOfMonth + 1
            getTransactionListByRange(indexOfMonth)
        }
    }

    fun fillPie(){
        var totalSpent:Double = 0.0
        var totalEarned:Double = 0.0
        adapter.items!!.forEach {
            if(it.transactionAmount!!.contains("-"))
                totalSpent += it.transactionAmount!!.toDouble()
            if(!it.transactionAmount!!.contains("-"))
                totalEarned += it.transactionAmount!!.toDouble()
        }
        val groups = adapter.items!!.groupingBy({ item ->
            item.transactionCategory
        }).eachCount()

        for (i in 0..groups.size-1){
            var slice = PieSlice()
            slice.setColor(Color.parseColor(TransactionCategory.from(adapter.items!!.get(i).transactionCategory!!)!!.colorHex))
            slice.setValue((groups.values.toIntArray().get(i).toDouble()/groups.size.toDouble())*100)
            binding.piegraph.addSlice(slice)
        }
        binding.piegraph.requestFocus()
        binding.tvCategoryName.setTextColor(Color.parseColor(TransactionCategory.from(adapter.items!!.get(0).transactionCategory!!)!!.colorHex))
        binding.tvCategoryName.text =adapter.items!!.get(0).transactionCategory
        binding.tvTotalSpent.text = totalSpent.toString()
        binding.tvTotalEarned.text = totalEarned.toString()

        binding.btNextSlice.setOnClickListener(View.OnClickListener {

            var anim = ScaleAnimToShowFrame(1.0f, 0.4f, 1.0f,
                    1.0f, 200, binding.tvCategoryName)
            anim.setFillAfter(true)
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationStart(animation: Animation?) {
                    binding.tvCategoryName.setTextColor(Color.parseColor(TransactionCategory.from(adapter.items!!.get(binding.piegraph.getActiveSlice()).transactionCategory!!)!!.colorHex))
                    binding.tvCategoryName.text =adapter.items!!.get(binding.piegraph.getActiveSlice()).transactionCategory

                    // binding.tvCategoryLastYearValue.text = lastValueStrings.get(binding.piegraph.getActiveSlice())

                    //binding.tvCategoryThisYearValue.text = thisValueStrings.get(binding.piegraph.getActiveSlice())
                    binding.piegraph.openNextWithAnimate()
                }
                // All the other override functions
            })
            binding.tvCategoryName.startAnimation(anim)
        })
    }

    override fun bindView(binding: ItemTransactionListBinding, item: TransactionEntity, adapterPosition: Int) {
        binding.transaction = item
    }

    override fun onAdapterItemClickListener(item: TransactionEntity, position: Int, binding: ItemTransactionListBinding) {

    }


}
