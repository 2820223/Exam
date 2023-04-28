package uz.itschool.plants

import uz.itschool.plants.databinding.FragmentNotificationBinding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class NotificationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentNotificationBinding.inflate(inflater,container,false)

        binding.b.setOnClickListener {
            findNavController().navigate(R.id.action_notificationFragment_to_homeFragment)
        }
        return binding.root
    }


}