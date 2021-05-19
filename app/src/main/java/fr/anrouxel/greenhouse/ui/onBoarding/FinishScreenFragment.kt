package fr.anrouxel.greenhouse.ui.onBoarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import fr.anrouxel.greenhouse.R

class FinishScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_finish_screen, container, false)

        val finish_button: TextView = view.findViewById(R.id.finish_screen_finish)

        finish_button.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)

            val editor = sharedPref.edit()
            editor.putBoolean("onBoarding", true)
            editor.apply()

            val action = GreenhouseScreenFragmentDirections.actionGlobalHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
}