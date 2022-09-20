package com.example.mymusicapp.presentation.apadters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymusicapp.R
import com.example.mymusicapp.domain.models.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    inner class PostViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
    private val differCallback = object: DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.postId==newItem.postId
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
return PostViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.post_item,
        parent,
        false
    )
)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       val post = differ.currentList[position]
        holder.itemView.apply{
Glide.with(this).load(post.imageUrl).into(im_post)
tv_post.text = post.postText
        }
    }

    override fun getItemCount(): Int {
    return differ.currentList.size
    }

}