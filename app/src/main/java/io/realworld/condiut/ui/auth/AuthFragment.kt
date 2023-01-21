package io.realworld.condiut.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import io.realworld.condiut.R
import io.realworld.condiut.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? =null
    private var navController: NavController? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater,container,false)
             return _binding?.root
         }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = _binding?.let{Navigation.findNavController(it.root)}
        _binding?.authTabLayout?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0-> {
                        navController?.navigate(R.id.gotoLoginFragment)
                    }
                    1-> {
                        navController?.navigate(R.id.gotoSignupFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}