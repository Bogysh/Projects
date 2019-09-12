package View;

public enum Ship {
    BLUE("/View/Resources/playerShip1_blue.png", "/View/Resources/life_blue.png"),
    ORANGE("/View/Resources/playerShip1_orange.png", "/View/Resources/life_orange.png"),
    GREEN("/View/Resources/playerShip1_green.png", "/View/Resources/life_green.png"),
    RED("/View/Resources/playerShip1_red.png", "/View/Resources/life_red.png");

    String shipPath;
    String healthPath;

    private Ship(String shipPath, String healthPath) {
        this.shipPath = shipPath;
        this.healthPath = healthPath;
    }

    public String getShip() {
        return this.shipPath;
    }

    public String getHealth() {
        return this.healthPath;
    }
}
