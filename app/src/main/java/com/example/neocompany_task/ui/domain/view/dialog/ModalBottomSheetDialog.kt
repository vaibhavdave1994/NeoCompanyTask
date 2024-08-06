package com.example.neocompany_task.ui.domain.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neocompany_task.databinding.DesignBottomSheetBinding
import com.example.neocompany_task.ui.data.model.FruitModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class ModalBottomSheetDialog @Inject constructor(val fruitlist: List<FruitModel>) : BottomSheetDialogFragment() {
    private lateinit var binding : DesignBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DesignBottomSheetBinding.inflate(inflater, container, false)
        countListChar(binding)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // used to show the bottom sheet dialog
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }


    fun countListChar(binding: DesignBottomSheetBinding) {
        // Sample ArrayList
        var list = ArrayList<String>()
        for (i in 0 until fruitlist.size) {
            fruitlist.get(i).fruitName?.let { list.add(it) }
        }
        // MutableMap to store counts of first characters
        val firstCharCount = mutableMapOf<Char, Int>()

        // Iterate through the list and count first characters

        for (s in list) {
            if (s.isNotEmpty()) {
                val firstChar = s[0]
                firstCharCount[firstChar] = firstCharCount.getOrDefault(firstChar, 0) + 1
            }
        }


        // Print the result
        val builder = StringBuilder()

        for ((char, count) in firstCharCount) {
            builder.append("Character: $char, Count: $count" + "\n");
            println("Character: $char, Count: $count")
        }

        binding.textShare.text = builder.toString()

    }
}