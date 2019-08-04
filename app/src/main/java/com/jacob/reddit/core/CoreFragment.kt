package com.jacob.reddit.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jacob.reddit.ui.activities.MainActivity

abstract class CoreFragment<P : CorePresenter<*>, V : ViewDataBinding> : Fragment(), CoreView {
    var presenter: P? = null
    var dataBinding: V? = null

    abstract fun getLayoutId(): Int
    abstract fun createPresenter(): P
    abstract fun onViewReady()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        presenter = createPresenter()
        onViewReady()
        return dataBinding?.root
    }

    override fun showMessage(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(error: Throwable) {
        showMessage(error.message!!)
    }

    override fun showProgress(show: Boolean) {
        val host = activity
        if (host is MainActivity) host.showProgress(show)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detachView()
    }
}