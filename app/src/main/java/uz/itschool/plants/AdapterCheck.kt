package uz.itschool.plants
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import uz.itschool.plants.databinding.OrderListBinding

class AdapterCheck  (val context: Context, private val list: MutableList<uz.itschool.plants.Object>, var cartClick: AdapterCheck.CartClick) :
    RecyclerView.Adapter<AdapterCheck.MyHolder>() {
    class MyHolder(itemview: OrderListBinding) : RecyclerView.ViewHolder(itemview.root) {
        var name_plant = itemview.orderPlantName
        var img_plant = itemview.imgOrder
        var price_plant = itemview.pricePlantOrder
        var layout = itemview.layoutCheck


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val holder = MyHolder(
            OrderListBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false))
        return holder
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = list[position]
        holder.name_plant.text = item.name
        holder.price_plant.text = item.price.toString()
        holder.img_plant.setImageResource(item.img)


        holder.itemView.setOnClickListener {
            cartClick.itemclick(item,holder.img_plant)
        }
    }
    interface CartClick {
        fun itemclick(plant: uz.itschool.plants.Object,img: ImageView)
    }


}