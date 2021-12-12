package tech.ozil.monktagram.ui.photos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tech.ozil.monktagram.utils.Constants

class PhotosViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(albumId: Int): HashMap<String, Int> {
        val queries: HashMap<String, Int> = HashMap()

        queries[Constants.ALBUM_ID] = albumId

        return queries
    }

}