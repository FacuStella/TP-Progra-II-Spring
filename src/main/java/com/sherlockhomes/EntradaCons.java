package com.sherlockhomes;

import java.util.Scanner;

public class EntradaCons { 
    
    private static final Scanner sc = new Scanner(System.in);

    public static int ingresaInt(){
        String input;
        int opcion = -1;
        
        input = sc.nextLine();

        if (input.matches("\\d+")) {
            opcion = Integer.parseInt(input);
        }   
        
        return opcion;
    }

    public static String ingresaString() {
        return sc.nextLine();
    }

}