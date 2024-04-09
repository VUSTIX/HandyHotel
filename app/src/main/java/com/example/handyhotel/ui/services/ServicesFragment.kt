package com.example.handyhotel.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.handyhotel.AdapterService
import com.example.handyhotel.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterService: AdapterService
    private lateinit var dataListServices: List<String>
    private lateinit var dataListDescription: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        dataListServices = arguments?.getStringArray("dataListServices")?.toList() ?: emptyList()
        dataListDescription = arguments?.getStringArray("dataListDescription")?.toList() ?: emptyList()
        adapterService = AdapterService(requireContext(), dataListServices, dataListDescription)
        recyclerView.adapter = adapterService
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(dataListServices: List<String>, dataListDescription: List<String>): ServicesFragment {
            val fragment = ServicesFragment()
            val args = Bundle().apply {
                putStringArray("dataListServices", dataListServices.toTypedArray())
                putStringArray("dataListDescription", dataListDescription.toTypedArray())
            }
            fragment.arguments = args
            return fragment
        }
    }
}


