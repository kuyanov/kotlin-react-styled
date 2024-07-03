import csstype.PropertiesBuilder
import emotion.react.css
import js.uri.encodeURIComponent
import react.*
import react.dom.html.ReactHTML.select
import react.dom.html.SelectHTMLAttributes
import web.cssom.*
import web.html.HTMLSelectElement

external interface StyledSelectAttrs : StyledButtonAttrs {
    var showArrow: Boolean
}

fun StyledSelectAttrs.defaultStyledSelectAttrs() {
    defaultStyledButtonAttrs()
    showArrow = true
}

external interface StyledSelectPropsGeneric<SelectBuilderType, SelectStylesType> :
    StyledSelectAttrs, Props {
    var selectBuilder: SelectBuilderType
    var selectStyles: SelectStylesType
}

typealias SelectBuilderType = SelectHTMLAttributes<HTMLSelectElement>.() -> Unit
typealias SelectStylesType = PropertiesBuilder.() -> Unit
typealias StyledSelectProps = StyledSelectPropsGeneric<SelectBuilderType, SelectStylesType>

fun StyledSelectProps.defaultStyledSelectProps() {
    defaultStyledSelectAttrs()
    selectBuilder = {}
    selectStyles = {}
}

fun PropertiesBuilder.styledSelectCSS(attrs: StyledSelectAttrs) {
    styledButtonCSS(attrs)
    appearance = None.none
    if (attrs.showArrow) {
        backgroundImage = url(
            "\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' " +
                    "fill='${encodeURIComponent(attrs.colorTheme.primaryTextColor.toString())}' " +
                    "viewBox='0 0 512 512'>" +
                    "<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->" +
                    "<path d='M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z'/>" +
                    "</svg>\""
        )
        backgroundRepeat = BackgroundRepeat.noRepeat
        backgroundSize = 0.75.em
        backgroundPositionX = 100.pct - 0.75.em
        backgroundPositionY = 50.pct - 0.05.em
        paddingRight = 2.0.em
    }
}

val StyledSelect = FC<StyledSelectProps> { props ->
    select {
        props.selectBuilder(this)
        css {
            styledSelectCSS(props)
            props.selectStyles(this)
        }
    }
}

fun ChildrenBuilder.styledSelect(propsBuilder: StyledSelectProps.() -> Unit) {
    StyledSelect {
        defaultStyledSelectProps()
        propsBuilder()
    }
}
