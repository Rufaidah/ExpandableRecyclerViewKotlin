package com.example.expandablerecyclerviewkotlin

import java.io.Serializable

data class ParentDataItem (val parentName:String, val childDataItems: List<ChildDataItem>) : Serializable