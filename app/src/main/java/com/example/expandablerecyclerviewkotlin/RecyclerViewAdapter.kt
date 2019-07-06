package com.example.expandablerecyclerviewkotlin

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_parent_child_listing.view.*

class RecyclerViewAdapter(val dummyParentDataItems: MutableList<ParentDataItem>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerViewAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_child_listing, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dummyParentDataItems.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        val dummyParentDataItems = dummyParentDataItems[position]
        holder.itemView.tv_parentName.text = dummyParentDataItems.parentName

        holder.bind(dummyParentDataItems)
    }

    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var context : Context? = null
        var ivArrow: ImageView = itemView.findViewById(R.id.iv_arrow)

        override fun onClick(v: View?) {
            if (v?.id == R.id.tv_parentName) {
                if (itemView.ll_child_items?.visibility == View.VISIBLE) {
                    itemView.ll_child_items?.visibility = View.GONE
                    ivArrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
                } else {
                    itemView.ll_child_items?.visibility = View.VISIBLE
                    ivArrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
                }
            } else {
                val textViewClicked = v as TextView
                Toast.makeText(context, "" + textViewClicked.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(dummyParentDataItems: ParentDataItem) {

            val intMaxOfNoChild = dummyParentDataItems.childDataItems.size
            val noOfChild = dummyParentDataItems.childDataItems.size
            val noOfChildTextViews = itemView.ll_child_items?.childCount

            context = itemView.context
            itemView.ll_child_items?.visibility = View.GONE

            for (indexView in 0 until intMaxOfNoChild) {
                val textView = TextView(context)
                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                textView.id = indexView
                textView.setPadding(0, 20, 0, 20)
                textView.gravity = Gravity.CENTER
                textView.background = ContextCompat.getDrawable(context!!, R.drawable.background_sub_module_text)

                textView.setOnClickListener(this)
                itemView.ll_child_items?.addView(textView, layoutParams)
            }
            itemView.tv_parentName?.setOnClickListener(this)

//            for (noOfChild < noOfChildTextViews!!) {
//                for (index in noOfChild until noOfChildTextViews) {
//                    val currentTextView = itemView.ll_child_items!!.getChildAt(index) as TextView
//                    currentTextView.visibility = View.GONE
//                }
//            }

            for (textViewIndex in 0 until noOfChild) {
                val currentTextView = itemView.ll_child_items!!.getChildAt(textViewIndex) as TextView
                currentTextView.text = dummyParentDataItems.childDataItems[textViewIndex].childName
            }
        }

    }

}