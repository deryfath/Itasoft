package com.itasoft.project.module

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.itasoft.project.Utils.EndlessScrollListener
import com.itasoft.project.Utils.gone
import com.itasoft.project.Utils.observe
import com.itasoft.project.Utils.visible
import com.itasoft.project.module.adapter.MainAdapter
import com.itasoft.project.module.viewmodel.MainViewModel
import com.itasofttest.project.R
import com.itasofttest.project.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var sortingType: String = ""
    private val myViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private var currentPage = 1;
    private val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel.getListStokBarang(currentPage)
        initObserver()
        setSupportActionBar(binding.toolbar)
    }

    private fun initObserver() {

        binding.rvStok.addOnScrollListener(loadScrollData())

        observe(myViewModel.stokLiveData, {
            val data = it?.data?.toMutableList() ?: mutableListOf()
            if(data.size > 0) {
                binding.rvStok.visible()
                if (currentPage == 1) {
                    adapter = MainAdapter(data, this)
                    val mLayoutManager = GridLayoutManager(this, 2)
                    binding.rvStok.setLayoutManager(mLayoutManager)
                    binding.rvStok.adapter = adapter
                } else {
                    adapter.addItems(data)
                }
            }else{
                binding.rvStok.gone()
            }
        })

        observe(myViewModel.isDataLoading,{
            if (it == true){
                println("LOADING")
                binding.pbMain.visible()
            }else{
                handler.postDelayed(Runnable {
                    binding.pbMain.gone()

                }, 3000)
            }
        })

    }

    private fun loadScrollData():EndlessScrollListener{

        return object : EndlessScrollListener(){
            override fun onLoadMore() {
                currentPage++
                myViewModel.getListStokBarang(currentPage)
            }
        }
    }

}
