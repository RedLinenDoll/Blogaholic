function applyThemeColor(themeColor) {
    document.documentElement.style.setProperty('--theme-color', themeColor);
    document.documentElement.style.setProperty('--heavy-color', colorLuminanceVariance(themeColor, -0.4))

}

function colorLuminanceVariance(colorHexCode, luminanceVariation) {

    // validate colorHexCode string
    colorHexCode = String(colorHexCode).replace(/[^0-9a-f]/gi, '');
    if (colorHexCode.length < 6) {
        colorHexCode = colorHexCode[0]+colorHexCode[0]+colorHexCode[1]+colorHexCode[1]+colorHexCode[2]+colorHexCode[2];
    }
    luminanceVariation = luminanceVariation || 0;
    // convert to decimal and change luminosity
    let newColorHexCode = "#", singleColor, colorPos;
    for (colorPos = 0; colorPos < 3; colorPos++) {
        singleColor = parseInt(colorHexCode.substr(colorPos*2,2), 16);
        singleColor = Math.round(Math.min(Math.max(0, singleColor + (singleColor * luminanceVariation)), 255)).toString(16);
        newColorHexCode += ("00"+singleColor).substr(singleColor.length);
    }

    return newColorHexCode;
}

