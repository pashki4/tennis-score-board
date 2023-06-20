package com.vyshniakov;

import com.vyshniakov.service.GamePoints;

public class Main {

    public static void main(String[] args) {
        GamePoints fifteen = GamePoints.valueOf("fifteen".toUpperCase());
        System.out.println(fifteen);
    }
}
