package com.example.mycommerceapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.mycommerceapp.R
import com.example.mycommerceapp.data.model.Result
import com.example.mycommerceapp.databinding.FragmentDetailsBinding
import java.io.Serializable

private const val BRAND = "BRAND"
private const val MODEL = "MODEL"

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private var productDetails: Serializable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        arguments?.let {
            productDetails = it.getSerializable(DETAIL_KEY)
        }
        initView()
    }

    private fun initView() {

        binding.menuBack.setOnClickListener {
            findNavController().
            navigate(R.id.action_detailsFragment_to_resultFragment)}

        (productDetails as Result).apply {
            if (condition == R.string.new_condition.toString()) {
                binding.detailCondition.text = getString(R.string.condition_new)
            } else {
                binding.detailCondition.text = getString(R.string.condition_used)
            }
            binding.detailSales.text = getString(R.string.sold_items, sold_quantity.toString())
            binding.detailTittle.text = title

            binding.imageThumbnail.load(thumbnail){
                crossfade(true)
                placeholder(R.drawable.ic_loading)
            }
            binding.detailPrice.text = getString(R.string.price, price.toString())
            if (installments != null) {
                binding.installmentsDetail.text = getString(
                    R.string.installments,
                    installments.quantity.toString(),
                    installments.amount.toString())
            } else {
                binding.installmentsDetail.visibility = View.GONE
            }
            binding.stockTxt.text = available_quantity.toString()
            if (shipping?.free_shipping == true) {
                binding.shipmentDetail.visibility = View.VISIBLE
            }
            for (index in attributes!!.indices) {
                when(attributes[index].id){
                    BRAND -> binding.descriptionTxt.text = attributes[index].value_name
                    MODEL -> binding.colorTxt.text = attributes[index].value_name
                    else -> {
                        binding.attributesTxt.text = attributes[index].value_name
                    }
                }
            }
        }
    }
}
