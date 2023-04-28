package uz.itschool.plants

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.plants.databinding.FragmentEdirProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EdirProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EdirProfileFragment : Fragment() {
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
        var sharedpreferences = this.activity?.getSharedPreferences("reg", Context.MODE_PRIVATE)
        var edit = sharedpreferences?.edit()
        var gson = Gson()
        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>() {}.type

        val binding = FragmentEdirProfileBinding.inflate(inflater, container, false)
//        var userr = arguments?.getSerializable("user") as User
//        binding.emailEdp.text = userr.email
//        binding.passwordEdp.text= userr.password

        var shared = requireContext().getSharedPreferences("reg", Context.MODE_PRIVATE)
        var userJson = shared.getString("active_user", "")
        var person = gson.fromJson(userJson, User::class.java)


        binding.emailEdp.text = person.email
        binding.passwordEdp.text = person.password
//        binding.emailEdp.text=person.email
//        binding.passwordEdp.text=person.password
        binding.logoutt.setOnClickListener {
            var usersData = sharedpreferences?.getString("users", "")
            var pos = false

            if (usersData == "") {
                userList = gson.fromJson(usersData, type)
                for (i in userList) {
                    if (i.email == binding.emailEdp.text.toString() && i.password == binding.passwordEdp.text.toString()) {
                        pos = true

                        userList.remove(i)
                        val str = gson.toJson(userList)
                        edit?.putString("users", str)?.apply()

                        findNavController().navigate(R.id.action_edirProfileFragment_to_createAccountFragment)
                    }
                }

            }

        }




        binding.editprofileNext.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_editProfButFragment)
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
         * @return A new instance of fragment EdirProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EdirProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}