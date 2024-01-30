package pl.reqevo.starwarsapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import pl.reqevo.starwarsapp.R
import pl.reqevo.starwarsapp.databinding.FragmentDetailBinding
import pl.reqevo.starwarsapp.models.Person

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = requireArguments().get("person") as Person
        Log.d("DETAILS", person.toString())

        val name_tv = view.findViewById<TextView>(R.id.person_detail_name)
        name_tv.text = person.name
        
        val gender_tv = view.findViewById<View>(R.id.person_detail_gender)
        gender_tv.findViewById<TextView>(R.id.custom_detail_label).text = "Gender"
        gender_tv.findViewById<TextView>(R.id.custom_detail_value).text = person.gender

        val birth_year_tv = view.findViewById<View>(R.id.person_detail_birth_year)
        birth_year_tv.findViewById<TextView>(R.id.custom_detail_label).text = "Birth Year"
        birth_year_tv.findViewById<TextView>(R.id.custom_detail_value).text = person.birthYear

        val height_tv = view.findViewById<View>(R.id.person_detail_height)
        height_tv.findViewById<TextView>(R.id.custom_detail_label).text = "Height"
        height_tv.findViewById<TextView>(R.id.custom_detail_value).text = person.height

        val mass_tv = view.findViewById<View>(R.id.person_detail_mass)
        mass_tv.findViewById<TextView>(R.id.custom_detail_label).text = "Mass"
        mass_tv.findViewById<TextView>(R.id.custom_detail_value).text = person.mass

        val hair_color_tv = view.findViewById<View>(R.id.person_detail_hair_color)
        hair_color_tv.findViewById<TextView>(R.id.custom_detail_label).text = "Hair"
        hair_color_tv.findViewById<TextView>(R.id.custom_detail_value).text = person.hairColor

        val skin_color_tv = view.findViewById<View>(R.id.person_detail_skin_color)
        skin_color_tv.findViewById<TextView>(R.id.custom_detail_label).text = "Skin"
        skin_color_tv.findViewById<TextView>(R.id.custom_detail_value).text = person.skinColor


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}