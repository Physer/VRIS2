package com.labs.valtech.vris.app.base

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.external.boot.KioskActivity
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.labs.valtech.vris.BR
import com.labs.valtech.vris.VrisApplication




/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
 abstract class BaseActivity<TModel> : KioskActivity(), LazyKodeinAware {
    override val kodein = LazyKodein(appKodein)

    private var _model: TModel? = null
    protected open val Model: TModel
        get() = _model!!

    protected val Application: VrisApplication
        get() = (this.application as VrisApplication)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Application.ActivityContext = this
    }

    fun <TActivity: Activity> setModel(context: TActivity, layout: Int, model: TModel) {
        setContentView(layout)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(context, layout)

        binding.setVariable(BR.activity, this)
        binding.setVariable(BR.model, model)

        binding.executePendingBindings()

        _model = model;
    }

    override fun onStart() {
        super.onStart()

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.hide();
        val decorView = window.decorView
        // Hide the status bar.
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions

        if(Model == null) throw IllegalStateException("Make sure you call the setModel method in the onCreate method")
    }

    override fun onBackPressed() = HideKeyboard()

    fun HideKeyboard() {
        val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val focusView = this.getCurrentFocus()
        if (focusView != null) inputMethodManager.hideSoftInputFromWindow(focusView!!.getWindowToken(), 0)
        supportActionBar?.hide()
    }
}
