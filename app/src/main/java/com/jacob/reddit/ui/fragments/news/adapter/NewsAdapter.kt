package com.jacob.reddit.ui.fragments.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.jacob.reddit.databinding.NewsItemBinding
import com.jacob.reddit.model.News

class NewsAdapter(
    private val listener: OnItemClickListener,
    private val threshold: Double,
    private val onEndReached: () -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val dataList = NewsStorage()

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)

        return NewsViewHolder(binding).apply {
            binding.newsRoot.setOnClickListener {
                listener.onItemClicked(dataList[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(@NonNull holder: NewsViewHolder, position: Int) {
        holder.binding.model = dataList[position]
        holder.binding.executePendingBindings()
        checkIfReachedEnd(position)
    }

    override fun getItemCount(): Int = dataList.size()

    private fun checkIfReachedEnd(position: Int) {
        if (position > itemCount * threshold) onEndReached.invoke()
    }

    fun addToHead(@NonNull news: ArrayList<News>) {
        dataList.addAll(0, news)
        notifyItemRangeChanged(0, news.size)
    }

    fun addToTail(@NonNull news: ArrayList<News>) {
        val start = dataList.size() - 1
        dataList.addAll(news)
        val count = news.size
        notifyItemRangeChanged(start, count)
    }

    class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    class NewsStorage {
        private val data = java.util.ArrayList<News>()

        fun addAll(index: Int, newsList: MutableList<News>) {
            newsList.removeAll(data)
            data.addAll(index, newsList)
        }

        fun addAll(newsList: MutableList<News>) {
            newsList.removeAll(data)
            data.addAll(newsList)
        }

        operator fun get(position: Int) = data[position]

        fun size() = data.size
    }

    interface OnItemClickListener {
        fun onItemClicked(news: News)
    }
}
