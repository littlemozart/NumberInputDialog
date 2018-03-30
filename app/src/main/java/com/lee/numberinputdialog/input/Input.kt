package com.lee.numberinputdialog.input

import java.io.Serializable

class Input : Serializable {
    var title: String
    var max: Double = 0.0
    var min: Double = 0.0
    var def: String = ""

    constructor(title: String) {
        this.title = title
    }

    constructor(title: String, def: String) {
        this.title = title
        this.def = def
    }
}