package com.recyclerviewapp

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kathayat.myapplication.ImageFullActivity
import com.kathayat.myapplication.Images
import com.kathayat.myapplication.R


class ImageAdapter(private var context: Context, private var imagesList:ArrayList<Images>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView?=null

        init {
            image = itemView.findViewById(R.id.imageRecycler)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.imagecustomview,parent,false)
        return ImageViewHolder(view)

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = imagesList[position]
        Glide.with(context)
            .load(currentImage.imagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
        holder.image?.setOnClickListener {
            val intent = Intent(context, ImageFullActivity::class.java)
            intent.putExtra("path", currentImage.imagePath)
            intent.putExtra("path", currentImage.imageName)
        }
    }
    override fun getItemCount(): Int {
        return imagesList.size
    }

}