package ipca.am1.sergio.listdateshours

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.TemporalQueries.localDate
import java.util.*

class MainActivity : AppCompatActivity() {

    var listDates: MutableList<DatesModel> = arrayListOf()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        val listViewSimpleDate = findViewById<ListView>(R.id.listView_DatesHours)
        val dateAdapter = DateAdapter()
        listViewSimpleDate?.adapter = dateAdapter

        GlobalScope.launch(Dispatchers.IO){
            var srt : String = ""
            val dateObject = LocalDateTime(srt)
            val dateArray = dateObject.toLocalDate()
            
           for (index in 0 until dateArray.length())
                val localDate = dateArray.get(index) as DateFormat
                val listsofdates = DatesModel.fromDate(localDate)
                listDates.add(listsofdates)


        }
        GlobalScope.launch(Dispatchers.Main){
            dateAdapter.notifyDataSetChanged()
        }

    }

    inner class DateAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return listDates.size
        }

        override fun getItem(position: Int): Any {
            return listDates[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val rowView: View?
            val vhDates: ViewHolder

            if (convertView == null) {
                rowView = layoutInflater.inflate(R.layout.row_view_dates, parent, false)
                vhDates = ViewHolder(rowView)
                rowView.tag = vhDates

                findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {


                    val textViewDate = rowView.findViewById<TextView>(R.id.textView_ItenView)

                    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").toString().toShort()

                    val currentDateAndTime = simpleDateFormat
                    textViewDate.text = currentDateAndTime.toString()
                }

            } else {
                rowView = convertView
                vhDates = rowView.tag as ViewHolder

            }
            vhDates.textViewDate.text = listDates[position].date


            return rowView

        }
    }

    private class ViewHolder(view: View){
        val textViewDate : TextView = view.findViewById<TextView>(R.id.textView_ItenView) as TextView
    }

/*
   //create an extension function on a date class which returns a string
    private fun Date.dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }
*/

    //call the extension function on a date object


}












