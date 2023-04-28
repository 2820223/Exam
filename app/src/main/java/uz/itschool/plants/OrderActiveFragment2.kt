package uz.itschool.plants
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.itschool.plants.databinding.FragmentOrderActive2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderActiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderActiveFragment : Fragment() {
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
        val binding= FragmentOrderActive2Binding.inflate(inflater,container,false)


        val sharedpreferenc = MySharedPreference.getInstance(requireContext())

        val list = sharedpreferenc.getOrder()


        binding.activeRv.setHasFixedSize(true)

        var adapter = AdapterActive(requireContext(),
            list, object :AdapterActive.ActiveClickAc   {
                override fun itemclick(plant: uz.itschool.plants.Object, img: ImageView) {
                    val bundle = bundleOf("plant" to plant)
//                val extras = FragmentNavigatorExtras(img to "img_big")
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        bundle
                    )
                }
            })

        binding.activeRv.adapter = adapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderActiveFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderActiveFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}