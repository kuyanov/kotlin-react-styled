import web.cssom.*

class TransparentDefaultInputTheme : ColorTheme {
    override val primaryBackgroundColor = Color("transparent")
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.4)
    override val hoverBackgroundColor = primaryBackgroundColor
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = primaryBackgroundColor
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = rgb(0, 0, 0, 0.6)
    override val disabledBackgroundColor = primaryBackgroundColor
    override val disabledTextColor = rgb(0, 0, 0, 0.4)
    override val disabledBorderColor = rgb(0, 0, 0, 0.2)
}

class TransparentBlueInputTheme : ColorTheme {
    override val primaryBackgroundColor = Color("transparent")
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.4)
    override val hoverBackgroundColor = primaryBackgroundColor
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = primaryBackgroundColor
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = Color("lightblue")
    override val disabledBackgroundColor = primaryBackgroundColor
    override val disabledTextColor = rgb(0, 0, 0, 0.4)
    override val disabledBorderColor = rgb(0, 0, 0, 0.2)
}
