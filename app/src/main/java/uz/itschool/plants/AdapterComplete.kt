package uz.itschool.plants
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import uz.itschool.plants.databinding.OrderCompeteItemBinding

class AdapterComplete  (val context: Context, private val list: MutableList<uz.itschool.plants.Object>, var activeClick:AdapterComplete.ActiveClickAc) :
    RecyclerView.Adapter<AdapterComplete.MyHolder>() {
    class MyHolder(itemview: OrderCompeteItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        var name_plant = itemview.orderPlantName
        var img_plant = itemview.imgOrder
        var quantity = itemview.quantity
        var price_plant = itemview.pricePlantOrder
        var layout = itemview.activelay
        var track = itemview.track

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val holder = MyHolder(
            OrderCompeteItemBinding.inflate(
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


        holder.track.setOnClickListener {

            val dialog = BottomSheetDialog(context)
            dialog.setContentView(R.layout.review)
            dialog.show()
            val yes =dialog.findViewById<MaterialButton>(R.id.submit)
            val imgRemove =dialog.findViewById<ImageView>(R.id.img_cart_r)
            val nameRemove =dialog.findViewById<TextView>(R.id.cart_plant_name_r)
            val priceRemove =dialog.findViewById<TextView>(R.id.price_plant_cart_r)
            imgRemove!!.setImageResource(item.img)
            nameRemove!!.text=item.name
            priceRemove!!.text=item.price.toString()
            val cancel =dialog.findViewById<MaterialButton>(R.id.cancel_review)

            val st1 =dialog.findViewById<ImageView>(R.id.star1)
            val st2 =dialog.findViewById<ImageView>(R.id.star2)
            val st3 =dialog.findViewById<ImageView>(R.id.star3)
            val st4 =dialog.findViewById<ImageView>(R.id.star4)
            val st5 =dialog.findViewById<ImageView>(R.id.star5)
            var count=0
            st1?.setOnClickListener {
                st1.setImageResource(R.drawable.yellow_star_24)
                count++
            }
            st2?.setOnClickListener {
                st2.setImageResource(R.drawable.yellow_star_24)
                count++
            }
            st3?.setOnClickListener {
                st3.setImageResource(R.drawable.yellow_star_24)
                count++
            }
            st4?.setOnClickListener {
                st4.setImageResource(R.drawable.yellow_star_24)
                count++
            }
            st5?.setOnClickListener {
                st5.setImageResource(R.drawable.yellow_star_24)
                count++
            }
            yes?.setOnClickListener {
//

                Toast.makeText(context, "Thank you for your Review", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            cancel?.setOnClickListener {

                dialog.cancel()
            }

        }






        holder.itemView.setOnClickListener {
            activeClick.itemclick(item, holder.img_plant)
        }
    }

    interface ActiveClickAc {
        fun itemclick(plant: uz.itschool.plants.Object, img: ImageView)
    }



}