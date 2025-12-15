package Stakan;

public class Glass {
    private final int glassVolumeML;
    private int waterInGlass = 0;

    public Glass(int glassVolumeML) {
        this.glassVolumeML = glassVolumeML;
    }

    public int getGlassVolumeML() {
        return glassVolumeML;
    }

    public int getWaterInGlass() {
        return waterInGlass;
    }

    public int getFreeSpace() {
        return glassVolumeML - waterInGlass;
    }

    public boolean isFull() {
        return waterInGlass >= glassVolumeML;
    }

    public boolean isEmpty() {
        return waterInGlass <= 0;
    }

    public void addWaterML(int ml) {
        // check the valid amount of adding water
        if (ml <= 0) {
            return;
        }
        // valid adding
        waterInGlass += ml;
        // prevent spill
        if (waterInGlass >= glassVolumeML) waterInGlass = glassVolumeML;
    }

    public void removeWaterML(int ml) {
        // check tha valid amount of deleting water
        if (ml <= 0) {
            return;
        }
        // check the empty
        if (waterInGlass <= 0) {
            return;
        }
        // correct deleting
        waterInGlass -= ml;
        // prevent delete more than have
        if (waterInGlass < 0) {
            waterInGlass = 0;
        }
    }
}