package tech.ozil.monktagram.adapter

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import tech.ozil.monktagram.model.AlbumItem
import tech.ozil.monktagram.ui.albums.AlbumsFragmentDirections

class AlbumRowBinding {
    companion object {

        @BindingAdapter("setAlbumId")
        @JvmStatic
        fun setAldumId(textView: TextView, aldumId: Int){
            textView.text = aldumId.toString()
        }

        @BindingAdapter("onAlbumClickListener")
        @JvmStatic
        fun onAlbumClickListener(albumRowLayout: ConstraintLayout, albumItem: AlbumItem) {
            albumRowLayout.setOnClickListener {
                try {
                    val action = AlbumsFragmentDirections.actionAlbumsFragmentToPhotosFragment(albumItem)
                    albumRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

    }
}