package uz.itschool.plants

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.itschool.plants.databinding.FragmentOrderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentOrderBinding.inflate(inflater,container,false)
        val share=MyShared.getInstance(requireContext())

        val list = share.getOrder()
        loadfragment(OrderActiveFragment())

        binding.completed.setOnClickListener {
            binding.completed.setTextColor(Color.GREEN)
            binding.active.setTextColor(Color.BLACK)
            loadfragment(CompletedFragment())

        }
        binding.active.setOnClickListener {
            binding.active.setTextColor(Color.GREEN)
            binding.completed.setTextColor(Color.BLACK)
            loadfragment(OrderActiveFragment())

        }
//        binding.ac.setHasFixedSize(true)
//
//        var adapter = AdapterCart(requireContext(),
//            list, object : AdapterCart.CartClick {
//                override fun itemclick(plant: Plant, img: ImageView) {
//                    val bundle = bundleOf("plant" to plant)
////                val extras = FragmentNavigatorExtras(img to "img_big")
//                    findNavController().navigate(
//                        R.id.action_homeFragment_to_detailFragment,
//                        bundle
//                    )
//                }
//            })
//
//        binding.cartRv.adapter = adapter


        return binding.root
    }

    private  fun loadfragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.orderlay,fragment)
            .commit()
    }
}