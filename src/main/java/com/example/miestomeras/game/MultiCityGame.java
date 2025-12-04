package com.example.miestomeras.game;

import com.example.miestomeras.factory.BuildingFactory;
import com.example.miestomeras.factory.BuildingFactory.BuildingType;
import com.example.miestomeras.model.Building;
import com.example.miestomeras.event.EventManager;
import com.example.miestomeras.strategy.*;
import java.util.*;

public class MultiCityGame {
    private final List<City> cities;
    private final Scanner scanner;
    private boolean gameRunning;
    private final EventManager eventManager;
    private int currentTurn;

    private static final int NUMBER_OF_CITIES = 3;
    private static final String[] CITY_NAMES = {"Vilnius", "Kaunas", "Klaipėda"};

    public MultiCityGame() {
        this.cities = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.gameRunning = true;
        this.eventManager = new EventManager();
        this.currentTurn = 1;

        for (int i = 0; i < NUMBER_OF_CITIES; i++) {
            cities.add(new City(CITY_NAMES[i]));
        }
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("  MULTI-CITY MAYOR GAME!");
        System.out.println("==========================================");
        System.out.println("Manage " + NUMBER_OF_CITIES + " cities simultaneously!");
        System.out.println("Goal: Keep ALL cities alive for " + City.WIN_TURNS + " turns!\n");

        while (gameRunning && currentTurn <= City.WIN_TURNS) {
            displayAllCitiesStatus();

            if (checkAnyLose()) {
                handleLose();
                break;
            }

            for (City city : cities) {
                if (city.checkLose()) {
                    continue;
                }

                System.out.println("\n========== MANAGING: " + city.getName() + " ==========");
                city.displayStatus();

                showCityMenu();
                int choice = getPlayerChoice();
                processChoice(choice, city);

                if (!gameRunning) {
                    break;
                }
            }

            if (gameRunning) {
                endTurnForAllCities();
                currentTurn++;
            }
        }

        if (currentTurn > City.WIN_TURNS && !checkAnyLose()) {
            handleWin();
        }

        scanner.close();
    }

    private void displayAllCitiesStatus() {
        System.out.println("\n╔═════════════════════════════════════════════╗");
        System.out.println("║        TURN " + currentTurn + " / " + City.WIN_TURNS + "                          ║");
        System.out.println("╠═════════════════════════════════════════════╣");

        for (City city : cities) {
            String status = city.checkLose() ? "💀 DEAD" : "✓ Alive";
            System.out.printf("║ %-15s Budget: €%-10d %s ║%n",
                    city.getName(), city.getBudget(), status);
        }
        System.out.println("╚═════════════════════════════════════════════╝");
    }

    private void showCityMenu() {
        System.out.println("\n--- ACTIONS ---");
        System.out.println("1. Build Hospital (€1,500,000)");
        System.out.println("2. Build School (€1,000,000)");
        System.out.println("3. Build Police Station (€800,000)");
        System.out.println("4. Build Park (€300,000)");
        System.out.println("5. Build Homes (€500,000)");
        System.out.println("6. Build Market (€600,000)");
        System.out.println("7. Tax Options");
        System.out.println("8. Decrease Expenses (Save €200,000, Happiness -10%, Safety -10%)");
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

    private void processChoice(int choice, City city) {
        final BuildingType buildingType;
        switch (choice) {
            case 1: buildingType = BuildingType.HOSPITAL; break;
            case 2: buildingType = BuildingType.SCHOOL; break;
            case 3: buildingType = BuildingType.POLICE_STATION; break;
            case 4: buildingType = BuildingType.PARK; break;
            case 5: buildingType = BuildingType.PERSONS_HOME; break;
            case 6: buildingType = BuildingType.MARKET; break;
            case 7:
                showTaxMenu(city);
                return;
            case 8:
                city.decreaseExpenses();
                System.out.println("\n✓ Expenses decreased! Services reduced.");
                return;
            case 9:
                System.out.println("\n✓ Turn ended. Time passes...");
                return;
            case 0:
                System.out.println("\nThanks for playing!");
                gameRunning = false;
                return;
            default:
                System.out.println("\n✗ Invalid choice! Try again.");
                return;
        }

        buildBuilding(buildingType, city);
    }

    private void buildBuilding(BuildingType buildingType, City city) {
        Building building = BuildingFactory.createBuilding(buildingType);
        if (city.addBuilding(building)) {
            System.out.println("\n✓ " + building.getName() + " built successfully!");
            System.out.println("Effect: " + building.getDescription());
        } else {
            System.out.println("\n✗ Not enough budget to build " + building.getName());
        }
    }

    private void showTaxMenu(City city) {
        TaxStrategy lowTax = new LowTaxStrategy();
        TaxStrategy normalTax = new NormalTaxStrategy();
        TaxStrategy aggressiveTax = new AggressiveTaxStrategy();

        displayTaxOptions(lowTax, normalTax, aggressiveTax, city);

        int taxChoice = getPlayerChoice();
        processTaxChoice(taxChoice, lowTax, normalTax, aggressiveTax, city);
    }

    private void displayTaxOptions(TaxStrategy low, TaxStrategy normal,
                                   TaxStrategy aggressive, City city) {
        System.out.println("\n--- TAX OPTIONS ---");
        System.out.println("1. " + low.getDescription() + " (Get €" + low.getExpectedIncome(city) + ")");
        System.out.println("2. " + normal.getDescription() + " (Get €" + normal.getExpectedIncome(city) + ")");
        System.out.println("3. " + aggressive.getDescription() + " (Get €" + aggressive.getExpectedIncome(city) + ")");
        System.out.println("0. Back to main menu");
        System.out.print("\nYour choice: ");
    }

    private void processTaxChoice(int choice, TaxStrategy low, TaxStrategy normal,
                                  TaxStrategy aggressive, City city) {
        switch (choice) {
            case 1:
                city.applyTaxStrategy(low);
                System.out.println("\n✓ Low taxes applied. Citizens are slightly unhappy.");
                break;
            case 2:
                city.applyTaxStrategy(normal);
                System.out.println("\n✓ Normal taxes applied. Citizens are unhappy.");
                break;
            case 3:
                city.applyTaxStrategy(aggressive);
                System.out.println("\n✓ Aggressive taxes applied! Citizens are very unhappy!");
                break;
            case 0:
                System.out.println("\n✓ Returning to main menu...");
                break;
            default:
                System.out.println("\n✗ Invalid choice!");
                break;
        }
    }

    private void endTurnForAllCities() {
        System.out.println("\n========== END OF TURN " + currentTurn + " ==========");

        for (City city : cities) {
            if (city.checkLose()) {
                continue;
            }

            city.endTurn();

            String eventMessage = eventManager.triggerEvent(city);
            if (eventMessage != null) {
                System.out.println("\n🌆 " + city.getName() + ": " + eventMessage);
            }
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private boolean checkAnyLose() {
        for (City city : cities) {
            if (city.checkLose()) {
                return true;
            }
        }
        return false;
    }

    private void handleWin() {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║   🎉 CONGRATULATIONS! 🎉          ║");
        System.out.println("║   ALL CITIES SURVIVED!            ║");
        System.out.println("╚═══════════════════════════════════╝");

        displayAllCitiesStatus();
        gameRunning = false;
    }

    private void handleLose() {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║   💀 GAME OVER 💀                 ║");
        System.out.println("║   A CITY HAS FALLEN!              ║");
        System.out.println("╚═══════════════════════════════════╝");

        for (City city : cities) {
            if (city.checkLose()) {
                System.out.println("\n❌ " + city.getName() + " FAILED!");
                if (city.getBudget() <= 0) {
                    System.out.println("   Reason: Bankruptcy");
                } else {
                    System.out.println("   Reason: Citizen revolt (low happiness)");
                }
            }
        }

        displayAllCitiesStatus();
        gameRunning = false;
    }

    public static void main(String[] args) {
        MultiCityGame game = new MultiCityGame();
        game.start();
    }
}