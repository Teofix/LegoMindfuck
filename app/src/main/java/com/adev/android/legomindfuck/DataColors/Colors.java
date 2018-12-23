package com.adev.android.legomindfuck.DataColors;

public class Colors {

    private String[] colorTower = new String[4];

    private String[] colorChecked = new String[4];

    private int checked = 0;

    private int players = 1;

    public String getTowerColor(int pos) {
        return colorTower[pos];
    }

    public void setColorTower(String color, int pos) {
        colorTower[pos] = color;
    }

    public void setColorChecked(String color) {
        if (checked >= 4) return;
        colorChecked[checked++] = color;
    }

    public boolean colorMatch() {
        boolean win = true;
        for (int i = 0; i < 4 && win; i++) {
            if (colorChecked[i] == null || colorTower[i] == null) return false;
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

    public int getPlayers() { return players; }

    public void setPlayers(int p) { players = p; }

}
