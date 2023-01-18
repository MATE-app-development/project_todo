package com.example.mate

import java.text.DateFormat
import java.time.format.DateTimeFormatter

data class todo(
    var tID: Int?,
    var tName: String?,
    var tdate: String?, //나중에 date로 바꾸겠습니다. 데이터타입은 바꿨지만 형식은 "YYYY-MM-DD"로 동일하게 갑니다.
    var tdline: String?, //나중에 datetime으로 바꾸겠습니다. 형식은 'YYYY-MM-DD TT:MM:SS'로 동일하게 갑니다. (초 생략시 자동 00초로 맞춰짐)
    var tipt: Int?,
    var trpt: Boolean?,
    var talarm: String?, //나중에 datetime으로 바꾸겠습니다.
    var tdone: Boolean?,
    var tdel: Boolean?)
{ }

