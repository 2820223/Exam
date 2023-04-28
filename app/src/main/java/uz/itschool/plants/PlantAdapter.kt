package uz.itschool.plants

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.itschool.plants.databinding.PlantSelectItemBinding
import java.util.*


class PlantAdapter(
    val context: Context,
    private val list: MutableList<uz.itschool.plants.Object>,
    var plantClick: PlantClick
) :
    RecyclerView.Adapter<PlantAdapter.MyHolder>() {
    class MyHolder(itemview: PlantSelectItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        var name_plant = itemview.plantNamee
        var img_plant = itemview.imgg

        var price_plant = itemview.pricePlantt
        var fav_plant = itemview.favourite
        var layout = itemview.layout
        var rank = itemview.rank
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val holder =
            MyHolder(
                PlantSelectItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = list[position]
        holder.name_plant.text = item.name
        holder.rank.text = item.rank.toString()
        holder.price_plant.text = item.price.toString()
//        if (item.fav) {
//            holder.fav_plant.setImageResource(R.drawable.green_favorite_24)
//        } else {
//            holder.fav_plant.setImageResource(R.drawable.baseline_favorite_border_24)
//        }

//        holder.img_plant.load(item.img) {
//            placeholder(R.drawable.ic_launcher_background)
//            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
//        }
        holder.itemView.setOnClickListener {
            plantClick.itemclick(item, holder.img_plant)
        }
        holder.img_plant.setImageResource(item.img)
        val anim = AnimationUtils.loadAnimation(context, R.anim.anim)
        holder.layout.startAnimation(anim)
        holder.fav_plant.setOnClickListener {
            if (item.fav) {
                holder.fav_plant.setImageResource(R.drawable.baseline_favorite_border_24)
                item.fav = false
            } else {
                holder.fav_plant.setImageResource(R.drawable.green_favorite_24)
                item.fav = true
            }
        }


    }

    interface PlantClick {
        fun itemclick(plant: uz.itschool.plants.Object, img: ImageView)
    }


    fun itemmove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(list, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun itemposition(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}
