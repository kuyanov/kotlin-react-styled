import csstype.PropertiesBuilder
import react.ChildrenBuilder
import react.dom.html.*
import web.html.*

typealias ButtonBuilderType = ButtonHTMLAttributes<HTMLButtonElement>.() -> Unit
typealias ChildrenBuilderType = ChildrenBuilder.() -> Unit
typealias DivBuilderType = HTMLAttributes<HTMLDivElement>.() -> Unit
typealias InputBuilderType = InputHTMLAttributes<HTMLInputElement>.() -> Unit
typealias LabelBuilderType = LabelHTMLAttributes<HTMLLabelElement>.() -> Unit
typealias SelectBuilderType = SelectHTMLAttributes<HTMLSelectElement>.() -> Unit
typealias StylesType = PropertiesBuilder.() -> Unit
typealias TextAreaBuilderType = TextareaHTMLAttributes<HTMLTextAreaElement>.() -> Unit
