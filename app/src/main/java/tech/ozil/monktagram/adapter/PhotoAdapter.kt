package tech.ozil.monktagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.ozil.monktagram.databinding.PhotoRowLayoutBinding
import tech.ozil.monktagram.model.Photo
import tech.ozil.monktagram.model.PhotoItem
import tech.ozil.monktagram.utils.DataDiffUtil

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var photos = emptyList<PhotoItem>()

    class PhotoViewHolder(private val binding: PhotoRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photoItem: PhotoItem){
            binding.photoItem = photoItem
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): PhotoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PhotoRowLayoutBinding.inflate(layoutInflater, parent, false)
                return PhotoViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentAlbum = photos[position]

        holder.bind(currentAlbum)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

        fun setData(newData: Photo){
        val albumsDiffUtil =
            DataDiffUtil(photos, newData)
        val diffUtilResult = DiffUtil.calculateDiff(albumsDiffUtil)
        photos = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}