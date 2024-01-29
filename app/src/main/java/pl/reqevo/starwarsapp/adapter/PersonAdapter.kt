package pl.reqevo.starwarsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import pl.reqevo.starwarsapp.R
import pl.reqevo.starwarsapp.models.Person

class PersonAdapter(private val personList: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return personList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${personList.size} ")

        return holder.bind(personList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var personCard = itemView.findViewById<MaterialCardView>(R.id.person_card)
        var personName = itemView.findViewById<TextView>(R.id.person_name)

        fun bind(person: Person) {
            personName.text = person.name

            personCard.setOnClickListener{
                Log.d("CLICK", "Person Name :${personName.text}")
                findNavController(itemView).navigate(R.id.action_HomeFragment_to_DetailFragment)
            }
        }
    }
}