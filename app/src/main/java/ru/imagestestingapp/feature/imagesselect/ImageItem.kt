package ru.imagestestingapp.feature.imagesselect

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import ru.imagestesting.domain.model.enums.ImagesType
import ru.imagestestingapp.R

class ImageItem(val entry: String) :
    AbstractItem<ImageItem.ViewHolder>() {
    override val type: Int = R.id.itemImage
    var imageType: ImagesType = ImagesType.OBJECTS

    override val layoutRes: Int = R.layout.item_list_images


    class ViewHolder(itemView: View) : FastAdapter.ViewHolder<ImageItem>(itemView) {
        private val imageText: TextView = itemView.findViewById(R.id.imageText)
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val container: ConstraintLayout = itemView.findViewById(R.id.container)


        override fun bindView(item: ImageItem, payloads: List<Any>) {
            imageText.text = item.entry
            val imageLink = if(item.imageType == ImagesType.ACTIONS) ACTIONS_ASSET + item.entry else OBJECTS_ASSET + item.entry
            image.apply {
                post {
                    load(imageLink)
                }
            }
            if(item.isSelected) {
                container.setBackgroundColor(itemView.context.resources.getColor(R.color.appPrimary, null))
            }
            else {
                container.setBackgroundColor(itemView.context.resources.getColor(R.color.background, null))
            }
        }

        override fun unbindView(item: ImageItem) {
            imageText.text = null
            container.setBackgroundColor(itemView.context.resources.getColor(R.color.background, null))
        }
    }

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    companion object {
        private const val ACTIONS_ASSET = "file:///android_asset/actions/"
        private const val OBJECTS_ASSET = "file:///android_asset/objects/"
    }
}