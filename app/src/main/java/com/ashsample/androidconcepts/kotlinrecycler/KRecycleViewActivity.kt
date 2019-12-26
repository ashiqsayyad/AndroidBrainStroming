package com.ashsample.androidconcepts.kotlinrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashsample.androidconcepts.R
import kotlinx.android.synthetic.main.activity_krecycle_view.*
//https://www.journaldev.com/23989/android-recyclerview-data-binding
class KRecycleViewActivity : AppCompatActivity() {
    private lateinit var kRecycleAdapter: KRecycleViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_krecycle_view)
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = KDataSource.createDataSet()
        kRecycleAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        krecycler_view.apply {
            layoutManager = LinearLayoutManager(this@KRecycleViewActivity)
            val topSpacingDecorator = KRecycleViewDecorator(30)
            addItemDecoration(topSpacingDecorator)
            kRecycleAdapter = KRecycleViewAdapter()
            adapter = kRecycleAdapter
        }
    }
}
