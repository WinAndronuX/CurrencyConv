package com.winandronux.controller;

import java.util.Scanner;

public class InteractiveApp {

    private final static Scanner sc = new Scanner(System.in);

    public static void init () {

        var ctrl = true;

        while (ctrl) {

        }

    }

    public static String getInput(String text) {
        System.out.println(text);
        System.out.print("> ");
        return sc.nextLine();
    }
}
