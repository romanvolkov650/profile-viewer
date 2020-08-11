package com.romanvolkov.profileviewer.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.romanvolkov.profileviewer.GlideApp
import com.romanvolkov.profileviewer.R
import com.romanvolkov.profileviewer.model.entity.Profile

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<Profile>()

    lateinit var onClickListener: (Profile) -> Unit

    fun updateItems(itemList: List<Profile>) {
        items = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_profile_item, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder)
            holder.bind(item = items[position])
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.iv_photo)
        private val name = itemView.findViewById<TextView>(R.id.tv_name)

        @SuppressLint("SetTextI18n")
        fun bind(item: Profile) {
            GlideApp
                .with(itemView)
                .load(item.picture.medium)
                .into(image)
            name.text = "${item.name.title} ${item.name.first} ${item.name.last}"
        }

        init {
            itemView.setOnClickListener { onClickListener(items[layoutPosition]) }
        }
    }

}