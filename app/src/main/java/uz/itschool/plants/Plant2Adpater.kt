package uz.itschool.plants


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.itschool.plants.databinding.PlantGridBinding
import java.util.*

class Plant2Adapter(val context: Context, private val list: MutableList<uz.itschool.plants.Object>, var plantClick2: PlantClick2) :
    RecyclerView.Adapter<Plant2Adapter.MyHolder>() {
    class MyHolder(itemview: PlantGridBinding) : RecyclerView.ViewHolder(itemview.root) {
        var name_plant = itemview.plantNameee
        var img_plant = itemview.imggg

        var price_plant = itemview.pricePlaanttt
        var fav_plant = itemview.favouritee
        var layout = itemview.layyoutt
        var rank = itemview.rankk
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val holder =
            MyHolder(
                PlantGridBinding.inflate(
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


        holder.itemView.setOnClickListener {
            plantClick2.itemclick2(item,holder.img_plant)
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

    interface PlantClick2 {
        fun itemclick2(plant: uz.itschool.plants.Object,img: ImageView)
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