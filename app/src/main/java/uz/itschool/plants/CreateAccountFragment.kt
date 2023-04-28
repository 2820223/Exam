package uz.itschool.plants

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.plants.databinding.FragmentCreateAccountBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAccountFragment : Fragment() {
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


        var sharedpreferences= this.activity?.getSharedPreferences("reg", Context.MODE_PRIVATE)
        var edit = sharedpreferences?.edit()
        var gson = Gson()
        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>() {}.type
        val binding=FragmentCreateAccountBinding.inflate(inflater,container,false)
        binding.next.setOnClickListener {
            var usersData=sharedpreferences?.getString("users","")
            var pos=false

            if(usersData==""){
                userList= mutableListOf()
                userList.add(User(binding.email3.text.toString(),binding.pasword3.text.toString()))
                Toast.makeText(this.activity,"Succesfully registered", Toast.LENGTH_SHORT).show()
                val string=gson.toJson(userList)
                edit?.putString("users",string)?.commit()
            }else{
                userList = gson.fromJson(usersData,type)
                for( i in userList){
                    if(i.email!=binding.email3.text.toString() && i.password!=binding.pasword3.text.toString()){
                        pos=true
                    }
                    else{
                        pos=false
                        break
                    }

                }
                if(pos==true){
                    userList.add(User(binding.email3.text.toString(),binding.pasword3.text.toString()))
                    Toast.makeText(activity,"Succesfully registered", Toast.LENGTH_SHORT).show()
                    val str = gson.toJson(userList)
                    edit?.putString("users", str)?.commit()
                    findNavController().navigate(R.id.action_createAccountFragment_to_fillingprofileFragment)
                }
                else{
                    Toast.makeText(activity,"Change inputs!", Toast.LENGTH_SHORT).show()
                }
            }




        }
        binding.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_entranceFragment)
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
         * @return A new instance of fragment CreateAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}