/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.utils;

import java.util.Random;

/**
 * Generates a random full name for a Patient. Makes it easier to read the
 * schedule and write/debug code for errors.
 *
 * @author user
 */
public class RandomNameGenerator {

    public static String[] firstNames = {
        "Simon",
        "Kareen",
        "Ursula",
        "Man",
        "Alishia",
        "Keiko",
        "Dolores",
        "Josh",
        "Stephanie",
        "Ricky",
        "Mary",
        "Benjamin",
        "Stan",
        "Dries",
        "Marky",
        "Jef",
        "Robbe",
        "Carl",
        "Ido",
        "Emiel",
        "Sofie",
        "Dorien",
        "Gregory",
        "Bram",
        "Wenke",
        "Edgar",
        "Eline",
        "Emily",
        "Jeffrey",
        "Arno",
        "Cedric",
        "Jordi",
        "Adolf",
        "Tony",
        "Frieda",
        "Kenzo",
        "Sem",
        "Robin",
        "Melanie",
        "Jonas",
        "Seppe",
        "Joost",
        "Miel",
        "Nathan",
        "Lensey",
        "Liane",
        "Nick",
        "Sigrid"
    };

    public static String[] lastNames = {
        "Dundas",
        "Kuder",
        "Vivier",
        "Havard",
        "Rayfield",
        "Tarrance",
        "Shapiro",
        "Lipe",
        "Rimmer",
        "Barbosa",
        "Jane",
        "Van Der Smissen",
        "Detré",
        "Serruys",
        "Geerts",
        "van Hulle",
        "Denayer",
        "Balcaen",
        "Evers",
        "Carels",
        "De Schrijver",
        "Erkelens",
        "Steurbaut",
        "Vanhaelen",
        "Geeraerts",
        "Deven",
        "Longin",
        "Coppens",
        "Rahoens",
        "Gits",
        "Huffel",
        "Anthoons",
        "Hitler",
        "Stark",
        "Zevernaam",
        "Keulen",
        "Rossekop",
        "Smismans",
        "Raes",
        "Vanderkelen",
        "Nerinckx",
        "Lories",
        "Cannaers",
        "Latijntje",
        "Clement",
        "Hauwaert",
        "Lodefier",
        "Hulsmans"
    };

    public static String generate() {
        Random r = new Random();
        int f = r.nextInt(firstNames.length);
        int l = r.nextInt(lastNames.length);

        return firstNames[f] + " " + lastNames[l];
    }

}
