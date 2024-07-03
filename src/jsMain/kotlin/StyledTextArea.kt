import csstype.PropertiesBuilder
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.textarea
import react.dom.html.TextareaHTMLAttributes
import web.cssom.*
import web.html.HTMLTextAreaElement

external interface StyledTextAreaAttrs {
    var borderRadius: Length
    var borderWidth: Length
    var colorTheme: InputTheme
}

fun StyledTextAreaAttrs.defaultStyledTextAreaAttrs() {
    borderRadius = 0.85.em
    borderWidth = 1.px
    colorTheme = TransparentDefaultInputTheme()
}

external interface StyledTextAreaPropsGeneric<TextAreaBuilderType, TextAreaStylesType> :
    StyledTextAreaAttrs, Props {
    var textAreaBuilder: TextAreaBuilderType
    var textAreaStyles: TextAreaStylesType
}

typealias TextAreaBuilderType = TextareaHTMLAttributes<HTMLTextAreaElement>.() -> Unit
typealias TextAreaStylesType = PropertiesBuilder.() -> Unit
typealias StyledTextAreaProps = StyledTextAreaPropsGeneric<TextAreaBuilderType, TextAreaStylesType>

fun StyledTextAreaProps.defaultStyledTextAreaProps() {
    defaultStyledTextAreaAttrs()
    textAreaBuilder = {}
    textAreaStyles = {}
}

fun PropertiesBuilder.styledTextAreaCSS(attrs: StyledTextAreaAttrs) {
    backgroundColor = attrs.colorTheme.primaryBackgroundColor
    border = Border(attrs.borderWidth, LineStyle.solid, attrs.colorTheme.primaryBorderColor)
    borderRadius = attrs.borderRadius
    boxShadow = None.none
    color = attrs.colorTheme.primaryTextColor
    display = Display.inlineBlock
    font = Globals.inherit
    margin = 0.px
    outline = None.none
    padding = Padding(0.5 * attrs.borderRadius, 0.6 * attrs.borderRadius)
    resize = None.none
    textDecoration = None.none
    hover {
        not("[focus,disabled]") {
            backgroundColor = attrs.colorTheme.hoverBackgroundColor
            borderColor = attrs.colorTheme.hoverBorderColor
            color = attrs.colorTheme.hoverTextColor
        }
    }
    focus {
        backgroundColor = attrs.colorTheme.focusBackgroundColor
        borderColor = attrs.colorTheme.focusBorderColor
        color = attrs.colorTheme.focusTextColor
    }
    disabled {
        backgroundColor = attrs.colorTheme.disabledBackgroundColor
        borderColor = attrs.colorTheme.disabledBorderColor
        color = attrs.colorTheme.disabledTextColor
    }
}

val StyledTextArea = FC<StyledTextAreaProps> { props ->
    textarea {
        props.textAreaBuilder(this)
        css {
            styledTextAreaCSS(props)
            props.textAreaStyles(this)
        }
    }
}

fun ChildrenBuilder.styledTextArea(propsBuilder: StyledTextAreaProps.() -> Unit) {
    StyledTextArea {
        defaultStyledTextAreaProps()
        propsBuilder()
    }
}
