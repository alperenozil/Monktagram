package tech.ozil.monktagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.ozil.monktagram.databinding.AlbumRowLayoutBinding
import tech.ozil.monktagram.models.Album
import tech.ozil.monktagram.models.AlbumItem
import tech.ozil.monktagram.utils.AlbumsDiffUtil

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var albums = emptyList<AlbumItem>()

    class AlbumViewHolder(private val binding: AlbumRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(albumItem: AlbumItem){
            binding.albumItem = albumItem
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): AlbumViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumRowLayoutBinding.inflate(layoutInflater, parent, false)
                return AlbumViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentAlbum = albums[position]
        holder.bind(currentAlbum)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun setData(newData: Album){
        val albumsDiffUtil =
            AlbumsDiffUtil(albums, newData)
        val diffUtilResult = DiffUtil.calculateDiff(albumsDiffUtil)
        albums = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}