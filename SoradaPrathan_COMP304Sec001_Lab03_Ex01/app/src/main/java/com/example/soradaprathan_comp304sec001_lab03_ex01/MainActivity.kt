package com.example.soradaprathan_comp304sec001_lab03_ex01

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(),  View.OnClickListener{

    private lateinit var reusableImageView: ImageView
    private lateinit var y_textView: TextView
    private lateinit var leftImageButton: ImageButton
    private lateinit var rightImageButton: ImageButton
    private lateinit var upImageButton: ImageButton
    private lateinit var downImageButton: ImageButton
    private lateinit var line_thickness_spinner: Spinner
    private lateinit var lineColorRadioGroup : RadioGroup
    private var selectedColor = Color.RED


    //
    private var startx = 10
    private var starty = 10
    private var endx = 300
    private var endy = 300


    //
    private lateinit var canvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private lateinit var bitmap: Bitmap


    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = selectedColor // set the default color
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.canvas_layout)
        leftImageButton = findViewById(R.id.arrow_left_imageBtn)
        rightImageButton = findViewById(R.id.arrow_right_imageBtn)
        upImageButton = findViewById(R.id.arrow_up_imageBtn)
        downImageButton = findViewById(R.id.arrow_down_imageBtn)
        // Set onClickListeners for arrow image buttons
        leftImageButton.setOnClickListener(this)
        rightImageButton.setOnClickListener(this)
        upImageButton.setOnClickListener(this)
        downImageButton.setOnClickListener(this)
        line_thickness_spinner = findViewById(R.id.line_thickness_spinner)
        lineColorRadioGroup = findViewById(R.id.line_color_radioGroup)
        line_thickness_spinner.setSelection(0)
        lineColorRadioGroup.check(R.id.red_line_color_radioBtn)

        // Set up spinner

        line_thickness_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                paint.strokeWidth = selectedItem.toFloat()
                line_thickness_spinner.setSelection(position)

                clearCanvas(view)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        lineColorRadioGroup.setOnCheckedChangeListener{_, checkedId ->
            selectedColor = when (checkedId){
                R.id.red_line_color_radioBtn -> Color.RED
                R.id.yellow_line_color_radioBtn -> Color.YELLOW
                R.id.cyan_line_color_radioBtn -> Color.CYAN
                else -> Color.RED //default color
            }
            paint.color = selectedColor // update the paint color
        }

        bitmap = Bitmap.createBitmap(
            windowManager
                .defaultDisplay.width, windowManager
                .defaultDisplay.height, Bitmap.Config.ARGB_8888
        )
        //tell canvas about the content view
        canvas = Canvas(bitmap)
        //set the background for your drawings
        canvas.drawColor(Color.BLACK) //background
        //setup the image view
        reusableImageView = findViewById<View>(R.id.draw_imageView) as ImageView
        //tell image view for the bitmap format/content
        reusableImageView.setImageBitmap(bitmap)
        reusableImageView.setVisibility(View.VISIBLE)
        y_textView = findViewById<View>(R.id.yValue_textView) as TextView
    }

    fun clearCanvas(v: View?) {
        canvas?.drawColor(Color.BLACK)
        startx = 10
        starty = 10
        endx = 300
        endy = 300
        y_textView!!.text = "";
    }

    fun startDrawing(v: View?) {
        canvas.drawPoint(15f, 15f, paint!!)
    }

    fun drawLine(canvas: Canvas) {
        Log.d("MainActivity", "strokeWidth: ${paint.strokeWidth}")
        y_textView.text = "y= "+ endy.toString()
        canvas.drawLine(startx.toFloat(), starty.toFloat(), endx.toFloat(), endy.toFloat(), paint!!)
        startx = endx
        starty = endy
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.arrow_left_imageBtn -> {
                endx = endx - 5
                drawLine(canvas)
            }
            R.id.arrow_right_imageBtn -> {
                endx = endx + 5
                drawLine(canvas)
            }
            R.id.arrow_up_imageBtn -> {
                endy = endy - 5
                drawLine(canvas)
            }
            R.id.arrow_down_imageBtn -> {
                endy = endy + 5
                drawLine(canvas)
            }
        }
    }



}