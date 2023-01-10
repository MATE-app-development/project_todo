package com.example.mate

import java.text.DateFormat
import java.time.format.DateTimeFormatter

data class todo(
    var tID: Int?,
    var tName: String?,
    var tdate: DateFormat?,
    var tdline: DateTimeFormatter,
    var tipt: Int,
    var trpt: Boolean,
    var talarm: DateTimeFormatter,
    var tdone: Boolean,
    var tdel: Boolean)
{ }

