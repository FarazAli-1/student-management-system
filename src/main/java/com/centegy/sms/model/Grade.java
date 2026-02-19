package com.centegy.sms.model;

public enum Grade {
    A_PLUS(4.0),
    A(4.0),
    A_MINUS(3.8),
    B_PLUS(3.4),
    B(3.0),
    B_MINUS(2.8),
    C_PLUS(2.4),
    C(2.0),
    C_MINUS(1.8),
    D_PLUS(1.4),
    D(1.0),
    FAILS(0.0),
    N_A(0.0);

    private final double points;

    Grade(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }
}