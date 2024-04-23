package com.winandronux.utils;

import com.winandronux.exceptions.CommandException;

import java.util.ArrayList;
import java.util.Arrays;

public class Command {

    private final String command;
    private final String[] arguments;

    public Command(String input) {
        var cmds = input.split(" ");

        this.command = cmds[0];
        this.arguments = parseArguments(cmds);
    }

//    public Command(String[] cmds) {
//        this.command = cmds[0];
//        this.arguments = parseArguments(cmds);
//    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }

    private String[] parseArguments(String[] args) {
        var result = new ArrayList<>(Arrays.asList(args).subList(1, args.length));

        return result.toArray(new String[0]);
    }

    public boolean checkConvSyntax() {

        if (this.arguments.length < 4) {
            throw new CommandException("Incomplete command");
        }

        if (!Utils.checkIfIsDouble(arguments[0]) || Double.parseDouble(arguments[0]) <= 0) {
            throw new CommandException("Invalid amount");
        }

        if (GlobalVars.currencies.get(arguments[1]) == null) {
            throw new CommandException("\"" + arguments[1] + "\" is not a valid base_currency");
        }

        if (!arguments[2].equals("to")) {
            throw new CommandException("expected \"to\", got \"" + arguments[2] + "\"");
        }

        for (var t : arguments[3].split(",")) {
            if (GlobalVars.currencies.get(t) == null) {
                throw new CommandException("\"" + t + "\" is not a valid target_currency");
            }
        }

        return true;
    }
}
