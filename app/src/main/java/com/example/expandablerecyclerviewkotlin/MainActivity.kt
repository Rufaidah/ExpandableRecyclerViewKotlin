package com.example.expandablerecyclerviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewAdapter = RecyclerViewAdapter(getDummyDataToPass())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun getDummyDataToPass():MutableList<ParentDataItem> {
        val dummyDataItems: MutableList<ParentDataItem> = ArrayList()
        var childDataItems: MutableList<ChildDataItem>

        for (k in 1..4) {
            childDataItems = ArrayList()
            for (i in 0..k) {
                val dummyChildDataItem = ChildDataItem("Child Item $k.$i")
                childDataItems.add(dummyChildDataItem)
            }
            val dummyParentDataItem = ParentDataItem("Parent $k", childDataItems)
            dummyDataItems.add(dummyParentDataItem)
        }
        return dummyDataItems
    }
}
