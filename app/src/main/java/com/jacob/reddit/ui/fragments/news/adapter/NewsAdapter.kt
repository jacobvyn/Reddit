package com.jacob.reddit.ui.fragments.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.jacob.reddit.R
import com.jacob.reddit.model.News

open class NewsAdapter(private val threshold: Double, private val onEndReached: () -> Unit) :
    RecyclerView.Adapter<NewsAdapter.BaseViewHolder>() {

    override fun getItemCount(): Int = mData.size()

    private val mData = NewsStorage()


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: BaseViewHolder, position: Int) {
        val news = mData[position]
        holder.author.text = news.author
        holder.likes.text = news.likes.toString()
        holder.messages.text = news.comments.toString()
        holder.title.text = news.title

//        val icon = if (news.isImage()) news.getThumbnail() else UiUtils.getIconRes()
//        Picasso.get().load(icon).into(holder.image)
        checkIfReachedEnd(position)
    }

    private fun checkIfReachedEnd(position: Int) {
        if (position > itemCount * threshold) onEndReached.invoke()
    }

//    override fun getItemViewType(position: Int): Int {
//        return mData[position].getType()
//    }

    fun addToHead(@NonNull news: ArrayList<News>) {
        mData.addAll(0, news)
        notifyItemRangeChanged(0, news.size)
    }

    fun addToTail(@NonNull news: ArrayList<News>) {
        val start = mData.size() - 1
        mData.addAll(news)
        val count = news.size
        notifyItemRangeChanged(start, count)
    }

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val likes: TextView = itemView.findViewById(R.id.news_likes_count)
        val title: TextView = itemView.findViewById(R.id.news_item_title)
        val author: TextView = itemView.findViewById(R.id.news_author)
        val messages: TextView = itemView.findViewById(R.id.news_messages_count)
        val image: ImageView = itemView.findViewById(R.id.news_image_view)
    }

    class NewsStorage {
        private val mData = java.util.ArrayList<News>()

        fun addAll(index: Int, newsList: MutableList<News>) {
            newsList.removeAll(mData)
            mData.addAll(index, newsList)
        }

        fun addAll(newsList: MutableList<News>) {
            newsList.removeAll(mData)
            mData.addAll(newsList)
        }

        operator fun get(position: Int): News {
            return mData[position]
        }

        fun size(): Int {
            return mData.size
        }

        fun clear() {
            mData.clear()
        }
    }
}
