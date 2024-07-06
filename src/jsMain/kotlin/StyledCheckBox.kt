import csstype.PropertiesBuilder
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.span
import web.cssom.*
import web.dom.document
import web.html.HTMLInputElement
import web.html.InputType
import kotlin.random.Random

external interface StyledCheckBoxAttrs {
    var colorTheme: CheckBoxTheme
    var borderRadius: Length
    var borderWidth: Length
    var inputId: String
    var shadowed: Boolean
}

fun StyledCheckBoxAttrs.defaultStyledCheckBoxAttrs() {
    colorTheme = TransparentCheckBoxTheme()
    borderRadius = 50.pct
    borderWidth = 1.px
    inputId = Random.nextInt().toString()
    shadowed = false
}

external interface StyledCheckBoxPropsGeneric<InputBuilderType, LabelBuilderType, StylesType>
    : StyledCheckBoxAttrs, Props {
    var inputBuilder: InputBuilderType
    var fieldStyles: StylesType
    var labelBuilder: LabelBuilderType
    var labelStyles: StylesType
    var wrapperStyles: StylesType
}

typealias StyledCheckBoxProps = StyledCheckBoxPropsGeneric<InputBuilderType, LabelBuilderType,
        StylesType>

fun StyledCheckBoxProps.defaultStyledCheckBoxProps() {
    defaultStyledCheckBoxAttrs()
    inputBuilder = {}
    fieldStyles = {}
    labelBuilder = {}
    labelStyles = {}
    wrapperStyles = {}
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
    margin = Margin(Auto.auto, 0.25.em, Auto.auto, 0.em)
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
    var checked by useState(false)
    var disabled by useState(false)
    useEffect {
        val element = document.getElementById(props.inputId).unsafeCast<HTMLInputElement>()
        checked = element.checked
        disabled = element.disabled
    }
    div {
        input {
            id = props.inputId
            type = InputType.checkbox
            props.inputBuilder(this)
            css {
                display = None.none
            }
        }
        label {
            htmlFor = props.inputId
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
                props.fieldStyles(this)
            }
        }
        label {
            htmlFor = props.inputId
            props.labelBuilder(this)
            css {
                userSelect = None.none
                props.labelStyles(this)
            }
        }
        css {
            alignItems = AlignItems.center
            display = Display.flex
            flexDirection = FlexDirection.row
            props.wrapperStyles(this)
        }
    }
}

fun ChildrenBuilder.styledCheckBox(propsBuilder: StyledCheckBoxProps.() -> Unit) {
    StyledCheckBox {
        defaultStyledCheckBoxProps()
        propsBuilder()
    }
}
