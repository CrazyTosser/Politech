package logo

import react.RBuilder
import react.dom.div
import react.dom.img
import react.dom.jsStyle

fun RBuilder.logo() {
    div("Logo") {
        for (i in 0..63)
            img(alt = "Kotlin logo.logo", src = "CA.png", classes = "Logo-kotlin") {
                attrs {
                    jsStyle {
                        top = i * 2
                    }
                }
            }
    }
}
