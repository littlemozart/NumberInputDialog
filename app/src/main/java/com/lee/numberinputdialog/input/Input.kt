package com.lee.numberinputdialog.input

import java.io.Serializable

class Input : Serializable {
    var title: String
    var max: Double = 0.0
    var min: Double = 0.0
    var callback: InputCallback
    var def: String = ""

    constructor(title: String, callback: InputCallback) {
        this.title = title
        this.callback = callback
    }


}