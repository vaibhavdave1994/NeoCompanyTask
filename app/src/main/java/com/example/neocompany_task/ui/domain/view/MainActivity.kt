package com.example.neocompany_task.ui.domain.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.neocompany_task.R
import com.example.neocompany_task.core.BaseActivity
import com.example.neocompany_task.databinding.ActivityMainBinding
import com.example.neocompany_task.ui.data.model.FruitModel
import com.example.neocompany_task.ui.data.model.ImageItem
import com.example.neocompany_task.ui.domain.view.adapter.FruitAdapter
import com.example.neocompany_task.ui.domain.view.adapter.ImageAdapter
import com.example.neocompany_task.ui.domain.view.dialog.ModalBottomSheetDialog
import com.example.neocompany_task.ui.domain.viewmodel.MainViewModel
import com.example.neocompany_task.util.Utility
import com.example.neocompany_task.util.Utility.Companion.fruitList
import com.example.neocompany_task.util.Utility.Companion.setDataList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var str: String = ""
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
    private val fruitAdapter: FruitAdapter by lazy { FruitAdapter(this) }
    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()
        if (mainViewModel.getList.size == 0) {
            setData()
        } else {
            setupRecyclerView()
        }

    }

    private fun setData() {
        lifecycleScope.launch {
            mainViewModel.delete(FruitModel())
        }
        setDataList()
        lifecycleScope.launch {
            mainViewModel.insert(fruitList)
        }
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        if (mainViewModel.getList.size == 0) {
            setData()
        } else {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        binding.rvEmployees.apply {
            adapter = fruitAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            if (mainViewModel.getList.size != 0) {
                fruitAdapter.submitList(mainViewModel.getList)

            } else {
                fruitAdapter.submitList(fruitList)
            }
            searchData()
            setupModalButtons()
        }
    }

    private fun searchData() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()) {
                    binding.viewPager2.visibility = View.VISIBLE
                    binding.slideDotLL.visibility = View.VISIBLE
                } else {
                    binding.viewPager2.visibility = View.GONE
                    binding.slideDotLL.visibility = View.GONE
                }
                filterList(newText)
                return true
            }

        })

        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.viewPager2.visibility = View.GONE
                binding.slideDotLL.visibility = View.GONE
            } else {
                binding.viewPager2.visibility = View.VISIBLE
                binding.slideDotLL.visibility = View.VISIBLE
            }
        }
    }


    private fun filterList(query: String?) {

        if (query != null) {
            var filteredList: MutableList<FruitModel> = ArrayList<FruitModel>()
            var list : MutableList<FruitModel> = ArrayList()
            if (mainViewModel.getList.size != 0) {
                list.addAll(mainViewModel.getList)
            } else {
                list.addAll(fruitList)
            }

            for (i in list) {
                if (i.fruitName!!.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                fruitAdapter.submitList(filteredList)
            }
        }
    }


    private fun setViewPager() {
        val imageList = ArrayList<ImageItem>()

        for (i in 0 until 5) {
            imageList.add(
                ImageItem(
                    R.drawable.fruits
                )
            )
        }
        val imageAdapter = ImageAdapter()
        binding.viewPager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val slideDotLL = findViewById<LinearLayout>(R.id.slideDotLL)
        val dotsImage = Array(imageList.size) { ImageView(this) }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slideDotLL.addView(it, params)
        }

        // default first dot selected
        dotsImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position == index) {
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    } else {
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }
        binding.viewPager2.registerOnPageChangeCallback(pageChangeListener)

    }


    private fun setupModalButtons() {
        binding.addFab.setOnClickListener {
            val modal = ModalBottomSheetDialog(mainViewModel.getList)
            supportFragmentManager.let { modal.show(it, Utility.TAG) }
        }

    }


}
