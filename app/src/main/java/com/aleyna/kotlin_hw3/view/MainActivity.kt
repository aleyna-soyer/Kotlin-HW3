package com.aleyna.kotlin_hw3.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleyna.kotlin_hw3.R
import com.aleyna.kotlin_hw3.adapter.ProductAdapter
import com.aleyna.kotlin_hw3.databinding.ActivityMainBinding
import com.aleyna.kotlin_hw3.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var productAdapter = ProductAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.productRV.adapter = productAdapter
        binding.productRV.layoutManager = LinearLayoutManager(this)
        viewModel.getDataFromAPI()

        setObservers()
    }

    private fun setObservers(){
        viewModel.productsData.observe(this) { list ->
            productAdapter.updateList(list)
        }

        viewModel.productsLoad.observe(this) { loading ->
            if (loading) {
                binding.loadingPB.visibility = View.VISIBLE
            } else {
                binding.loadingPB.visibility = View.GONE
            }
        }
        viewModel.productsError.observe(this) { error ->
            if (error) {
                binding.errorTV.visibility = View.VISIBLE
            } else {
                binding.errorTV.visibility = View.GONE
            }
        }


    }
}