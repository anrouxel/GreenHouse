package fr.anrouxel.greenhouse.ui.onBoarding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import fr.anrouxel.greenhouse.R

class PermissionScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_permission_screen, container, false)

        val permission_button: Button = view.findViewById(R.id.permission_screen_button)
        val next_button: TextView = view.findViewById(R.id.permission_screen_next)

        permission_button.isEnabled = !requestPermissionCamera()

        permission_button.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
        }

        next_button.setOnClickListener {
            if (requestPermissionCamera()) {
                val action = PermissionScreenFragmentDirections.actionPermissionScreenFragmentToGreenhouseScreenFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }

        return view
    }

    private fun requestPermissionCamera(): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
}