package com.example.handyhotel

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment


class SortingDialogFragment : DialogFragment() {

    private var listener: SortingDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_sorting, null)

        builder.setView(dialogView)
            .setTitle(null)
            .setPositiveButton("", null)
            .setNegativeButton("", null)

        val dialog = builder.create()

        val btnSortByName = dialogView.findViewById<Button>(R.id.btnSortByName)
        val btnSortByName2 = dialogView.findViewById<Button>(R.id.btnSortByName2)
        val btnSortByName3 = dialogView.findViewById<Button>(R.id.btnSortByName3)
        val btnSortByName4 = dialogView.findViewById<Button>(R.id.btnSortByName4)

        btnSortByName.setOnClickListener {
            listener?.onSortingOptionSelected("По имени")
            dialog.dismiss()
        }

        btnSortByName2.setOnClickListener {
            listener?.onSortingOptionSelected("По цене")
            dialog.dismiss()
        }

        btnSortByName3.setOnClickListener {
            listener?.onSortingOptionSelected("По цене")
            dialog.dismiss()
        }

        btnSortByName4.setOnClickListener {
            listener?.onSortingOptionSelected("По цене")
            dialog.dismiss()
        }

        return dialog
    }

    interface SortingDialogListener {
        fun onSortingOptionSelected(option: String)
    }

    fun setSortingDialogListener(listener: SortingDialogListener) {
        this.listener = listener
    }
}


