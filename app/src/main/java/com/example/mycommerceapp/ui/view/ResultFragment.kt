package com.example.mycommerceapp.ui.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycommerceapp.R
import com.example.mycommerceapp.data.model.Result
import com.example.mycommerceapp.data.model.SearchModel
import com.example.mycommerceapp.databinding.FragmentResultBinding
import com.example.mycommerceapp.ui.view.adapter.SearchItemsAdapter
import com.example.mycommerceapp.ui.view.utils.action
import com.example.mycommerceapp.ui.view.utils.snack
import com.example.mycommerceapp.viewmodel.AppResource
import com.example.mycommerceapp.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

const val DETAIL_KEY = "DETAIL_KEY"

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result), ManagerCallback {

    private lateinit var binding : FragmentResultBinding
    private var productName: Serializable? = null
    private val itemAdapter: SearchItemsAdapter = SearchItemsAdapter(this)

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var appContext : Context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        appContext = requireContext().applicationContext
        arguments.let {
            productName = it?.getSerializable(SEARCH_KEY)
        }
        initLayoutManager()
        initRecyclerView()
        initMenuBar()
         }

    private fun initRecyclerView() {
        binding.recyclerview.adapter = itemAdapter
        itemAdapter.submitList(productName as List<Result>)
    }

    private fun initMenuBar() {
        binding.menuSearch.setOnClickListener() {
            val newSearch = binding.searchBar.text.toString()
            if (newSearch.isNotEmpty()) {
                prepareSearch(newSearch)
                appContext.hideKeyboard(it)
            } else {
                Toast.makeText(appContext, R.string.blank_search, Toast.LENGTH_LONG).show()
            }
        }
        binding.searchClear.setOnClickListener() {
            binding.searchBar.text.clear()
        }
        binding.menuBack.setOnClickListener() {
            findNavController().navigate(R.id.action_resultFragment_to_searchFragment)
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun prepareSearch(productName : String) {
        searchViewModel.getAllSearchItems(productName).
        observe(viewLifecycleOwner, Observer(::handleResult))
    }

    private fun handleResult(result: AppResource<SearchModel?>) {
        hideLoading()
        when(result) {
            is AppResource.Error -> showMessage(result.message)
            is AppResource.Loading -> showLoading()
            is AppResource.Success -> showResults(result.data)
        }
    }

    private fun showResults(data: SearchModel?) {
        hideLoading()
        val item = data?.results
        itemAdapter.submitList(item)
        arguments = Bundle().apply {
            putSerializable(SEARCH_KEY,item as Serializable?)
        }
    }

    private fun showLoading() {
        binding.homeProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.homeProgressBar.visibility = View.GONE
    }

    private fun showMessage(message: String?) {
        message?.also {
            binding.container.snack(it, Snackbar.LENGTH_INDEFINITE) {
                action(R.string.Ok.toString()) {
                }
            }
        }
    }

    private fun initLayoutManager() {
        binding.recyclerview.layoutManager = GridLayoutManager(
            appContext,
            2,
            RecyclerView.VERTICAL,
            false).apply {
                binding.recyclerview.layoutManager = this
        }
    }

    override fun onItemClicked(feed: Result) {
        findNavController().navigate(R.id.action_resultFragment_to_detailsFragment,
            Bundle().apply {
                putSerializable(DETAIL_KEY, feed)
            })
    }
}

interface ManagerCallback {
    fun onItemClicked(feed: Result): Unit
}