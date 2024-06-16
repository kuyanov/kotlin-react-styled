import csstype.PropertiesBuilder
import emotion.react.css
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
        backgroundImage =
            url("\"data:image/svg+xml;utf8,<svg fill='${attrs.colorTheme.primaryTextColor}' height='24' viewBox='0 0 24 24' width='24' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/><path d='M0 0h24v24H0z' fill='none'/></svg>\"")
        backgroundRepeat = BackgroundRepeat.noRepeat
        backgroundPositionX = 100.pct - 5.px
        backgroundPositionY = 50.pct - 1.px
        paddingRight = 1.0.em + 12.px
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
