package fr.anrouxel.greenhouse.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import fr.anrouxel.greenhouse.R
import mozilla.components.feature.qr.QrFeature

class MozillaQrFeatureFragment : Fragment() {

    val args: MozillaQrFeatureFragmentArgs by navArgs()

    companion object {
        private const val REQUEST_CODE_CAMERA_PERMISSIONS = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mozilla_qr_feature, container, false)

        Log.d("Preferences", requireContext().toString())

        val qrFeature = QrFeature(
            requireContext(),
            fragmentManager = parentFragmentManager,
            onNeedToRequestPermissions = { permissions ->
                requestPermissions(permissions, REQUEST_CODE_CAMERA_PERMISSIONS)
            },
            onScanResult = {result ->
                val sharedPref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)

                val editor = sharedPref.edit()
                editor.putString("networkIP", result)
                editor.apply()

                when (args.src) {
                    "homeFragment" -> navigation_nav_graph(view, MozillaQrFeatureFragmentDirections.actionGlobalHomeFragment())
                    "greenhouseScreenFragment" -> navigation_nav_graph(view, MozillaQrFeatureFragmentDirections.actionGlobalGreenhouseScreenFragment())
                    "settingsFragment" -> navigation_nav_graph(view, MozillaQrFeatureFragmentDirections.actionGlobalSettingsFragment())
                }
            }
        )

        qrFeature.scan(R.id.mozilla_qrFeature)

        return view
    }

    private fun navigation_nav_graph(view: View, action: NavDirections) {
        Navigation.findNavController(view).navigate(action)
    }
}