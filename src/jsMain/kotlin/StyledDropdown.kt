import csstype.PropertiesBuilder
import emotion.react.css
import js.uri.encodeURIComponent
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import web.cssom.*
import web.dom.document
import web.events.*
import web.html.HTMLDivElement

enum class StyledDropdownContentAlignment {
    TopLeft,
    TopRight,
    BottomLeft,
    BottomRight,
}

external interface StyledDropdownAttrs : StyledButtonAttrs {
    var contentAlignment: StyledDropdownContentAlignment
    var showArrow: Boolean
}

fun StyledDropdownAttrs.defaultDropdownAttrs() {
    defaultStyledButtonAttrs()
    contentAlignment = StyledDropdownContentAlignment.BottomRight
    showArrow = true
}

external interface StyledDropdownPropsGeneric<
        ButtonBuilderType, ButtonStylesType, ContentBuilderType, ContentStylesType> :
    StyledDropdownAttrs, Props {
    var buttonBuilder: ButtonBuilderType
    var buttonStyles: ButtonStylesType
    var contentBuilder: ContentBuilderType
    var contentStyles: ContentStylesType
}

typealias DropdownContentBuilderType = HTMLAttributes<HTMLDivElement>.() -> Unit
typealias DropdownContentStylesType = PropertiesBuilder.() -> Unit
typealias StyledDropdownProps = StyledDropdownPropsGeneric<
        ButtonBuilderType, ButtonStylesType, DropdownContentBuilderType, DropdownContentStylesType>

fun StyledDropdownProps.defaultStyledDropdownProps() {
    defaultDropdownAttrs()
    buttonBuilder = {}
    buttonStyles = {}
    contentBuilder = {}
    contentStyles = {}
}

fun PropertiesBuilder.styledDropdownButtonCSS(attrs: StyledDropdownAttrs, contentShown: Boolean) {
    styledButtonCSS(attrs)
    appearance = None.none
    if (attrs.showArrow) {
        val arrowDown = url(
            "\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' " +
                    "fill='${encodeURIComponent(attrs.colorTheme.primaryTextColor.toString())}' " +
                    "viewBox='0 0 512 512'>" +
                    "<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->" +
                    "<path d='M233.4 406.6c12.5 12.5 32.8 12.5 45.3 0l192-192c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L256 338.7 86.6 169.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3l192 192z'/>" +
                    "</svg>\""
        )
        val arrowUp = url(
            "\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' " +
                    "fill='${encodeURIComponent(attrs.colorTheme.primaryTextColor.toString())}' " +
                    "viewBox='0 0 512 512'>" +
                    "<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->" +
                    "<path d='M233.4 105.4c12.5-12.5 32.8-12.5 45.3 0l192 192c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L256 173.3 86.6 342.6c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3l192-192z'/>" +
                    "</svg>\""
        )
        backgroundRepeat = BackgroundRepeat.noRepeat
        backgroundSize = 0.75.em
        when (attrs.contentAlignment) {
            StyledDropdownContentAlignment.TopLeft -> {
                backgroundImage = when (contentShown) {
                    false -> arrowUp
                    true -> arrowDown
                }
                backgroundPositionX = 0.75.em
                backgroundPositionY = 50.pct - 0.05.em
                paddingLeft = 2.0.em
            }

            StyledDropdownContentAlignment.TopRight -> {
                backgroundImage = when (contentShown) {
                    false -> arrowUp
                    true -> arrowDown
                }
                backgroundPositionX = 100.pct - 0.75.em
                backgroundPositionY = 50.pct - 0.05.em
                paddingRight = 2.0.em
            }

            StyledDropdownContentAlignment.BottomLeft -> {
                backgroundImage = when (contentShown) {
                    false -> arrowDown
                    true -> arrowUp
                }
                backgroundPositionX = 0.75.em
                backgroundPositionY = 50.pct - 0.05.em
                paddingLeft = 2.0.em
            }

            StyledDropdownContentAlignment.BottomRight -> {
                backgroundImage = when (contentShown) {
                    false -> arrowDown
                    true -> arrowUp
                }
                backgroundPositionX = 100.pct - 0.75.em
                backgroundPositionY = 50.pct - 0.05.em
                paddingRight = 2.0.em
            }
        }
    }
}

fun PropertiesBuilder.styledDropdownContentCSS(attrs: StyledDropdownAttrs) {
    display = Display.flex
    flexDirection = FlexDirection.column
    position = Position.absolute
    when (attrs.contentAlignment) {
        StyledDropdownContentAlignment.TopLeft -> {
            bottom = 100.pct
            left = 0.pct
        }

        StyledDropdownContentAlignment.TopRight -> {
            bottom = 100.pct
            right = 0.pct
        }

        StyledDropdownContentAlignment.BottomLeft -> {
            top = 100.pct
            left = 0.pct
        }

        StyledDropdownContentAlignment.BottomRight -> {
            top = 100.pct
            right = 0.pct
        }
    }
}

val StyledDropdown = FC<StyledDropdownProps> { props ->
    var contentShown by useState(false)
    useEffectOnce {
        document.addEventListener(EventType("click"), { contentShown = false })
    }
    div {
        button {
            onClick = {
                it.stopPropagation()
                contentShown = !contentShown
            }
            props.buttonBuilder(this)
            css {
                position = Position.relative
                styledDropdownButtonCSS(props, contentShown)
                props.buttonStyles(this)
            }
        }
        if (contentShown) {
            div {
                props.contentBuilder(this)
                css {
                    styledDropdownContentCSS(props)
                    props.contentStyles(this)
                }
            }
        }
        css {
            display = Display.inlineBlock
            position = Position.relative
        }
    }
}

fun ChildrenBuilder.styledDropdown(propsBuilder: StyledDropdownProps.() -> Unit) {
    StyledDropdown {
        defaultStyledDropdownProps()
        propsBuilder()
    }
}
