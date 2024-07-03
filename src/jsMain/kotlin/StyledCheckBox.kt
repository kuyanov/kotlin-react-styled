import csstype.PropertiesBuilder
import emotion.react.css
import react.*
import react.dom.html.InputHTMLAttributes
import react.dom.html.LabelHTMLAttributes
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.span
import web.cssom.*
import web.dom.document
import web.html.HTMLInputElement
import web.html.HTMLLabelElement
import web.html.InputType
import kotlin.random.Random

external interface StyledCheckBoxAttrs {
    var colorTheme: CheckBoxTheme
    var borderRadius: Length
    var borderWidth: Length
    var shadowed: Boolean
}

fun StyledCheckBoxAttrs.defaultStyledCheckBoxAttrs() {
    colorTheme = TransparentCheckBoxTheme()
    borderRadius = 50.pct
    borderWidth = 1.px
    shadowed = false
}

external interface StyledCheckBoxPropsGeneric<CheckBoxFieldStylesType, CheckBoxInputBuilderType,
        CheckBoxLabelBuilderType, CheckBoxLabelStylesType> : StyledCheckBoxAttrs, Props {
    var checkBoxInputBuilder: CheckBoxInputBuilderType
    var checkBoxFieldStyles: CheckBoxFieldStylesType
    var checkBoxLabelBuilder: CheckBoxLabelBuilderType
    var checkBoxLabelStyles: CheckBoxLabelStylesType
}

typealias CheckBoxInputBuilderType = InputHTMLAttributes<HTMLInputElement>.() -> Unit
typealias CheckBoxFieldStylesType = PropertiesBuilder.() -> Unit
typealias CheckBoxLabelBuilderType = LabelHTMLAttributes<HTMLLabelElement>.() -> Unit
typealias CheckBoxLabelStylesType = PropertiesBuilder.() -> Unit
typealias StyledCheckBoxProps = StyledCheckBoxPropsGeneric<CheckBoxFieldStylesType,
        CheckBoxInputBuilderType, CheckBoxLabelBuilderType, CheckBoxLabelStylesType>

fun StyledCheckBoxProps.defaultStyledCheckBoxProps() {
    defaultStyledCheckBoxAttrs()
    checkBoxInputBuilder = {}
    checkBoxFieldStyles = {}
    checkBoxLabelBuilder = {}
    checkBoxLabelStyles = {}
}

fun PropertiesBuilder.styledCheckBoxFieldCSS(
    attrs: StyledCheckBoxAttrs,
    checked: Boolean, disabled: Boolean
) {
    borderRadius = attrs.borderRadius
    display = Display.flex
    flexShrink = number(0.0)
    height = 1.25.em
    width = 1.25.em
    margin = Margin(Auto.auto, 5.px, Auto.auto, 0.px)
    userSelect = None.none
    if (!checked) {
        if (!disabled) {
            backgroundColor = attrs.colorTheme.blankPrimaryBackgroundColor
            border = Border(attrs.borderWidth, LineStyle.solid, attrs.colorTheme.blankPrimaryBorderColor)
            hover {
                backgroundColor = attrs.colorTheme.blankHoverBackgroundColor
                borderColor = attrs.colorTheme.blankHoverBorderColor
            }
            active {
                backgroundColor = attrs.colorTheme.blankActiveBackgroundColor
                borderColor = attrs.colorTheme.blankActiveBorderColor
            }
        } else {
            backgroundColor = attrs.colorTheme.blankDisabledBackgroundColor
            border = Border(attrs.borderWidth, LineStyle.solid, attrs.colorTheme.blankDisabledBorderColor)
        }
    } else {
        if (!disabled) {
            backgroundColor = attrs.colorTheme.checkedPrimaryBackgroundColor
            border = Border(attrs.borderWidth, LineStyle.solid, attrs.colorTheme.checkedPrimaryBorderColor)
            boxShadow = when (attrs.shadowed) {
                true -> BoxShadow(0.px, 0.px, 0.5.em, NamedColor.lightgrey)
                false -> None.none
            }
            color = attrs.colorTheme.checkedPrimaryTickColor
            hover {
                backgroundColor = attrs.colorTheme.checkedHoverBackgroundColor
                borderColor = attrs.colorTheme.checkedHoverBorderColor
                color = attrs.colorTheme.checkedHoverTickColor
            }
            active {
                backgroundColor = attrs.colorTheme.checkedActiveBackgroundColor
                borderColor = attrs.colorTheme.checkedActiveBorderColor
                color = attrs.colorTheme.checkedActiveTickColor
            }
        } else {
            backgroundColor = attrs.colorTheme.checkedDisabledBackgroundColor
            border = Border(attrs.borderWidth, LineStyle.solid, attrs.colorTheme.checkedDisabledBorderColor)
            color = attrs.colorTheme.checkedDisabledTickColor
        }
    }
}

val StyledCheckBox = FC<StyledCheckBoxProps> { props ->
    val inputId = Random.nextInt().toString()
    var checked by useState(false)
    var disabled by useState(false)
    useEffect {
        checked = document.getElementById(inputId).unsafeCast<HTMLInputElement>().checked
        disabled = document.getElementById(inputId).unsafeCast<HTMLInputElement>().disabled
    }
    div {
        input {
            id = inputId
            type = InputType.checkbox
            props.checkBoxInputBuilder(this)
            css {
                display = None.none
            }
        }
        label {
            htmlFor = inputId
            span {
                css(ClassName("fa-solid fa-check")) {
                    fontSize = 0.75.em
                    margin = Auto.auto
                    if (!checked) {
                        visibility = Visibility.hidden
                    }
                }
            }
            css {
                styledCheckBoxFieldCSS(props, checked, disabled)
                props.checkBoxFieldStyles(this)
            }
        }
        label {
            htmlFor = inputId
            props.checkBoxLabelBuilder(this)
            css {
                userSelect = None.none
                props.checkBoxLabelStyles(this)
            }
        }
        css {
            alignItems = AlignItems.center
            display = Display.flex
            flexDirection = FlexDirection.row
        }
    }
}

fun ChildrenBuilder.styledCheckBox(propsBuilder: StyledCheckBoxProps.() -> Unit) {
    StyledCheckBox {
        defaultStyledCheckBoxProps()
        propsBuilder()
    }
}
