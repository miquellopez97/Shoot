package com.company;

import Models.Player;
import Models.Shoots;
import Models.Stats;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    final static String RED = "\033[31m", RESET = "\u001B[0m";
    static ArrayList<Player> Team = new ArrayList<>();
    static ArrayList<Stats> TeamStats = new ArrayList<>();
    static ArrayList<Integer> position1 = new ArrayList<>() , position2 = new ArrayList<>() , position3 = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        int numberPlayers, numberPositions, numberShoots;

        numberPlayers = numbersWithRange("Numero de jugadores: ", 1, 14);

        numberPositions = numbersWithRange("Numero de posiciones: ", 1, 25);

        numberShoots = numbersWithRange("Numero de tiros por posicion: ", 1, 20);

        doList(numberPositions);

        System.out.println("Ahora toca escribir el nombre de los jugadores");
        for (int i = 0; i < numberPlayers; i++) {
            AddPlayer(numberPositions, numberShoots);
        }

        System.out.println("Los jugadores se han anadido correctamente");

        entrieScore(numberShoots);

        doStats(numberPositions, numberShoots);

        showStats();

        printNormalStats();

        printStats();
    }

    public static void doList(int nP){
        System.out.println("Escribe las posiciones que son de 1pts");
        System.out.println("Para salir escribe " + (nP + 2));
        boolean flag = false;

        do {
            int position;
            position = numbersWithRange("Posicion: ", 1, (nP + 2));
            if (position!=(nP + 2)){
                position1.add(position-1);
            } else {
                flag=true;
            }
        }while (!flag);

        System.out.println("Escribe las posiciones que son de 2pts");
        System.out.println("Para salir escribe " + (nP + 2));
        flag = false;

        do {
            int position;
            position = numbersWithRange("Posicion: ", 1, (nP + 2));
            if (position!=(nP + 2)){
                position2.add(position-1);
            } else {
                flag=true;
            }
        }while (!flag);

        System.out.println("Escribe las posiciones que son de 3pts");
        System.out.println("Para salir escribe " + (nP + 2));
        flag = false;

        do {
            int position;
            position = numbersWithRange("Posicion: ", 1, (nP + 2));
            if (position!=(nP + 2)){
                position3.add(position-1);
            } else {
                flag=true;
            }
        }while (!flag);
    }

    public static void doStats(int nP, int nS){
        for (int i = 0; i < Team.size(); i++) {

            float totalAvg, oneAvg, twoAvg, threeAvg, trueAvg=0;
            int count=0, count1pts=0, count2pts=0, count3pts=0;

            for (int j = 0; j < Team.get(i).getPosition().size(); j++) {
                count += Team.get(i).getPosition().get(j).getSuccess();
                if (!position1.isEmpty()){
                    for (int k = 0; k < position1.size(); k++) {
                        if(j == position1.get(k)){
                            count1pts += Team.get(i).getPosition().get(j).getSuccess();
                        }
                    }
                }
                if (!position2.isEmpty()){
                    for (int k = 0; k < position2.size(); k++) {
                        if(j == position2.get(k)){
                            count2pts += Team.get(i).getPosition().get(j).getSuccess();
                        }
                    }
                }
                if (!position3.isEmpty()){
                    for (int k = 0; k < position3.size(); k++) {
                        if(j == position3.get(k)){
                            count3pts += Team.get(i).getPosition().get(j).getSuccess();
                        }
                    }
                }
            }

            totalAvg = (float)count/(nS*nP)*100;
            oneAvg = (float)count1pts/(nS* position1.size())*100;
            twoAvg = (float)count2pts/(nS* position2.size())*100;
            threeAvg = (float)count3pts/(nS* position3.size())*100;
            trueAvg = 100*(float)(count2pts + 1.5*count3pts)/(nS*nP);

            Stats x = new Stats(
                    Team.get(i).getName(),
                    totalAvg,
                    oneAvg,
                    twoAvg,
                    threeAvg,
                    trueAvg);
            TeamStats.add(x);
        }
    }

    public static void showStats(){
        System.out.println("Name     %TC     %1P     %2P     %3P     eFG%");
        for (Stats player : TeamStats) {
            System.out.println(player);
        }
    }

    public static void entrieScore(int nS){
        int positionP=0, success, postionS;
        String name;

        do {
            boolean flag=false;

            System.out.println("Escribe exit para salir");

            do {
                name = onlyString("Nombre: ");
                if (name.equalsIgnoreCase("exit")){
                    break;
                }
                for (int i = 0; i < Team.size(); i++) {
                    if (Team.get(i).getName().equalsIgnoreCase(name)) {
                        flag = true;
                        positionP=i;
                        break;
                    }
                }
                if (!flag){
                    System.out.println(RED + "ERROR: No se ha encontrado el jugador" + RESET);
                }
            }while (!flag);

            if (flag){
                postionS= numbersWithRange("Des de que posicion se ha realizado el tiro: ", 0, nS);

                success= numbersWithRange("Basquets anotados: ", 0, nS);

                Team.get(positionP).getPosition().get(postionS-1).setSuccess(success);

                printTeam();
            }
        }while (!name.equalsIgnoreCase("exit"));

    }

    public static void AddPlayer(int nP, int nS){
        String name;
        ArrayList<Shoots> shoots = new ArrayList<>();

        name = onlyString("Nombre: ");

        for (int i = 0; i < nP; i++) {
            Shoots exemple = new Shoots(0, nS);
            shoots.add(exemple);
        }

        Player newPlayer = new Player(name, shoots);
        Team.add(newPlayer);
    }

    public static String onlyString(String input){
        Scanner sc = new Scanner(System.in);
        String x;
        boolean correctValue=false;

        do {
            System.out.print(input);
            x=sc.nextLine();

            try {
                Integer.parseInt(x);
                System.out.println(RED + "Solo puedes escribir letras." + RESET);
            } catch (NumberFormatException excepcion) {
                correctValue=true;
            }
        }while(!correctValue);

        return  x;
    }

    public static int numbersWithRange(String input, int valorMin, int valorMax){
        Scanner sc = new Scanner(System.in);
        int x = 0;
        boolean correctValue;

        do{
            System.out.print(input);
            correctValue = sc.hasNextInt();

            if(!correctValue){
                System.out.println(RED+"ERROR: No has escrito un numero"+RESET);
                sc.nextLine();
            }else{
                x = sc.nextInt();
                sc.nextLine();

                if (x < valorMin || x > valorMax){
                    System.out.println(RED+"ERROR: El numero esta fuera del rango"+RESET);
                    correctValue = false;
                }
            }
        }while(!correctValue);
        return x;
    }

    public static void printTeam(){
        for (Player player : Team) {
            System.out.println(player);
        }
    }

    public static void printNormalStats() throws IOException {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        BufferedWriter br = new BufferedWriter(new FileWriter(date + ".csv"));
        StringBuilder sb = new StringBuilder();

        for (Player element : Team) {
            sb.append(element);
            sb.append(";");
        }

        br.write(sb.toString());
        br.close();
    }

    public static void printStats() throws IOException {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        BufferedWriter br = new BufferedWriter(new FileWriter(date + "Stats.csv"));
        StringBuilder sb = new StringBuilder();

        for (Stats element : TeamStats) {
            sb.append(element);
            sb.append(";");
        }

        br.write(sb.toString());
        br.close();
    }
}
