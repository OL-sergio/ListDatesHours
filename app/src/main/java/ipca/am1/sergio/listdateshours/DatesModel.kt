package ipca.am1.sergio.listdateshours

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.*
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DatesModel {

    var date:  String? = null

    constructor(date: String){

        this.date = date

    }

    constructor(){

    }

    @RequiresApi(VERSION_CODES.O)
    fun toDate() : LocalDate{
        var dateObject = LocalDate.now()

        dateObject = LocalDate.parse("yyyy mm dd ")

        return dateObject
    }

    companion object {




        @SuppressLint("NewApi")
        fun fromDate(dateObject: LocalDate) : DatesModel{

            val datesModel = DatesModel()

            datesModel.date = dateObject.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

            return datesModel
        }


    }

}





