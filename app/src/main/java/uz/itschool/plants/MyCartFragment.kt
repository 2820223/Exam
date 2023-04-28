package uz.itschool.plants

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.itschool.plants.databinding.FragmentMyCartBinding
import kotlin.math.log


class MyCartFragment : Fragment() {
    lateinit var sharedpreferenc:SHaredPreference
    lateinit var list:MutableList<uz.itschool.plants.Object>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedpreferenc = SHaredPreference.getInstance(requireContext())
        list = sharedpreferenc.getPlant()
    }


    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyCartBinding.inflate(inflater, container, false)

        Log.d("TAG", "onCreateView: ${sharedpreferenc.getPlant()}")
        if (sharedpreferenc.getPlant().toString()!=="") {
            binding.cartRv.setHasFixedSize(true)

            var adapter = AdapterCart(requireContext(),
                list, object : AdapterCart.CartClick {
                    override fun itemclick(plant: uz.itschool.plants.Object, img: ImageView) {
                        val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                        findNavController().navigate(
                            R.id.action_homeFragment_to_detailFragment,
                            bundle
                        )
                    }
                })

            binding.cartRv.adapter = adapter
        }
        else{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main,EmptyFragment())
                .commit()
        }
        var t=0
        for (i in list){
            t=(i.price*i.qt)+t
        }
        binding.priceCartTotal.text=t.toString()
        binding.checkout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_checkOutFragment)

        }

        return binding.root

    }
}

