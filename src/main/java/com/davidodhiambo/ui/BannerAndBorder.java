package com.davidodhiambo.ui;

public class BannerAndBorder {

    public static void banner(){
        System.out.println("""
                __________                         .__           /\\          __________                  __   \s
                \\______   \\  ____    ____  ______  |  |    ____  )/  ______  \\______   \\_____     ____  |  | __
                 |     ___/_/ __ \\  /  _ \\ \\____ \\ |  |  _/ __ \\    /  ___/   |    |  _/\\__  \\   /    \\ |  |/ /
                 |    |    \\  ___/ (  <_> )|  |_> >|  |__\\  ___/    \\___ \\    |    |   \\ / __ \\_|   |  \\|    <\s
                 |____|     \\___  > \\____/ |   __/ |____/ \\___  >  /____  >   |______  /(____  /|___|  /|__|_ \\
                                \\/         |__|               \\/        \\/           \\/      \\/      \\/      \\/""");
    }


    public static void border() {
        System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n*----------------------------------------*");
    }

    public static void animate(){
        for (int i = 0; i < 3; i++) {
            System.out.print(" > ");
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("system interrupted...");
        }

    }
}
