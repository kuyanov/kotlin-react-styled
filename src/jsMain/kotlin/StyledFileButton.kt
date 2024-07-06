import csstype.PropertiesBuilder
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import web.cssom.*
import web.html.InputType
import kotlin.random.Random

external interface StyledFileButtonAttrs: StyledButtonAttrs {
    var inputId: String
}

fun StyledFileButtonAttrs.defaultStyledFileButtonAttrs() {
    defaultStyledButtonAttrs()
    inputId = Random.nextInt().toString()
}

external interface StyledFileButtonPropsGeneric<ButtonBuilderType, InputBuilderType, StylesType>
    : StyledFileButtonAttrs, Props {
    var buttonBuilder: ButtonBuilderType
    var buttonStyles: StylesType
    var inputBuilder: InputBuilderType
    var labelStyles: StylesType
    var wrapperStyles: StylesType
}

typealias StyledFileButtonProps = StyledFileButtonPropsGeneric<LabelBuilderType, InputBuilderType,
        StylesType>

fun StyledFileButtonProps.defaultStyledFileButtonProps() {
    defaultStyledFileButtonAttrs()
    buttonBuilder = {}
    buttonStyles = {}
    inputBuilder = {}
    labelStyles = {}
    wrapperStyles = {}
}

fun PropertiesBuilder.styledFileButtonCSS(attrs: StyledFileButtonAttrs) {
    styledButtonCSS(attrs)
}

val StyledFileButton = FC<StyledFileButtonProps> { props ->
    var filename: String? by useState(null)
    div {
        input {
            id = props.inputId
            type = InputType.file
            onChange = {
                filename = it.target.files?.item(0)?.name
            }
            props.inputBuilder(this)
            css {
                display = None.none
            }
        }
        label {
            htmlFor = props.inputId
            props.buttonBuilder(this)
            css {
                styledFileButtonCSS(props)
                props.buttonStyles(this)
            }
        }
        label {
            htmlFor = props.inputId
            +(filename ?: "No file chosen")
            css {
                marginLeft = 0.5.em
                overflow = Overflow.hidden
                textOverflow = TextOverflow.ellipsis
                userSelect = None.none
                whiteSpace = WhiteSpace.nowrap
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

fun ChildrenBuilder.styledFileButton(propsBuilder: StyledFileButtonProps.() -> Unit) {
    StyledFileButton {
        defaultStyledFileButtonProps()
        propsBuilder()
    }
}
