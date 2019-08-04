package com.jacob.reddit.databinding.component

import androidx.databinding.DataBindingComponent

import com.jacob.reddit.databinding.adapter.DataBindingAdapters
import com.jacob.reddit.databinding.adapter.DataBindingAdaptersDefault

class DefaultDataBindingComponentImpl : DataBindingComponent {
    override fun getDataBindingAdapters(): DataBindingAdapters {
        return DataBindingAdaptersDefault()
    }
}