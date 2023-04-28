package uz.itschool.plants

import uz.itschool.plants.databinding.FragmentHomeBinding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=FragmentHomeBinding.inflate(inflater,container,false)

        loadfragment(BlankFragment())
        binding.navBottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadfragment(BlankFragment())
                    true
                }
                R.id.cart -> {
                    loadfragment(MyCartFragment())
                    true
                }
                R.id.order -> {
                    loadfragment(OrderFragment())
                    true
                }
                R.id.profile -> {
                    loadfragment(EdirProfileFragment())
                    true
                }
                else -> {
                    false
                }
            }

        }

        return binding.root
    }
    private  fun loadfragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.main,fragment)
            .commit()
    }

}