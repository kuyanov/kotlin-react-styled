import web.cssom.*

interface CheckBoxTheme {
    val blankPrimaryBackgroundColor: Color
    val blankPrimaryBorderColor: Color
    val blankHoverBackgroundColor: Color
    val blankHoverBorderColor: Color
    val blankActiveBackgroundColor: Color
    val blankActiveBorderColor: Color
    val blankDisabledBackgroundColor: Color
    val blankDisabledBorderColor: Color

    val checkedPrimaryBackgroundColor: Color
    val checkedPrimaryBorderColor: Color
    val checkedPrimaryTickColor: Color
    val checkedHoverBackgroundColor: Color
    val checkedHoverBorderColor: Color
    val checkedHoverTickColor: Color
    val checkedActiveBackgroundColor: Color
    val checkedActiveBorderColor: Color
    val checkedActiveTickColor: Color
    val checkedDisabledBackgroundColor: Color
    val checkedDisabledBorderColor: Color
    val checkedDisabledTickColor: Color
}

class TransparentCheckBoxTheme : CheckBoxTheme {
    override val blankPrimaryBackgroundColor = NamedColor.transparent
    override val blankPrimaryBorderColor = rgb(0, 0, 0, 0.15)
    override val blankHoverBackgroundColor = rgb(0, 0, 0, 0.05)
    override val blankHoverBorderColor = blankPrimaryBorderColor
    override val blankActiveBackgroundColor = rgb(0, 0, 0, 0.1)
    override val blankActiveBorderColor = blankPrimaryBorderColor
    override val blankDisabledBackgroundColor = NamedColor.whitesmoke
    override val blankDisabledBorderColor = rgb(0, 0, 0, 0.05)

    override val checkedPrimaryBackgroundColor = blankPrimaryBackgroundColor
    override val checkedPrimaryBorderColor = blankPrimaryBorderColor
    override val checkedPrimaryTickColor = rgb(0, 0, 0, 0.8)
    override val checkedHoverBackgroundColor = blankHoverBackgroundColor
    override val checkedHoverBorderColor = blankHoverBorderColor
    override val checkedHoverTickColor = checkedPrimaryTickColor
    override val checkedActiveBackgroundColor = blankActiveBackgroundColor
    override val checkedActiveBorderColor = blankActiveBorderColor
    override val checkedActiveTickColor = checkedPrimaryTickColor
    override val checkedDisabledBackgroundColor = blankDisabledBackgroundColor
    override val checkedDisabledBorderColor = blankDisabledBorderColor
    override val checkedDisabledTickColor = rgb(0, 0, 0, 0.2)
}

class LightBlueCheckBoxTheme : CheckBoxTheme {
    override val blankPrimaryBackgroundColor = NamedColor.transparent
    override val blankPrimaryBorderColor = rgb(0, 0, 0, 0.15)
    override val blankHoverBackgroundColor = rgb(0, 0, 0, 0.05)
    override val blankHoverBorderColor = blankPrimaryBorderColor
    override val blankActiveBackgroundColor = rgb(0, 0, 0, 0.1)
    override val blankActiveBorderColor = blankPrimaryBorderColor
    override val blankDisabledBackgroundColor = NamedColor.whitesmoke
    override val blankDisabledBorderColor = rgb(0, 0, 0, 0.05)

    override val checkedPrimaryBackgroundColor = rgb(65, 193, 225)
    override val checkedPrimaryBorderColor = NamedColor.transparent
    override val checkedPrimaryTickColor = NamedColor.white
    override val checkedHoverBackgroundColor = rgb(70, 180, 207)
    override val checkedHoverBorderColor = checkedPrimaryBorderColor
    override val checkedHoverTickColor = checkedPrimaryTickColor
    override val checkedActiveBackgroundColor = rgb(53, 160, 187)
    override val checkedActiveBorderColor = checkedPrimaryBorderColor
    override val checkedActiveTickColor = checkedPrimaryTickColor
    override val checkedDisabledBackgroundColor = rgb(0, 0, 0, 0.15)
    override val checkedDisabledBorderColor = NamedColor.transparent
    override val checkedDisabledTickColor = NamedColor.white
}

class LightGreenCheckBoxTheme : CheckBoxTheme {
    override val blankPrimaryBackgroundColor = NamedColor.transparent
    override val blankPrimaryBorderColor = rgb(0, 0, 0, 0.15)
    override val blankHoverBackgroundColor = rgb(0, 0, 0, 0.05)
    override val blankHoverBorderColor = blankPrimaryBorderColor
    override val blankActiveBackgroundColor = rgb(0, 0, 0, 0.1)
    override val blankActiveBorderColor = blankPrimaryBorderColor
    override val blankDisabledBackgroundColor = NamedColor.whitesmoke
    override val blankDisabledBorderColor = rgb(0, 0, 0, 0.05)

    override val checkedPrimaryBackgroundColor = rgb(104, 214, 104)
    override val checkedPrimaryBorderColor = NamedColor.transparent
    override val checkedPrimaryTickColor = NamedColor.white
    override val checkedHoverBackgroundColor = rgb(96, 194, 96)
    override val checkedHoverBorderColor = checkedPrimaryBorderColor
    override val checkedHoverTickColor = checkedPrimaryTickColor
    override val checkedActiveBackgroundColor = rgb(87, 175, 87)
    override val checkedActiveBorderColor = checkedPrimaryBorderColor
    override val checkedActiveTickColor = checkedPrimaryTickColor
    override val checkedDisabledBackgroundColor = rgb(0, 0, 0, 0.15)
    override val checkedDisabledBorderColor = NamedColor.transparent
    override val checkedDisabledTickColor = NamedColor.white
}
