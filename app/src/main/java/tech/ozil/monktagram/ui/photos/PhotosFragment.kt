package tech.ozil.monktagram.ui.photos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_albums.view.*
import kotlinx.android.synthetic.main.fragment_photos.view.*
import tech.ozil.monktagram.R
import tech.ozil.monktagram.adapter.AlbumAdapter
import tech.ozil.monktagram.adapter.PhotoAdapter
import tech.ozil.monktagram.ui.albums.AlbumsViewModel
import tech.ozil.monktagram.ui.main.MainViewModel
import tech.ozil.monktagram.utils.NetworkResult

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val args by navArgs<PhotosFragmentArgs>()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var mView: View
    private val photoAdapter by lazy { PhotoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photosViewModel = ViewModelProvider(requireActivity()).get(PhotosViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_photos, container, false)
        setupRecyclerView()
        requestPhotoData(args.albumItem.id)
        return mView
    }

    private fun requestPhotoData(albumId: Int) {
        mainViewModel.getPhotos(photosViewModel.applyQueries(albumId))
        mainViewModel.photosResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmer()
                    response.data?.let { photoAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmer()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmer()
                }
            }
        })
    }

    private fun showShimmer() {
        mView.shimmerRecyclerViewPhoto.showShimmer()
    }

    private fun hideShimmer() {
        mView.shimmerRecyclerViewPhoto.hideShimmer()
    }

    private fun setupRecyclerView() {
        mView.shimmerRecyclerViewPhoto.adapter = photoAdapter
        mView.shimmerRecyclerViewPhoto.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }
}