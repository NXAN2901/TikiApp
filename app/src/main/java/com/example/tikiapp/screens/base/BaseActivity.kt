package com.example.tikiapp.screens.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<out BaseNavigator>> :
    AppCompatActivity() {

    private lateinit var viewModel: V
    private lateinit var viewDataBinding: T

    abstract fun initViews()

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getViewModelVariable(): Int

    fun getDataBinding() = viewDataBinding

    protected open fun initBindingVariables() {
        // override to execute binding here
    }

    private fun performDataBinding() {
        val viewModel = getViewModel()
        viewDataBinding = (DataBindingUtil.setContentView(this, getLayoutId()) as T).also {binding ->
            binding.lifecycleOwner = this
            binding.setVariable(getViewModelVariable(), viewModel)
            initBindingVariables()
            binding.executePendingBindings()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initViews()
    }

}