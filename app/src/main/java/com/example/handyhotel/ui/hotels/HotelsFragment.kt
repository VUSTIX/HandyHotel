package com.example.handyhotel.ui.hotels

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.handyhotel.*
import com.example.handyhotel.databinding.FragmentHotelsBinding

class HotelsFragment : Fragment(), SortingDialogFragment.SortingDialogListener {

    private var _binding: FragmentHotelsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterHotel: AdapterHotel
    private lateinit var dataListHotels: List<String>
    private lateinit var dataListPrice: List<String>
    private lateinit var dataListPhotos: List<Int>

    private lateinit var buttonSorting: Button
    private lateinit var buttonFilter: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        dataListHotels = arguments?.getStringArray("dataListHotels")?.toList() ?: emptyList()
        dataListPrice = arguments?.getStringArray("dataListPrice")?.toList() ?: emptyList()
        dataListPhotos = arguments?.getIntArray("dataListPhotos")?.toList() ?: emptyList()
        adapterHotel = AdapterHotel(requireContext(), dataListHotels, dataListPrice, dataListPhotos)
        recyclerView.adapter = adapterHotel
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSorting = binding.buttonSorting
        buttonSorting.setOnClickListener {
            val sortingDialog = SortingDialogFragment()
            sortingDialog.setSortingDialogListener(this)
            sortingDialog.show(childFragmentManager, "sorting_dialog")
        }

        buttonFilter = binding.buttonFilter
        buttonFilter.setOnClickListener {
            val intent = Intent(requireActivity(), HotelPageActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(dataListHotels: List<String>, dataListPrice: List<String>, dataListPhotos: List<Int>): HotelsFragment {
            val fragment = HotelsFragment()
            val args = Bundle().apply {
                putStringArray("dataListHotels", dataListHotels.toTypedArray())
                putStringArray("dataListPrice", dataListPrice.toTypedArray())
                putIntArray("dataListPhotos", dataListPhotos.toIntArray())
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onSortingOptionSelected(option: String) {
        TODO("Not yet implemented")
    }
}