package com.example.soradaprathan_comp304sec001_lab03_ex02


import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.*


class LongDateTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var myCustomAttribute: Int = 0

    companion object {
        private const val DEFAULT_LONG_DATE_FORMAT = "MMMM dd, yyyy HH:mm:ss z"
    }

    // Define longDate attribute
    var longDate: String? = null
        set(value) {
            field = value
            text = formatDate(value?.toLongOrNull() ?: System.currentTimeMillis())
        }


    private fun formatDate(dateInMillis: Long): String {
        val cal = Calendar.getInstance().apply { timeInMillis = dateInMillis }
        val sdf = SimpleDateFormat(DEFAULT_LONG_DATE_FORMAT, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault() // set the timezone to the default timezone
        return sdf.format(cal.time)
    }
    init {
        // Obtain the styled attributes defined in the XML file
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.LongDateTextView, defStyleAttr, 0
        )

        // Get the value of the custom attribute
        if(a.hasValue(R.styleable.LongDateTextView_longDate))
        {
            longDate = a.getString(R.styleable.LongDateTextView_longDate)
        }

        // Recycle the TypedArray to free up resources
        a.recycle()
    }



}