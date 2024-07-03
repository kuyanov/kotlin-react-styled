import web.cssom.*

interface InputTheme {
    val primaryBackgroundColor: Color
    val primaryTextColor: Color
    val primaryBorderColor: Color
    val hoverBackgroundColor: Color
    val hoverTextColor: Color
    val hoverBorderColor: Color
    val focusBackgroundColor: Color
    val focusTextColor: Color
    val focusBorderColor: Color
    val disabledBackgroundColor: Color
    val disabledTextColor: Color
    val disabledBorderColor: Color
}

class TransparentDefaultInputTheme : InputTheme {
    override val primaryBackgroundColor = NamedColor.transparent
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.4)
    override val hoverBackgroundColor = primaryBackgroundColor
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val focusBackgroundColor = primaryBackgroundColor
    override val focusTextColor = primaryTextColor
    override val focusBorderColor = rgb(0, 0, 0, 0.6)
    override val disabledBackgroundColor = primaryBackgroundColor
    override val disabledTextColor = rgb(0, 0, 0, 0.4)
    override val disabledBorderColor = rgb(0, 0, 0, 0.2)
}

class TransparentBlueInputTheme : InputTheme {
    override val primaryBackgroundColor = NamedColor.transparent
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.4)
    override val hoverBackgroundColor = primaryBackgroundColor
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val focusBackgroundColor = primaryBackgroundColor
    override val focusTextColor = primaryTextColor
    override val focusBorderColor = NamedColor.lightblue
    override val disabledBackgroundColor = primaryBackgroundColor
    override val disabledTextColor = rgb(0, 0, 0, 0.4)
    override val disabledBorderColor = rgb(0, 0, 0, 0.2)
}
