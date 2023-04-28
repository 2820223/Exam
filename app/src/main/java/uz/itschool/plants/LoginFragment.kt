package uz.itschool.plants

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.plants.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
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

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val share = SHaredPreference.getInstance(requireContext())

//        share.setStatus(true)
        var sharedpreferences = this.activity?.getSharedPreferences("reg", Context.MODE_PRIVATE)
        var edit = sharedpreferences?.edit()
        var gson = Gson()
        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>() {}.type
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.next.setOnClickListener {
            var usersData = sharedpreferences?.getString("users", "")
            var pos = false

            if (usersData == "") {
                Toast.makeText(this.activity, "Enter empty blanks!", Toast.LENGTH_SHORT).show()
            } else {
                userList = gson.fromJson(usersData, type)
                for (i in userList) {
                    if (i.email == binding.email3.text.toString() && i.password == binding.pasword3.text.toString()) {
                        pos = true

//                        var emaill=binding.emailLoginn.text.toString()
//                        var ph=binding.passwordLogin.text.toString()
                        val obj: User = i as User
                        break
                    } else {
                        pos = false
                    }
                }
                if (pos) {
                    var emaill = binding.email3.text.toString()
                    var ph = binding.pasword3.text.toString()

                    var user = User(emaill, ph)
                    var userJson = Gson().toJson(user)

                    var shared = requireContext().getSharedPreferences("reg", Context.MODE_PRIVATE)
                    shared.edit().putString("active_user", userJson).apply()

                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment,)
                } else {
                    Toast.makeText(this.activity, "You did not registered yet!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.signUp.setOnClickListener {

//                val extras = FragmentNavigatorExtras(img to "img_big")
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}