package uz.itschool.plants

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.plants.databinding.FragmentPayMethodBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [payMethodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class payMethodFragment : Fragment() {
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
        val binding = FragmentPayMethodBinding.inflate(inflater, container, false)
        val sharedpreferenc = MySharedPreference.getInstance(requireContext())
        val shared = MyShared.getInstance(requireContext())
//        val sharedPrefs =
//            context?.getSharedPreferences(sharedpreferenc.toString(), Context.MODE_PRIVATE) //
//        val editor = sharedPrefs?.edit()


        binding.confirmpay.setOnClickListener {
            if (binding.radio1.isChecked || binding.radio2.isChecked || binding.radio3.isChecked || binding.radio4.isChecked || binding.radio5.isChecked) {
                val binding1 = layoutInflater.inflate(R.layout.dialog_pay, container, false)
                val myDialog = Dialog(requireContext())
                myDialog.setContentView(binding1)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                var list = sharedpreferenc.getPlant()

                sharedpreferenc.clearLIst()
                myDialog.show()


                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    myDialog.cancel()
                    sharedpreferenc.addOrder(list)
//                    var  list=shared.getOrder()
//                   list=sharedpreferenc.getPlant()
//                    list.clear()
//                    sharedpreferenc.getPlant()=list
                    findNavController().navigate(R.id.action_payMethodFragment_to_homeFragment)

                }, 2000)
            }
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment payMethodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            payMethodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}