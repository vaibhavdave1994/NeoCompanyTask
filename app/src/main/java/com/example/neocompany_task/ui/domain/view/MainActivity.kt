package com.example.neocompany_task.ui.domain.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.neocompany_task.R
import com.example.neocompany_task.databinding.ActivityMainBinding
import com.example.neocompany_task.ui.data.model.FruitModel
import com.example.neocompany_task.ui.data.model.ImageItem
import com.example.neocompany_task.ui.domain.view.adapter.CustomerClickListener
import com.example.neocompany_task.ui.domain.view.adapter.FruitAdapter
import com.example.neocompany_task.ui.domain.view.adapter.ImageAdapter
import com.example.neocompany_task.ui.domain.view.dialog.ModalBottomSheetDialog
import com.example.neocompany_task.ui.domain.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var fruitList: MutableList<FruitModel>
    private var str: String =""
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
    private val fruitAdapter: FruitAdapter by lazy { FruitAdapter(this) }
    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            getWindow().decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.lightwhite)
        }
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPager()

        fruitList = ArrayList<FruitModel>()
        if (mainViewModel.getList.size == 0) {
            setdata()
        } else {
            setupRecyclerView()
        }



    }

    private fun setdata() {
        lifecycleScope.launch {
            mainViewModel.delete(FruitModel())
        }

        fruitList.add(FruitModel("Apple", "Fruit"))
        fruitList.add(FruitModel("Avocados", "Fruit"))
        fruitList.add(FruitModel("Apricot", "Fruit"))
        fruitList.add(FruitModel("Banana", "Fruit"))
        fruitList.add(FruitModel("Blueberries", "Fruit"))
        fruitList.add(FruitModel("Cherry", "Fruit"))
        fruitList.add(FruitModel("Cantaloupe", "Fruit"))
        fruitList.add(FruitModel("Clementine", "Fruit"))
        fruitList.add(FruitModel("Cucumbers", "Fruit"))
        fruitList.add(FruitModel("Dewberries", "Fruit"))
        fruitList.add(FruitModel("Dragon", "Fruit"))
        fruitList.add(FruitModel("Elderberry", "Fruit"))
        fruitList.add(FruitModel("Evergreen", "Fruit"))
        fruitList.add(FruitModel("Imbe", "Fruit"))
        fruitList.add(FruitModel("Indian Fig", "Fruit"))
        fruitList.add(FruitModel("Jackfruit", "Fruit"))
        fruitList.add(FruitModel("Jambolan", "Fruit"))
        fruitList.add(FruitModel("Kiwi", "Fruit"))
        fruitList.add(FruitModel("Lime", "Fruit"))
        fruitList.add(FruitModel("Longan", "Fruit"))
        fruitList.add(FruitModel("Mango", "Fruit"))
        fruitList.add(FruitModel("Mandarin", "Fruit"))
        fruitList.add(FruitModel("Orange", "Fruit"))
        fruitList.add(FruitModel("Mulberry", "Fruit"))
        fruitList.add(FruitModel("Melon", "Fruit"))

        lifecycleScope.launch {
            mainViewModel.insert(fruitList)
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        binding.rvEmployees.apply {
            adapter = fruitAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            fruitAdapter.submitList(mainViewModel.getList)
            serchdata()
            setupModalButtons()
        }
    }

    private fun serchdata() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()) {
                    binding.viewpager2.visibility = View.VISIBLE
                    binding.slideDotLL.visibility = View.VISIBLE
                } else {
                    binding.viewpager2.visibility = View.GONE
                    binding.slideDotLL.visibility = View.GONE
                }
                filterList(newText)
                return true
            }

        })

        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.viewpager2.visibility = View.GONE
                binding.slideDotLL.visibility = View.GONE
            } else {
                binding.viewpager2.visibility = View.VISIBLE
                binding.slideDotLL.visibility = View.VISIBLE
            }
        }


    }


    private fun filterList(query: String?) {

        if (query != null) {
            var filteredList: MutableList<FruitModel> = ArrayList<FruitModel>()

            for (i in mainViewModel.getList) {
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


    fun setViewPager() {
        val imageList = ArrayList<ImageItem>()

        for (i in 0 until 5) {
            imageList.add(
                ImageItem(
                    R.drawable.fruits
                )
            )
        }
        val imageAdapter = ImageAdapter()
        binding.viewpager2.adapter = imageAdapter
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
        binding.viewpager2.registerOnPageChangeCallback(pageChangeListener)

    }



    private fun setupModalButtons() {

            binding.addFab.setOnClickListener {
                val modal = ModalBottomSheetDialog(mainViewModel.getList)
                supportFragmentManager.let { modal.show(it, ModalBottomSheetDialog.TAG) }
            }

        }



}
