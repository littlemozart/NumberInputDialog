package com.lee.numberinputdialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lee.numberinputdialog.input.Input
import com.lee.numberinputdialog.input.InputCallback

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), InputCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            val input = Input("Title", this)
            input.max = 100.0 //max limit
            input.min = 0.0 //min limit
            input.def = textView.text.toString() //default value
            val fragment = InputDialogFragment.newInstance(input)
            fragment.show(supportFragmentManager, "InputDialogFragment")
        }
    }

    override fun onInputFinished(value: Double) {
        textView.text = value.toString()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("text", textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState?.getString("text")
    }

}
