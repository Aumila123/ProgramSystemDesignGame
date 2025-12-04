package com.example.miestomeras.game;

import com.example.miestomeras.model.*;
import com.example.miestomeras.event.EventManager;
import com.example.miestomeras.factory.BuildingFactory;
import com.example.miestomeras.factory.BuildingFactory.BuildingType;
import com.example.miestomeras.strategy.*;
import java.util.Scanner;

public class Game {
    private final City city;
    private final Scanner scanner;
    private boolean gameRunning;
    private final EventManager eventManager;

    public Game() {
        this.city = new City();
        this.scanner = new Scanner(System.in);
        this.gameRunning = true;
        this.eventManager = new EventManager();
    }

    public void start() {
        System.out.println("=================================");
        System.out.println("  WELCOME TO CITY MAYOR GAME!");
        System.out.println("=================================");
        System.out.println("Goal: Survive " + City.WIN_TURNS + " turns without going bankrupt");
        System.out.println("or losing citizen happiness!\n");

        while (gameRunning) {
            city.displayStatus();
            if (city.checkLose()) {
                handleLose();
                break;
            }
            if (city.checkWin()) {
                handleWin();
                break;
            }
            showMenu();
            int choice = getPlayerChoice();
            processChoice(choice);
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n--- ACTIONS ---");
        System.out.println("1. Build Hospital (€1,500,000)");
        System.out.println("2. Build School (€1,000,000)");
        System.out.println("3. Build Police Station (€800,000)");
        System.out.println("4. Build Park (€300,000)");
        System.out.println("5. Build Homes (€500,000)");
        System.out.println("6. Build Market (€600,000)");
        System.out.println("7. Tax Options");        System.out.println("8. Decrease Expenses (Save €200,000, Happiness -5%, Safety -5%)");
        System.out.println("9. End Turn (Do nothing)");
        System.out.println("0. Quit Game");
        System.out.print("\nYour choice: ");
    }

    private int getPlayerChoice() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void processChoice(int choice) {
        final BuildingType buildingType;
        switch (choice) {
            case 1: buildingType = BuildingType.HOSPITAL; break;
            case 2: buildingType = BuildingType.SCHOOL; break;
            case 3: buildingType = BuildingType.POLICE_STATION; break;
            case 4: buildingType = BuildingType.PARK; break;
            case 5: buildingType = BuildingType.PERSONS_HOME; break;
            case 6: buildingType = BuildingType.MARKET; break;
            case 7:
                showTaxMenu();
                return;
            case 8:
                city.decreaseExpenses();
                System.out.println("\n✓ Expenses decreased! Services reduced.");
                endTurnWithEvents();
                return;
            case 9:
                System.out.println("\n✓ Turn ended. Time passes...");
                endTurnWithEvents();
                return;
            case 0:
                System.out.println("\nThanks for playing!");
                gameRunning = false;
                return;
            default:
                System.out.println("\n✗ Invalid choice! Try again.");
                return;
        }
        buildBuilding(buildingType);
    }

    private void buildBuilding(BuildingType buildingType) {
        Building building = BuildingFactory.createBuilding(buildingType);
        if (city.addBuilding(building)) {
            System.out.println("\n✓ " + building.getName() + " built successfully!");
            System.out.println("Effect: " + building.getDescription());
            endTurnWithEvents();
        } else {
            System.out.println("\n✗ Not enough budget to build " + building.getName());
        }
    }

    private void endTurnWithEvents() {
        city.endTurn();
        String eventMessage = eventManager.triggerEvent(city);
        if (eventMessage != null) {
            System.out.println("\n" + eventMessage);
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void showTaxMenu() {
        TaxStrategy lowTax = new LowTaxStrategy();
        TaxStrategy normalTax = new NormalTaxStrategy();
        TaxStrategy aggressiveTax = new AggressiveTaxStrategy();

        displayTaxOptions(lowTax, normalTax, aggressiveTax);
        int taxChoice = getPlayerChoice();
        processTaxChoice(taxChoice, lowTax, normalTax, aggressiveTax);
    }

    private void displayTaxOptions(TaxStrategy low, TaxStrategy normal, TaxStrategy aggressive) {
        System.out.println("\n--- TAX OPTIONS ---");
        System.out.println("1. " + low.getDescription() + " (Get €" + low.getExpectedIncome(city) + ")");
        System.out.println("2. " + normal.getDescription() + " (Get €" + normal.getExpectedIncome(city) + ")");
        System.out.println("3. " + aggressive.getDescription() + " (Get €" + aggressive.getExpectedIncome(city) + ")");
        System.out.println("0. Back to main menu");
        System.out.print("\nYour choice: ");
    }

    private void processTaxChoice(int choice, TaxStrategy low, TaxStrategy normal, TaxStrategy aggressive) {
        switch (choice) {
            case 1:
                city.applyTaxStrategy(low);
                System.out.println("\n✓ Low taxes applied. Citizens are slightly unhappy.");
                endTurnWithEvents();
                break;
            case 2:
                city.applyTaxStrategy(normal);
                System.out.println("\n✓ Normal taxes applied. Citizens are unhappy.");
                endTurnWithEvents();
                break;
            case 3:
                city.applyTaxStrategy(aggressive);
                System.out.println("\n✓ Aggressive taxes applied! Citizens are very unhappy!");
                endTurnWithEvents();
                break;
            case 0:
                System.out.println("\n✓ Returning to main menu...");
                break;
            default:
                System.out.println("\n✗ Invalid choice!");
                break;
        }
    }

    private void handleWin() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║     CONGRATULATIONS!           ║");
        System.out.println("║   YOU WON THE GAME!            ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("\nYour city survived " + City.WIN_TURNS + " turns!");
        System.out.println("Final statistics:");
        city.displayStatus();
        gameRunning = false;
    }

    private void handleLose() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║       GAME OVER                ║");
        System.out.println("║     YOU LOST!                  ║");
        System.out.println("╚════════════════════════════════╝");

        if (city.getBudget() <= 0) {
            System.out.println("\nYour city went bankrupt!");
        } else {
            System.out.println("\nCitizen happiness dropped too low!");
            System.out.println("People left the city in masses.");
        }
        System.out.println("\nFinal statistics:");
        city.displayStatus();
        gameRunning = false;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}