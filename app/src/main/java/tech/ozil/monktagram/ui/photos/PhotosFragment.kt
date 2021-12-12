package tech.ozil.monktagram.ui.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tech.ozil.monktagram.R

@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private val args by navArgs<PhotosFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("selaminko", "onCreateView: " + args.albumItem)
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }
}