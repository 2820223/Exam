package uz.itschool.plants
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import uz.itschool.plants.databinding.OrderActiveItemBinding

class AdapterActive  (val context: Context, private val list: MutableList<uz.itschool.plants.Object>, var activeClick:AdapterActive.ActiveClickAc) :
    RecyclerView.Adapter<AdapterActive.MyHolder>() {
    class MyHolder(itemview: OrderActiveItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        var name_plant = itemview.orderPlantName
        var img_plant = itemview.imgOrder
        var quantity = itemview.quantity
        var price_plant = itemview.pricePlantOrder
        var layout = itemview.activelay
        var track = itemview.track

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val holder = MyHolder(
            OrderActiveItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
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
        val bundle = bundleOf("track" to item)


        holder.name_plant.text = item.name
//        holder.quantity.text = item.q
        holder.price_plant.text = item.price.toString()
        holder.img_plant.setImageResource(item.img)
        with(holder.itemView) {
            holder.track.setOnClickListener {
                    view ->
//                    fun doWhatever(name: String, pr: String,img:Int) {
//                        // call the handler function with your data (you can write handler.invoke() if you prefer)
//                        handler(Plant(item.name, item.price,item.img,item.rank,item.description,item.fav))
//                    }

                view.findNavController().navigate(R.id.action_homeFragment_to_trackFragment)

            }

//
//        }




            holder.itemView.setOnClickListener {
                activeClick.itemclick(item, holder.img_plant)
            }
        }}

    interface ActiveClickAc {
        fun itemclick(plant: uz.itschool.plants.Object, img: ImageView)
    }
    interface Callbacks {
        fun handleUserData(plant: uz.itschool.plants.Object)
    }


}