package pl.reqevo.starwarsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
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
    private lateinit var previousPageButton: Button
    private lateinit var nextPageButton: Button

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
        previousPageButton = view.findViewById(R.id.home_previous_page_button)
        nextPageButton = view.findViewById(R.id.home_next_page_button)
        recyclerView = view.findViewById(R.id.home_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadPersons(null)//TODO cache data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadPersons(pageNumber: Int?) {//TODO store pageNumber
        val personApi = RetrofitClient.getInstance().create(PersonApi::class.java)
        val result = personApi.getPeople(pageNumber)

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
                    recyclerView.adapter = PersonListAdapter(personList)


                    if (response.body()!!.previous != null) {
                        previousPageButton.isVisible = true
                        val prevoiusPage: Int =
                            (response.body()!!.previous as String).split("page=")[1].toInt()
                        previousPageButton.setOnClickListener {
                            loadPersons(prevoiusPage)
                            view?.findViewById<NestedScrollView>(R.id.home_nested_scroll)!!
                                .smoothScrollTo(0, recyclerView.top)

                        }
                    } else {
                        previousPageButton.isVisible = false
                    }

                    if (response.body()!!.next != null) {
                        nextPageButton.isVisible = true
                        val nextPage: Int =
                            (response.body()!!.next as String).split("page=")[1].toInt()
                        nextPageButton.setOnClickListener {
                            loadPersons(nextPage)
                            view?.findViewById<NestedScrollView>(R.id.home_nested_scroll)!!
                                .smoothScrollTo(0, recyclerView.top)

                        }
                    } else {
                        nextPageButton.isVisible = false
                    }

                } else {
                    println("persons error : ${response.message()}")
                    Log.d("PERSONS", "persons response error : ${response.message()}")

                }
            }

            override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
                Log.d("PERSONS", "persons error : ${t.message.toString()}")
            }

        })
    }
}