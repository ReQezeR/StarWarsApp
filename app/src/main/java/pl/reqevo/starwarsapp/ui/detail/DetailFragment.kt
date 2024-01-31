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


        this.setupDetail(view, R.id.person_detail_gender, "Gender", person.gender)
        this.setupDetail(view, R.id.person_detail_birth_year, "Birth Year", person.birthYear)
        this.setupDetail(view, R.id.person_detail_height, "Height", person.height)
        this.setupDetail(view, R.id.person_detail_mass, "Mass", person.mass)
        this.setupDetail(view, R.id.person_detail_hair_color, "Hair Color", person.hairColor)
        this.setupDetail(view, R.id.person_detail_skin_color, "Skin Color", person.skinColor)
    }

    private fun setupDetail(view: View, viewElementId: Int, label: String, value: String): View {
        val viewElement = view.findViewById<View>(viewElementId)
        viewElement.findViewById<TextView>(R.id.custom_detail_label).text = label
        viewElement.findViewById<TextView>(R.id.custom_detail_value).text = value
        return viewElement
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}