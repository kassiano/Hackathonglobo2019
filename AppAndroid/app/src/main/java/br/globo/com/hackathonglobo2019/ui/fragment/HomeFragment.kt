package br.globo.com.hackathonglobo2019.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.globo.com.hackathonglobo2019.R
import br.globo.com.hackathonglobo2019.ui.NewsAdapter
import br.globo.com.hackathonglobo2019.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    val adapter = NewsAdapter()

    val viewmodel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvNews.adapter = adapter

        viewmodel.fetchNews()
        viewmodel.news.observe(this, Observer {
            it?.let{
                adapter.setItems(it)
            }
        })
    }

    companion object {

        const val TAG = "HomeFragment"

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
