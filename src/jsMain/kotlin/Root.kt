import emotion.react.*
import react.*
import react.dom.client.createRoot
import react.dom.html.ReactHTML.body
import react.dom.html.ReactHTML.html
import web.cssom.*
import web.dom.document

external interface RootPropsGeneric<RootBuilderType, StylesType> : Props {
    var rootBuilder: RootBuilderType
    var rootStyles: StylesType
    var bodyStyles: StylesType
    var htmlStyles: StylesType
}

typealias RootProps = RootPropsGeneric<ChildrenBuilderType, StylesType>

fun RootProps.defaultRootProps() {
    rootBuilder = {}
    rootStyles = {}
    bodyStyles = {}
    htmlStyles = {}
}

val Root = FC<RootProps> { props ->
    Global {
        styles {
            "*, *:before, *:after" {
                boxSizing = BoxSizing.borderBox
            }
            html {
                display = Display.flex
                minHeight = 100.pct
                props.htmlStyles(this)
            }
            body {
                display = Display.flex
                flex = number(1.0)
                flexDirection = FlexDirection.column
                margin = 0.px
                width = 100.vw
                props.bodyStyles(this)
            }
            "#root" {
                display = Display.flex
                flex = number(1.0)
                flexDirection = FlexDirection.column
                props.rootStyles(this)
            }
        }
    }
    props.rootBuilder(this)
}

fun root(propsBuilder: RootProps.() -> Unit) {
    val container = document.createElement("div")
    container.setAttribute("id", "root")
    document.body.appendChild(container)
    createRoot(container).render(Root.create {
        defaultRootProps()
        propsBuilder()
    })
}
