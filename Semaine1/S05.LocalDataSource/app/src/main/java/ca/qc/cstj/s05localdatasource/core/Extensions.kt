package ca.qc.cstj.s05localdatasource.core

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

fun ImageView.loadFromResource(imageName : String) {
    val imageId = this.resources.getIdentifier(imageName,"drawable",this.context.packageName)

    this.setImageResource(imageId)
}

fun <VH: RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.notifyAllItemChanged(){
    this.notifyItemRangeChanged(0,this.itemCount)
}