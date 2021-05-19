package fr.anrouxel.greenhouse.ui.onBoarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import fr.anrouxel.greenhouse.R

class GreenhouseScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_greenhouse_screen, container, false)

        val qrFeature_button: Button = view.findViewById(R.id.greenhouse_screen_button)
        val next_button: TextView = view.findViewById(R.id.greenhouse_screen_next)

        qrFeature_button.isEnabled = !requestQrFeature()

        qrFeature_button.setOnClickListener {
            val action = GreenhouseScreenFragmentDirections.actionGlobalMozillaQrFeatureFragment("greenhouseScreenFragment")
            Navigation.findNavController(view).navigate(action)
        }

        next_button.setOnClickListener {
            if (requestQrFeature()) {
                val action = GreenhouseScreenFragmentDirections.actionGreenhouseScreenFragmentToFinishScreenFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }

        return view
    }

    private fun requestQrFeature(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val networkIP = sharedPref.getString("networkIP", null)
        return networkIP != null
    }
}