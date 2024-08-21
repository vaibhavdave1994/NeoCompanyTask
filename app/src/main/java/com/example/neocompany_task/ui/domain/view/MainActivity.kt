package com.example.neocompany_task.ui.domain.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.neocompany_task.theme.JetPackComposeHiltMvvmDemoTheme
import com.example.neocompany_task.ui.domain.view.mainscreen.mainScreen
import com.example.neocompany_task.ui.domain.viewmodel.MainViewModel
import com.example.neocompany_task.util.Utility.Companion.fruitList
import com.example.neocompany_task.util.Utility.Companion.setDataList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackComposeHiltMvvmDemoTheme {
                setDataList()
                val mainViewModel: MainViewModel = hiltViewModel()
                lifecycleScope.launch {
                    mainViewModel.insert(fruitList)
                }
                App()
            }
        }
    }
}



@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        mainScreen()
    }

}


/*
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





    private fun setupModalButtons() {
        binding.addFab.setOnClickListener {
            val modal = ModalBottomSheetDialog(mainViewModel.getList)
            supportFragmentManager.let { modal.show(it, Utility.TAG) }
        }

    }


}
*/
