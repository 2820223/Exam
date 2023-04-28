package uz.itschool.plants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.itschool.plants.databinding.FragmentDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
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
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        val plant = arguments?.getSerializable("plant") as uz.itschool.plants.Object

        val sharedPreference=MySharedPreference.getInstance(requireContext())
        binding.img.setImageResource(plant.img)
        var position=false
        binding.nameDetail.text = plant.name
        binding.descriptionText.text = plant.description
        binding.rank.text = plant.rank.toString()
        var count=0
        binding.plusBut.setOnClickListener {
            count++
            binding.resultBut.text=count.toString()
            plant.qt=count
            binding.totalPriceCart.text= (count*plant.price).toString()
        }

        binding.minusBut.setOnClickListener {
            if (count>0){
                count--
                binding.resultBut.text=count.toString()
                plant.qt=count
                binding.totalPriceCart.text= (count*plant.price).toString()
            }}

        binding.favdetail.setOnClickListener {
            if (!position) {
                binding.favdetail.setImageResource(R.drawable.green_favorite_24)
                position = true
            } else {
                binding.favdetail.setImageResource(R.drawable.baseline_favorite_border_24)
                position = false
            }

        }
        binding.backdetail.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
        binding.addToCart.setOnClickListener {
            if (count>0){
                sharedPreference.addPlant(plant)
                Toast.makeText(this.activity, "Added to cart", Toast.LENGTH_SHORT).show()
                binding.resultBut.text=0.toString()
                binding.totalPriceCart.text= 0.toString()
            }
            else{
                Toast.makeText(this.activity, "Error", Toast.LENGTH_SHORT).show()
            }}
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}