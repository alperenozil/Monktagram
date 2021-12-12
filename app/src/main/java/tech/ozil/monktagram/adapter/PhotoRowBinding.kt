package tech.ozil.monktagram.adapter

import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import tech.ozil.monktagram.model.PhotoItem
import tech.ozil.monktagram.ui.photos.PhotosFragmentDirections

class PhotoRowBinding {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }

        @BindingAdapter("onPhotoClickListener")
        @JvmStatic
        fun onPhotoClickListener(photoRowLayout: ConstraintLayout, photoItem: PhotoItem) {
            photoRowLayout.setOnClickListener {
                try {
                    val action = PhotosFragmentDirections.actionPhotosFragmentToDetailsFragment(photoItem)
                    photoRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onPhotoClickListener", e.toString())
                }
            }
        }

    }
}