import csstype.PropertiesBuilder
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.button
import web.cssom.*

external interface StyledButtonAttrs {
    var borderRadius: Length
    var borderWidth: Length
    var colorTheme: ButtonTheme
    var cursor: Cursor
    var shadowed: Boolean
}

fun StyledButtonAttrs.defaultStyledButtonAttrs() {
    borderRadius = 1.25.em
    borderWidth = 0.px
    colorTheme = TransparentButtonTheme()
    cursor = Cursor.default
    shadowed = false
}

external interface StyledButtonPropsGeneric<ButtonBuilderType, StylesType> :
    StyledButtonAttrs, Props {
    var buttonBuilder: ButtonBuilderType
    var buttonStyles: StylesType
}

typealias StyledButtonProps = StyledButtonPropsGeneric<ButtonBuilderType, StylesType>

fun StyledButtonProps.defaultStyledButtonProps() {
    defaultStyledButtonAttrs()
    buttonBuilder = {}
    buttonStyles = {}
}

fun PropertiesBuilder.styledButtonCSS(attrs: StyledButtonAttrs) {
    backgroundColor = attrs.colorTheme.primaryBackgroundColor
    border = Border(attrs.borderWidth, LineStyle.solid, attrs.colorTheme.primaryBorderColor)
    borderRadius = attrs.borderRadius
    boxShadow = None.none
    color = attrs.colorTheme.primaryTextColor
    display = Display.inlineBlock
    font = Globals.inherit
    lineHeight = 1.5.em
    margin = 0.em
    outline = None.none
    padding = Padding(0.5.em, 1.em)
    textDecoration = None.none
    hover {
        not("[disabled]") {
            backgroundColor = attrs.colorTheme.hoverBackgroundColor
            borderColor = attrs.colorTheme.hoverBorderColor
            boxShadow = when (attrs.shadowed) {
                true -> BoxShadow(0.px, 2.px, 0.3.em, NamedColor.lightgrey)
                false -> None.none
            }
            color = attrs.colorTheme.hoverTextColor
            cursor = attrs.cursor
        }
    }
    active {
        not("[disabled]") {
            backgroundColor = attrs.colorTheme.activeBackgroundColor
            borderColor = attrs.colorTheme.activeBorderColor
            boxShadow = when (attrs.shadowed) {
                true -> BoxShadow(0.px, 2.px, 0.3.em, NamedColor.lightgrey)
                false -> None.none
            }
            color = attrs.colorTheme.activeTextColor
        }
    }
    disabled {
        backgroundColor = attrs.colorTheme.disabledBackgroundColor
        borderColor = attrs.colorTheme.disabledBorderColor
        boxShadow = None.none
        color = attrs.colorTheme.disabledTextColor
    }
}

val StyledButton = FC<StyledButtonProps> { props ->
    button {
        props.buttonBuilder(this)
        css {
            styledButtonCSS(props)
            props.buttonStyles(this)
        }
    }
}

fun ChildrenBuilder.styledButton(propsBuilder: StyledButtonProps.() -> Unit) {
    StyledButton {
        defaultStyledButtonProps()
        propsBuilder()
    }
}
