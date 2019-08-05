package com.jacob.reddit.ui.fragments.details

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.jacob.reddit.R
import com.jacob.reddit.core.CoreFragment
import com.jacob.reddit.databinding.FragmentDetailsBinding
import com.jacob.reddit.di.Injector
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : CoreFragment<DetailsPresenter, FragmentDetailsBinding>(), DetailsView,
    BaseWebView.ProgressListener {
    override fun getLayoutId() = R.layout.fragment_details

    override fun createPresenter() = DetailsPresenter(Injector.injectRepository())

    override fun onViewReady() {
        presenter?.onViewCreated(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(URL_KEY)
        if (url == null) activity?.onBackPressed()
        webview.progressListener = this
        webview.loadUrl(url)
    }

    companion object {
        const val URL_KEY = "URL_KEY"

        fun newInstance(url: String): Fragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(URL_KEY, url)
                }
            }
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        val anim = if (enter) R.anim.slide_in_right else R.anim.slide_out_right
        return AnimationUtils.loadAnimation(context, anim)
    }

    override fun onDestroyView() {
        showProgress(false)
        super.onDestroyView()
    }

    override fun onBackPress(): Boolean {
        return if (webview.canGoBack()) {
            webview.goBack()
            true
        } else false
    }
}