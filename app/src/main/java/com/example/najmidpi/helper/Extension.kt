package com.example.najmidpi.helper

import com.example.najmidpi.database.tables.SensorTable

object Extension {

    fun filterListByDate(list: MutableList<SensorTable>, from: String, to: String): List<SensorTable> {

        val fromDate = from.split("/")
        val toDate = to.split("/")

        val fromYear  = fromDate[0].toInt()
        val fromMonth = fromDate[1].toInt()
        val fromDay   = fromDate[2].toInt()

        val toYear  = toDate[0].toInt()
        val toMonth = toDate[1].toInt()
        val toDay   = toDate[2].toInt()

        val filterByDate = list.filter {

            val date = it.date.split("/")

            val year  = date[0].toInt()
            val month = date[1].toInt()
            val day   = date[2].toInt()

            year in fromYear..toYear && month in fromMonth..toMonth && day in fromDay..toDay
        }

        return filterByDate
    }
}