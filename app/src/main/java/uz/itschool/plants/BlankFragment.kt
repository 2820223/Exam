package uz.itschool.plants

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.gson.Gson
import uz.itschool.plants.databinding.FragmentBlankBinding

private lateinit var plantList:MutableList<uz.itschool.plants.Object>
private lateinit var plantList2:MutableList<uz.itschool.plants.Object>
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var check_fav:Boolean=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBlankBinding.inflate(inflater, container, false)




        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val userJson = shared.getString("active_user", null)



        var sharedd = requireContext().getSharedPreferences("reg", Context.MODE_PRIVATE)
        var userJsonn = sharedd.getString("active_user", "")
        var person = gson.fromJson(userJsonn, User::class.java)

        binding.nameAction.text=person.email




//        val user: User = gson.fromJson(userJson, User::class.java)

//        println(user.toString())
//
//        binding.nameAction.setText(user.fullname)
//        binding.passwordEdp.setText(user.password)
//        binding.emailEdp.setText(user.email)








        binding.wishlist.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_myWishListFragment)
        }
        binding.notification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }

        plantList = mutableListOf()
        plantList.add(
            Object(
                "Peanut Cactus",
                R.drawable.img_20,
                43,
                4.4,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
//
        plantList.add(
            Object(
                "Aloe",
                R.drawable.img_16,
                30,
                4.6,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList.add(
            Object(
                "Rubber Fig Plant",
                R.drawable.img_21,
                25,
                4.2,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList.add(
            Object(
                "Dracaena Plant",
                R.drawable.img_17,
                38,
                4.7,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList.add(
            Object(
                "Yucca Plant",
                R.drawable.img_5,
                29,
                4.5,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList.add(
            Object(
                "Aspidistra Plant",
                R.drawable.img_18,
                27,
                4.8,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList.add(
            Object(
                "Prayer Plant",
                R.drawable.img_19,
                33,
                4.4,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )

        binding.rv1.setHasFixedSize(true)
        var adapter = PlantAdapter(
            requireActivity(),
            plantList, object : PlantAdapter.PlantClick {
                override fun itemclick(plant: uz.itschool.plants.Object, img: ImageView) {
                    val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        bundle
                    )
                }
            })



        plantList2 = mutableListOf()
        plantList2.add(
            Object(
                "Peanut Cactus",
                R.drawable.img_25,
                43,
                4.4,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
//

        plantList2.add(
            Object(
                "Rubber Fig Plant",
                R.drawable.img_21,
                25,
                4.2,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList2.add(
            Object(
                "Dracaena Plant",
                R.drawable.img_17,
                38,
                4.7,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList2.add(
            Object(
                "Yucca Plant",
                R.drawable.img_5,
                29,
                4.5,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList2.add(
            Object(
                "Aspidistra Plant",
                R.drawable.img_18,
                27,
                4.8,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
        plantList2.add(
            Object(
                "Prayer Plant",
                R.drawable.img_19,
                33,
                4.4,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )

        binding.rv2.setHasFixedSize(true)
        var adapter2 = Plant2Adapter(
            requireActivity(),
            plantList2, object : Plant2Adapter.PlantClick2 {
                override fun itemclick2(plant: Object, img: ImageView) {
                    val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        bundle
                    )
                }
            })
        var lm = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        binding.rv2.adapter = adapter2

        val all= plantList

        binding.see.setOnClickListener {
            binding.see.setTextColor(Color.GREEN)
            binding.mostPopular.setTextColor(Color.BLACK)
            all.addAll(plantList2)
            binding.rv2.setHasFixedSize(true)
            var adapter2 = Plant2Adapter(
                requireActivity(),
                all, object : Plant2Adapter.PlantClick2 {
                    override fun itemclick2(plant: uz.itschool.plants.Object, img: ImageView) {
                        val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_detailFragment,
                            bundle
                        )
                    }
                })
            var lm = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
            binding.rv2.adapter = adapter2
        }
        binding.mostPopular.setOnClickListener {
            binding.mostPopular.setTextColor(Color.GREEN)
            binding.see.setTextColor(Color.BLACK)

            binding.rv2.setHasFixedSize(true)
            var adapter2 = Plant2Adapter(
                requireActivity(),
                plantList2, object : Plant2Adapter.PlantClick2 {
                    override fun itemclick2(plant: uz.itschool.plants.Object, img: ImageView) {
                        val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_detailFragment,
                            bundle
                        )
                    }
                })
            var lm = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
            binding.rv2.adapter = adapter2


        }

        binding.wishlist.setOnClickListener {
            if(!check_fav){
                binding.wishlist.setImageResource(R.drawable.baseline_favorite_2black)
                check_fav=true
                var filter= plantList.filter {
                    it.fav
                }
                binding.wishlist.tag=1
                adapter= PlantAdapter(
                    requireActivity(),
                    filter as MutableList<uz.itschool.plants.Object>,
                    object : PlantAdapter.PlantClick {
                        override fun itemclick(plant: uz.itschool.plants.Object, img: ImageView) {
                            val bundle = bundleOf("plant" to plant)
                            findNavController().navigate(
                                R.id.action_blankFragment_to_detailFragment,
                                bundle
                            )
                        }
                    })
                binding.rv1.adapter=adapter
            }
            else{
                binding.wishlist.setImageResource(R.drawable.fav_black)
                check_fav=false
                binding.wishlist.tag=0
                adapter= PlantAdapter(
                    requireActivity(),
                    plantList,
                    object : PlantAdapter.PlantClick {
                        override fun itemclick(plant:uz.itschool.plants.Object, img: ImageView) {
                            val bundle = bundleOf("plant" to plant)
                            findNavController().navigate(
                                R.id.action_blankFragment_to_detailFragment,
                                bundle
                            )
                        }
                    })
                binding.rv1.adapter=adapter
            }
        }



        binding.searchact.addTextChangedListener {
            var filter = mutableListOf<uz.itschool.plants.Object>()
            if (it != null) {

                for (c in plantList) {
                    if (c.name.toUpperCase().contains(it.toString().toUpperCase())) {
                        filter.add(c)
                    }
                }
                adapter= PlantAdapter(requireContext(), filter,object:PlantAdapter.PlantClick{
                    override fun itemclick(plant: uz.itschool.plants.Object, img: ImageView) {
                        val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_detailFragment,
                            bundle)
                    }
                } )
                binding.rv1.adapter = adapter
            }
        }



        val touch = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.itemmove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.itemposition(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelper = ItemTouchHelper(touch)
        itemTouchHelper.attachToRecyclerView(binding.rv1)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rv1)
        binding.rv1.adapter = adapter
        return binding.root
    }

}


