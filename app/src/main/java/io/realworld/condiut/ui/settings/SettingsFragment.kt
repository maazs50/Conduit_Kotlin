package io.realworld.condiut.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.realworld.condiut.AuthViewModel
import io.realworld.condiut.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val authViewModel: AuthViewModel by activityViewModels()
    private var _binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({lifecycle}){
            _binding?.apply {
                emailEditText.setText(it?.email?:"")
                usernameEditText.setText(it?.username?:"")
                imageEditText.setText(it?.image?:"")
                bioEditText.setText(it?.bio?:"")

            }
        }
        _binding?.apply {
            updateButton.setOnClickListener {
                updateSettings(
                    email = emailEditText.text.toString().takeIf { it.isNotBlank() },
                    username = usernameEditText.text.toString().takeIf { it.isNotBlank() },
                    password = passwordEditText.text.toString().takeIf { it.isNotBlank() },
                    image = imageEditText.text.toString(),
                    bio = bioEditText.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateSettings(
        email:String?,
        username:String?,
        password:String?,
        image:String?,
        bio:String?
    ) {
        authViewModel.updateUser(email,username,password, image, bio)
    }
}