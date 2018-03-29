package com.lee.numberinputdialog


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lee.numberinputdialog.input.Input
import kotlinx.android.synthetic.main.fragment_input_dialog.*

/**
 * A simple [Fragment] subclass.
 *
 */
class InputDialogFragment : DialogFragment(), TextWatcher {

    private var input: Input? = null
    private var max: Double = 0.0
    private var min: Double = 0.0

    companion object {
        fun newInstance(input: Input): InputDialogFragment {
            val fragment = InputDialogFragment()
            val args = Bundle()
            args.putSerializable("input", input)
            fragment.arguments = args
            fragment.isCancelable = false
            fragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            input = (arguments as Bundle).getSerializable("input") as Input
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_input_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hint = input?.min.toString() + "~" + input?.max.toString()
        max = input?.max as Double
        min = input?.min as Double
        titleText.text = input?.title
        editText.addTextChangedListener(this)
        editText.setText(input?.def)
//        editText.hint = hint
        textLayout.hint = hint
        canBtn.setOnClickListener { _ -> dismiss() }
        okBtn.setOnClickListener { _ ->
            input?.callback?.onInputFinished(editText.text.toString().toDouble())
            dismiss()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d(tag, s.toString())
    }

    override fun afterTextChanged(s: Editable?) {
        try {
            val temp = s.toString().toDouble()
            if (temp in min..max) {
                textLayout.error = null
                okBtn.isEnabled = true
            } else {
                textLayout.error = "Out Of Range!"
                okBtn.isEnabled = false
            }
        } catch (e: Exception) {
            okBtn.isEnabled = false
            textLayout.error = "Invalid Number!"
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d(tag, s.toString())
    }
}
