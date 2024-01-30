package pl.reqevo.starwarsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.reqevo.starwarsapp.R
import pl.reqevo.starwarsapp.adapter.PersonListAdapter
import pl.reqevo.starwarsapp.databinding.FragmentHomeBinding
import pl.reqevo.starwarsapp.models.PersonResponse
import pl.reqevo.starwarsapp.network.PersonApi
import pl.reqevo.starwarsapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var recyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.home_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadPersons()//TODO cache data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadPersons() {
        val personApi = RetrofitClient.getInstance().create(PersonApi::class.java)
        val result = personApi.getPeople()

        result.enqueue(object : Callback<PersonResponse> {
            override fun onResponse(
                call: Call<PersonResponse>,
                response: Response<PersonResponse>
            ) {
                Log.d("Response", "onResponse: ${response.body()}")
                Log.d("PERSONS", response.body()!!.results.toString())
                if (response.isSuccessful) {
                    val personList = response.body()!!.results

                    Log.d("PERSONS", personList.toString())
                    recyclerView.adapter = PersonListAdapter(response.body()!!.results)
                } else {
                    println("persons error : ${response.message()}")
                    Log.d("PERSONS", "persons error : ${response.message()}")
                    //TODO
//                    Toast.makeText(
//                        this@HomeFragment,
//                        "Something went wrong ${response.message()}",
//                        Toast.LENGTH_LONG
//                    ).show()

                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                Log.d("PERSONS", "persons error : ${t.message.toString()}")
                //TODO
//                Toast.makeText(this@HomeFragment, "Something went wrong $t", Toast.LENGTH_SHORT)
//                    .show()
            }

        })
    }
}