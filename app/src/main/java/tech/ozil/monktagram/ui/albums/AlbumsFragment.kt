package tech.ozil.monktagram.ui.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_albums.view.*
import kotlinx.android.synthetic.main.fragment_photos.view.*
import tech.ozil.monktagram.ui.main.MainViewModel
import tech.ozil.monktagram.R
import tech.ozil.monktagram.adapter.AlbumAdapter
import tech.ozil.monktagram.utils.NetworkResult

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var mView: View
    private val albumAdapter by lazy { AlbumAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsViewModel = ViewModelProvider(requireActivity()).get(AlbumsViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_albums, container, false)
        setupRecyclerView()
        requestAlbumData()
        return mView
    }

    private fun requestAlbumData() {
        mainViewModel.getAlbums(albumsViewModel.applyQueries(1))
        mainViewModel.albumsResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmer()
                    response.data?.let { albumAdapter.setData(it) }
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
        mView.shimmerRecyclerView.showShimmer()
    }

    private fun hideShimmer() {
        mView.shimmerRecyclerView.hideShimmer()
    }

    private fun setupRecyclerView() {
        mView.shimmerRecyclerView.adapter = albumAdapter
        mView.shimmerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }

}