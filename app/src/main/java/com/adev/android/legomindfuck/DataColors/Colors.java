package com.adev.android.legomindfuck.DataColors;

public class Colors {

    private String[] colorTower = new String[4];

    private String[] colorChecked = new String[4];

    private int checked = 0;

    public String getTowerColor(int pos) {
        return colorTower[pos];
    }

    public void setColorTower(String color, int pos) {
        colorTower[pos] = color;
    }

    public void setColorChecked(String color) {
        colorChecked[checked++] = color;
    }

    public boolean colorMatch() {
        if (colorChecked.length != 3 || colorTower.length != 3) return false;
        boolean win = true;
        for (int i = 0; i < 4 && win; i++) {
            win = colorChecked[i].equals(colorTower[i]);
        }
        return win;
    }

    public void wipeColors() {
        checked = 0;
        colorChecked = new String[4];
    }

    public void wipeLastColor() {
        colorChecked[--checked] = "";
    }

}
