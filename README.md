# NumberInputDialog
Provide a dialog for number input with range prompt.

## Usage
```
fab.setOnClickListener { _ ->
            val input = Input("Title", textView.text.toString())
            input.max = 100.0 //max limit
            input.min = 0.0 //min limit
            val fragment = InputDialogFragment.newInstance(input, this)
            fragment.show(supportFragmentManager, "InputDialogFragment")
        }
```
don't forget to implements the InputCallback:
```
class MainActivity : AppCompatActivity(), InputCallback {
    ...
    override fun onInputFinished(value: Double) {
        textView.text = value.toString()
    }
    ...
}
```
