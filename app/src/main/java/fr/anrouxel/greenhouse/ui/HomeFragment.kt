package fr.anrouxel.greenhouse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.anrouxel.greenhouse.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).supportActionBar?.show()

        val bottom_nav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav)
        bottom_nav.visibility = View.VISIBLE

        return view
    }
}