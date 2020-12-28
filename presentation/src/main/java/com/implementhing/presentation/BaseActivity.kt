package com.implementhing.presentation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this is BindingSupport) {
            binding = invoke(this, layoutResId)
        } else {
            setContentView(layoutResId)
        }
    }

    private fun binding(): B {
        if (this is ViewModelSupport) {
            return binding
        } else {
            throw IllegalStateException("${this::class.simpleName} does not support Binding")
        }
    }
}