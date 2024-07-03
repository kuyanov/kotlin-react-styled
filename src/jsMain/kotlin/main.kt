import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.span
import web.cssom.*

private val App = FC<Props> {
    var disabled1 by useState(false)
    var disabled2 by useState(false)
    var disabled3 by useState(false)
    var language by useState("ru")
    var checked1 by useState(false)
    var checked2 by useState(false)
    div {
        styledButton {
            buttonBuilder = {
                span {
                    className = ClassName("fa-solid fa-play")
                }
                disabled = disabled1
                onClick = {
                    disabled1 = true
                    disabled2 = false
                    disabled3 = false
                }
            }
            buttonStyles = {
                height = 3.em
                width = 3.em
                borderRadius = 50.pct
            }
        }
        styledButton {
            colorTheme = LightBlueButtonTheme()
            buttonBuilder = {
                if (language == "en") +"Button 2"
                else +"Кнопка 2"
                disabled = disabled2
                onClick = {
                    disabled1 = false
                    disabled2 = true
                    disabled3 = false
                }
            }
        }
        styledButton {
            colorTheme = LightGreenButtonTheme()
            cursor = Cursor.copy
            borderRadius = 0.5.em
            borderWidth = 1.px
            shadowed = true
            buttonBuilder = {
                span {
                    className = ClassName("fa-solid fa-tablet")
                }
                if (language == "en") +" Copy"
                else +" Копировать"
                disabled = disabled3
                onClick = {
                    disabled1 = false
                    disabled2 = false
                    disabled3 = true
                }
            }
        }
        styledSelect {
            colorTheme = DarkGreenButtonTheme()
            selectBuilder = {
                value = language
                onChange = {
                    language = it.target.value
                }
                option {
                    +"EN"
                    value = "en"
                }
                option {
                    +"RU"
                    value = "ru"
                }
            }
        }
        styledDropdown {
            buttonBuilder = {
                +"Menu"
            }
            contentBuilder = {
                styledButton {
                    buttonBuilder = {
                        +"Menu button 1"
                    }
                    buttonStyles = {
                        borderRadius = 0.5.em
                    }
                }
                styledButton {
                    buttonBuilder = {
                        +"Menu button 2"
                    }
                    buttonStyles = {
                        borderRadius = 0.5.em
                    }
                }
            }
            contentStyles = {
                backgroundColor = NamedColor.white
                borderRadius = 0.5.em
                boxShadow = BoxShadow(0.px, 0.px, 0.5.em, NamedColor.lightgrey)
                margin = 5.px
                width = 200.px
                zIndex = integer(10)
            }
        }
    }
    div {
        styledInput {
//            appearance = StyledInputAppearance.Underline
//            borderRadius = 0.0.em
            colorTheme = TransparentBlueInputTheme()
            inputBuilder = {
                placeholder = "Enter text here"
                disabled = disabled1
            }
            inputStyles = {
                margin = 10.px
                width = 300.px
            }
        }
    }
    div {
        styledTextArea {
            colorTheme = TransparentBlueInputTheme()
            textAreaBuilder = {
                placeholder = "Long text goes here"
                disabled = disabled2
            }
            textAreaStyles = {
                margin = 10.px
                width = 300.px
            }
        }
    }
    div {
        styledCheckBox {
            colorTheme = LightBlueCheckBoxTheme()
            checkBoxInputBuilder = {
                disabled = disabled1
                checked = checked1
                onChange = {
                    checked1 = !checked1
                }
            }
            checkBoxLabelBuilder = {
                +"Check box 1"
            }
            checkBoxFieldStyles = {
                marginLeft = 10.px
            }
        }
        styledCheckBox {
            colorTheme = LightGreenCheckBoxTheme()
            borderRadius = 20.pct
            shadowed = true
            checkBoxInputBuilder = {
                disabled = disabled2
                checked = checked2
                onChange = {
                    checked2 = !checked2
                }
            }
            checkBoxLabelBuilder = {
                +"Check box 2"
            }
            checkBoxFieldStyles = {
                marginLeft = 10.px
            }
        }
    }
}

fun main() {
    root {
        rootBuilder = {
            App()
        }
        rootStyles = {
            font = Font(FontStyle.normal, 20.px, string("Open Sans"))
        }
    }
}
