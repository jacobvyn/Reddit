package com.jacob.reddit.ui.activities

import android.os.Bundle
import com.jacob.reddit.R
import com.jacob.reddit.core.CoreActivity
import com.jacob.reddit.ui.fragments.details.DetailsFragment
import com.jacob.reddit.ui.fragments.news.NewsFragment
import com.jacob.reddit.utils.addFragment

class MainActivity : CoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(NewsFragment(), R.id.container)
    }

    fun showDetailsView(url: String) {
        addFragment(DetailsFragment.newInstance(url), R.id.container, true)
    }
}