package uz.itschool.plants

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.plants.databinding.FragmentFillingprofileBinding
import java.net.UnknownServiceException


class FillingprofileFragment : Fragment() {

    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var sharedpreferences = this.activity?.getSharedPreferences("reg", Context.MODE_PRIVATE)
        var edit = sharedpreferences?.edit()
        var gson = Gson()
        var userList = mutableListOf<User>()
        var type = object : TypeToken<List<User>>() {}.type
        val gender = arrayOf("Gender", "Male", "Female")
        val binding = FragmentFillingprofileBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener {
            var p = 0
            var usersData = sharedpreferences?.getString("users", "")
            var pos = false
            if (usersData == "") {
                Toast.makeText(this.activity, "Enter empty blanks!", Toast.LENGTH_SHORT).show()
            } else {
                userList = gson.fromJson(usersData, type)
                for (i in 0..userList.size) {
                    if (userList[i].email == binding.Email.text.toString()) {
                        pos = true
                        p = i
                    }
                }
                if (pos == true) {
                    Toast.makeText(this.activity, "TRUE", Toast.LENGTH_SHORT).show()
//                    userList[p].nickname=binding.nickname.text.toString()
//                    userList[p].fullname=binding.fullname.text.toString()
//                    userList[p].birth_date=binding.birthDate.text.toString()
//                    userList[p].gender=binding.spinner1
//                    userList[p].phone=binding.phone.text.toString()
                    val str = gson.toJson(userList)
                    edit?.putString("users", str)?.commit()
                    findNavController().navigate(R.id.action_blankFragment_to_homeFragment)
                }
            }
        }
//        userList.add(User(binding.email.text.toString(),binding.pa.text.toString()))
//        Toast.makeText(this.activity,"Succesfully registered", Toast.LENGTH_SHORT).show()
//        val string=gson.toJson(userList)
//        edit?.putString("users",string)?.commit()


        binding.next.setOnClickListener {
            val binding1 = layoutInflater.inflate(R.layout.dialog_congrats, container, false)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(binding1)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            myDialog.show()

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                myDialog.cancel()
                findNavController().navigate(R.id.action_blankFragment_to_homeFragment)
            }, 2000)
        }

        return binding.root
    }
}