package com.btkAkademi.rentACar.business.enums;

public enum CarType {
    ECONOMY(1),
    BASE(2),
    PRESTIGE(3);

    private int id;

    CarType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
