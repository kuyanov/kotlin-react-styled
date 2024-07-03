import web.cssom.*

interface ButtonTheme {
    val primaryBackgroundColor: Color
    val primaryTextColor: Color
    val primaryBorderColor: Color
    val hoverBackgroundColor: Color
    val hoverTextColor: Color
    val hoverBorderColor: Color
    val activeBackgroundColor: Color
    val activeTextColor: Color
    val activeBorderColor: Color
    val disabledBackgroundColor: Color
    val disabledTextColor: Color
    val disabledBorderColor: Color
}

class TransparentButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = NamedColor.transparent
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(0, 0, 0, 0.05)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(0, 0, 0, 0.1)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = NamedColor.whitesmoke
    override val disabledTextColor = rgb(0, 0, 0, 0.2)
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}

class WhiteButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = NamedColor.white
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(0, 0, 0, 0.05)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(0, 0, 0, 0.1)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = NamedColor.whitesmoke
    override val disabledTextColor = rgb(0, 0, 0, 0.2)
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}

class LightBlueButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = NamedColor.lightblue
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(149, 208, 228)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(125, 188, 209)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = NamedColor.whitesmoke
    override val disabledTextColor = rgb(0, 0, 0, 0.2)
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}

class LightGreenButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = NamedColor.lightgreen
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(124, 228, 124)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(104, 214, 104)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = NamedColor.whitesmoke
    override val disabledTextColor = rgb(0, 0, 0, 0.2)
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}

class DarkGreenButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = rgb(48, 86, 88)
    override val primaryTextColor = NamedColor.white
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(65, 116, 119)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(48, 86, 88)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = rgb(190, 213, 210)
    override val disabledTextColor = NamedColor.black
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}

class YellowButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = rgb(252, 237, 124)
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(245, 224, 104)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(236, 212, 79)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = NamedColor.whitesmoke
    override val disabledTextColor = rgb(0, 0, 0, 0.2)
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}

class RedButtonTheme : ButtonTheme {
    override val primaryBackgroundColor = rgb(250, 120, 120)
    override val primaryTextColor = rgb(0, 0, 0, 0.8)
    override val primaryBorderColor = rgb(0, 0, 0, 0.15)
    override val hoverBackgroundColor = rgb(241, 101, 101)
    override val hoverTextColor = primaryTextColor
    override val hoverBorderColor = primaryBorderColor
    override val activeBackgroundColor = rgb(222, 87, 87)
    override val activeTextColor = primaryTextColor
    override val activeBorderColor = primaryBorderColor
    override val disabledBackgroundColor = NamedColor.whitesmoke
    override val disabledTextColor = rgb(0, 0, 0, 0.2)
    override val disabledBorderColor = rgb(0, 0, 0, 0.05)
}
