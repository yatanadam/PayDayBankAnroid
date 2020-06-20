package com.payday.kdogruer.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.payday.kdogruer.viewmodel.BaseViewModel
/**
 * Base fragment with usefull functions&dagger injections&base adapter&drawer.
 *
 * Created by kaandogruer
 */
abstract class BaseMenuListActivity<T : ViewDataBinding, K : ViewDataBinding, L, V : BaseViewModel> : BaseInjectableMenuActivity<V, T>() {

    @get:LayoutRes
    protected abstract val viewHolderLayoutId: Int
    lateinit var adapter: BaseAdapter<K, L>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = object : BaseAdapter<K, L>(viewHolderLayoutId, true) {
            override fun bindView(binding: K, item: L?, adapterPosition: Int) {
                item?.let { this@BaseMenuListActivity.bindView(binding, item, adapterPosition) }
            }

            override fun clickListener(item: L?, position: Int, binding: K) {
                item?.let { onAdapterItemClickListener(item, position, binding) }

            }
        }
    }

    abstract fun bindView(binding: K, item: L, adapterPosition: Int)

    open fun onAdapterItemClickListener(item: L, position: Int, binding: K) {

    }
}