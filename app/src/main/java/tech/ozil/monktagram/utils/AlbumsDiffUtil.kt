package tech.ozil.monktagram.utils

import androidx.recyclerview.widget.DiffUtil
import tech.ozil.monktagram.models.AlbumItem

class AlbumsDiffUtil(private val oldList: List<AlbumItem>, private val newList: List<AlbumItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}