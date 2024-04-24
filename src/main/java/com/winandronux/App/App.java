package com.winandronux.App;

import com.winandronux.utils.Command;
import com.winandronux.utils.GlobalVars;

import java.util.Scanner;

public class App {

    private final static Scanner sc = new Scanner(System.in);

    public static void init () {

        System.out.println("""
                ********************************************
                *               CurrencyConv               *
                *                                          *
                *   Challenge: Conversor de monedas        *
                *   Author: Luis Daniel Salazar            *
                ********************************************
                
                Usage: conv [amount] [base_currency] to [targets_currency]
                
                To display the supported currencies type “supported”
                
                Examples:
                
                conv 1 USD to MXN
                conv 1 USD to ARS,COP,CLP,PEN
                
                For more info type "help" or "?"
                """);

        var ctrl = true;

        while (ctrl) {
            var input = getInput();
            var cmd = new Command(input);

            switch (cmd.getCommand()) {
                case "conv":
                    try {
                        if (cmd.checkConvSyntax()) {
                            var args = cmd.getArguments();
                            var baseCurrency = GlobalVars.currencies.get(args[1]);
                            var results = baseCurrency.convert(Double.parseDouble(args[0]), args[3]);
                            for (var result : results) {
                                System.out.println(result);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "supported":
                    for (var currency : GlobalVars.currencies.values()) {
                        System.out.println(currency.getCode() + ": " + currency.getName());
                    }
                    break;
                case "search":
                    var query = String.join(" ", cmd.getArguments());
                    for (var currency : GlobalVars.currencies.values()) {
                        if (currency.getName().toLowerCase().contains(query.toLowerCase())) {
                            System.out.println(currency.getCode() + ": " + currency.getName());
                        }
                    }
                    break;
                case "help", "?":
                    System.out.println("""
                                conv            Currency Converter
                                                Usage: conv [amount] [base_currency] to [targets_currency]
                                                    amount: positive number
                                                    base_currency: supported code currency
                                                    targets_currency: supported code currency or list of supported
                                                                      currencies separated by comma ","
                                supported       Shows a list of available currencies
                                search          Return all currencies matching the query
                                                Usage: search [query]
                                                    Examples:
                                                        search Peso -> Returns all currencies named "pesos"
                                                        search Peruvian -> Returns the currency used in Peru
                                                    Tip: For country names use their demonym
                                                        Mexico -> Mexican
                                                        Argentina -> Argentine
                                version         Shows the program version
                                clear           Cleans the screen
                                quit, q         Exit the program
                                help, ?         Shows this help message""");
                    break;
                case "version":
                    System.out.println("Program Version: 1.1");
                    break;
                case "clear":
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
                case "quit", "q":
                    ctrl = false;
                    break;
                case "":
                    break;
                default:
                    System.out.println("Unknown command: \"" + cmd.getCommand() + "\" type help or ? for help");
                    break;
            }
        }
    }

    private static String getInput() {
        System.out.print("> ");
        return sc.nextLine();
    }
}
