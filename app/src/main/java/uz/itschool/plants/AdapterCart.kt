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
import uz.itschool.plants.databinding.MycartRecycleBinding


class AdapterCart(val context: Context, private val list: MutableList<uz.itschool.plants.Object>, var cartClick: AdapterCart.CartClick) :
    RecyclerView.Adapter<AdapterCart.MyHolder>() {
    class MyHolder(itemview: MycartRecycleBinding) : RecyclerView.ViewHolder(itemview.root) {
        var name_plant = itemview.cartPlantName
        var img_plant = itemview.imgCart
        var price_plant = itemview.pricePlantCart
        var layout = itemview.layoutCart
        var remove=itemview.removeCart
        var quant=itemview.resultButCart

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val holder = MyHolder(MycartRecycleBinding.inflate(LayoutInflater.from(parent.context), parent,
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
        holder.quant.text=item.qt.toString()
        holder.remove.setOnClickListener {
            val sharedpreferenc = SHaredPreference.getInstance(context)

            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.bottomsheet_remove)
            dialog.show()


            val yes =dialog.findViewById<MaterialButton>(R.id.yer_remove)
            val imgRemove =dialog.findViewById<ImageView>(R.id.img_cart_remove)
            val nameRemove =dialog.findViewById<TextView>(R.id.cart_plant_name_remove)
            val priceRemove =dialog.findViewById<TextView>(R.id.price_plant_cart_remove)
            imgRemove!!.setImageResource(item.img)
            nameRemove!!.text=item.name
            priceRemove!!.text=item.price.toString()

            val cancel =dialog.findViewById<MaterialButton>(R.id.cancel)

            yes?.setOnClickListener {


                list.remove(item)
                sharedpreferenc.removePlant(item)
                notifyDataSetChanged()
                Toast.makeText(context, "Enter empty blanks!", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            cancel?.setOnClickListener {

                dialog.cancel()
            }

        }

        holder.itemView.setOnClickListener {
            cartClick.itemclick(item,holder.img_plant)
        }
    }
    interface CartClick {
        fun itemclick(plant: uz.itschool.plants.Object,img: ImageView)
    }

}