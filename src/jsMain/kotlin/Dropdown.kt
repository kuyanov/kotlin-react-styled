import csstype.PropertiesBuilder
import emotion.react.css
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import web.cssom.*
import web.dom.document
import web.events.*
import web.html.HTMLDivElement

enum class DropdownMenuAlignment {
    TopLeft,
    TopRight,
    BottomLeft,
    BottomRight,
}

external interface DropdownAttrs : StyledButtonAttrs {
    var menuAlignment: DropdownMenuAlignment
    var showArrow: Boolean
}

fun DropdownAttrs.defaultDropdownAttrs() {
    defaultStyledButtonAttrs()
    menuAlignment = DropdownMenuAlignment.BottomRight
    showArrow = true
}

external interface DropdownPropsGeneric<
        ButtonBuilderType, ButtonStylesType, MenuBuilderType, MenuStylesType> :
    DropdownAttrs, Props {
    var buttonBuilder: ButtonBuilderType
    var buttonStyles: ButtonStylesType
    var menuBuilder: MenuBuilderType
    var menuStyles: MenuStylesType
}

typealias MenuBuilderType = HTMLAttributes<HTMLDivElement>.() -> Unit
typealias MenuStylesType = PropertiesBuilder.() -> Unit
typealias DropdownProps = DropdownPropsGeneric<
        ButtonBuilderType, ButtonStylesType, MenuBuilderType, MenuStylesType>

fun DropdownProps.defaultDropdownProps() {
    defaultDropdownAttrs()
    buttonBuilder = {}
    buttonStyles = {}
    menuBuilder = {}
    menuStyles = {}
}

fun PropertiesBuilder.dropdownButtonCSS(attrs: DropdownAttrs, menuShown: Boolean) {
    styledButtonCSS(attrs)
    appearance = None.none
    if (attrs.showArrow) {
        val arrowDown = url("\"data:image/svg+xml;utf8,<svg fill='${attrs.colorTheme.primaryTextColor}' height='24' viewBox='0 0 24 24' width='24' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/><path d='M0 0h24v24H0z' fill='none'/></svg>\"")
        val arrowUp = url("\"data:image/svg+xml;utf8,<svg fill='${attrs.colorTheme.primaryTextColor}' height='24' viewBox='0 0 24 24' width='24' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z' transform='rotate(180 12 12)'/><path d='M0 0h24v24H0z' fill='none'/></svg>\"")
        backgroundRepeat = BackgroundRepeat.noRepeat
        when (attrs.menuAlignment) {
            DropdownMenuAlignment.TopLeft -> {
                backgroundImage = when (menuShown) {
                    false -> arrowUp
                    true -> arrowDown
                }
                backgroundPositionX = 5.px
                backgroundPositionY = 50.pct - 1.px
                paddingLeft = 1.0.em + 12.px
            }
            DropdownMenuAlignment.TopRight -> {
                backgroundImage = when (menuShown) {
                    false -> arrowUp
                    true -> arrowDown
                }
                backgroundPositionX = 100.pct - 5.px
                backgroundPositionY = 50.pct - 1.px
                paddingRight = 1.0.em + 12.px
            }
            DropdownMenuAlignment.BottomLeft -> {
                backgroundImage = when (menuShown) {
                    false -> arrowDown
                    true -> arrowUp
                }
                backgroundPositionX = 5.px
                backgroundPositionY = 50.pct - 1.px
                paddingLeft = 1.0.em + 12.px
            }
            DropdownMenuAlignment.BottomRight -> {
                backgroundImage = when (menuShown) {
                    false -> arrowDown
                    true -> arrowUp
                }
                backgroundPositionX = 100.pct - 5.px
                backgroundPositionY = 50.pct - 1.px
                paddingRight = 1.0.em + 12.px
            }
        }
    }
}

fun PropertiesBuilder.dropdownMenuCSS(attrs: DropdownAttrs) {
    display = Display.flex
    flexDirection = FlexDirection.column
    position = Position.absolute
    when (attrs.menuAlignment) {
        DropdownMenuAlignment.TopLeft -> {
            bottom = 100.pct
            left = 0.pct
        }
        DropdownMenuAlignment.TopRight -> {
            bottom = 100.pct
            right = 0.pct
        }
        DropdownMenuAlignment.BottomLeft -> {
            top = 100.pct
            left = 0.pct
        }
        DropdownMenuAlignment.BottomRight -> {
            top = 100.pct
            right = 0.pct
        }
    }
}

val Dropdown = FC<DropdownProps> { props ->
    var menuShown by useState(false)
    useEffectOnce {
        document.addEventListener(EventType("click"), { menuShown = false })
    }
    div {
        button {
            onClick = {
                it.stopPropagation()
                menuShown = !menuShown
            }
            props.buttonBuilder(this)
            css {
                position = Position.relative
                dropdownButtonCSS(props, menuShown)
                props.buttonStyles(this)
            }
        }
        if (menuShown) {
            div {
                props.menuBuilder(this)
                css {
                    dropdownMenuCSS(props)
                    props.menuStyles(this)
                }
            }
        }
        css {
            display = Display.inlineBlock
            position = Position.relative
        }
    }
}

fun ChildrenBuilder.dropdown(propsBuilder: DropdownProps.() -> Unit) {
    Dropdown {
        defaultDropdownProps()
        propsBuilder()
    }
}
