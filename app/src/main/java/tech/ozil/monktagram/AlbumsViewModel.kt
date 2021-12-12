package tech.ozil.monktagram

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tech.ozil.monktagram.utils.Constants.Companion.USER_ID

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(userId: Int): HashMap<String, Int> {
        val queries: HashMap<String, Int> = HashMap()

        queries[USER_ID] = userId

        return queries
    }

}