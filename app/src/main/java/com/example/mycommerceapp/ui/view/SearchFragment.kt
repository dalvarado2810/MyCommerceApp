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
import com.example.mycommerceapp.R
import com.example.mycommerceapp.data.model.SearchModel
import com.example.mycommerceapp.databinding.MainFragmentBinding
import com.example.mycommerceapp.ui.view.utils.action
import com.example.mycommerceapp.ui.view.utils.snack
import com.example.mycommerceapp.viewmodel.AppResource
import com.example.mycommerceapp.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

const val SEARCH_KEY = "Search Key"

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding : MainFragmentBinding
    private lateinit var fragmentContext : Context

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        fragmentContext = requireContext().applicationContext
        binding.searchButton.setOnClickListener {
            val productName = binding.search.text.toString()
            if (productName!!.isNotEmpty()) {
                prepareSearch(productName)
                fragmentContext.hideKeyboard(view)
            } else {
                Toast.makeText(fragmentContext, R.string.blank_search,
                Toast.LENGTH_LONG).show()
            }
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
        findNavController().navigate(R.id.action_searchFragment_to_resultFragment,
                Bundle().apply {
                    putSerializable(SEARCH_KEY, item as Serializable)
                })
    }

    private fun showLoading() {
        binding.homeProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.homeProgressBar.visibility = View.GONE
    }

    private fun showMessage(message: String?) {
        message?.also {
            binding.main.snack(it, Snackbar.LENGTH_INDEFINITE) {
                action(getString(R.string.Ok)) {
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.search.text?.clear()
    }
}