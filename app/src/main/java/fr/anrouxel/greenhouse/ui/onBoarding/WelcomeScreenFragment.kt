package fr.anrouxel.greenhouse.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.anrouxel.greenhouse.R

class WelcomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome_screen, container, false)

        (activity as AppCompatActivity).supportActionBar?.hide()
        val bottom_nav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav)
        bottom_nav.visibility = View.GONE

        val next_button: TextView = view.findViewById(R.id.welcome_screen_next)
        next_button.setOnClickListener {
            val action = WelcomeScreenFragmentDirections.actionWelcomeScreenFragmentToPermissionScreenFragment()
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
}