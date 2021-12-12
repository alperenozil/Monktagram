package tech.ozil.monktagram.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.view.*
import tech.ozil.monktagram.R
import tech.ozil.monktagram.ui.photos.PhotosFragmentArgs

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val args by navArgs<DetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_details, container, false)
        view.detailsImageView.load(args.photoItem.url)
        view.albumNumber.setText(args.photoItem.albumId.toString())
        view.photoNumber.setText(args.photoItem.id.toString())
        view.title.setText(args.photoItem.title)
        return view
    }

}