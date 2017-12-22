package com.labs.valtech.vris.app.base

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.labs.valtech.vris.BR
import com.labs.valtech.vris.R
import com.labs.valtech.vris.databinding.ActivityMainBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
 abstract class BaseActivity<TModel> : AppCompatActivity(), LazyKodeinAware {
    override val kodein = LazyKodein(appKodein)

    private var _model: TModel? = null
    protected open val Model: TModel
        get() = _model!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun setModel(layout: Int, model: TModel) {
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, layout)

        binding.setVariable(BR.activity, this)
        binding.setVariable(BR.model, model)

        binding.executePendingBindings()

        _model = model;
    }

    override fun onStart() {
        super.onStart()
        if(Model == null) throw IllegalStateException("Make sure you call the setModel method in the onCreate method")
    }
}
