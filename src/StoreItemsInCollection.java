
//Benjamin McBrayer, 5.2.2018
//This Java console program uses collections to store items in a shopping list.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StoreItemsInCollection {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		final int NUM_ITEMS = 20;
		final int NUM_COSTS = 20;
		ArrayList<String> item = new ArrayList<>(NUM_ITEMS);
		ArrayList<Double> cost = new ArrayList<>(NUM_COSTS);
		double avgPrice = 0;
		HashMap<String, Double> products = new HashMap<>();
		String playAgain = null;

		// Populate HashMap with products and prices.
		products.put("apple", 0.99);
		products.put("banana", 0.59);
		products.put("cauliflower", 1.59);
		products.put("dragonfruit", 2.19);
		products.put("elderberry", 1.79);
		products.put("figs", 2.09);
		products.put("grapefruit", 1.99);
		products.put("honeydew", 3.49);

		// Ask for user information; greet user.
		System.out.println(getUserNameAndSayHello(scnr));

		System.out.println("\nWelcome to ARGUS FARMS!");

		do {
			// Display inventory.
			displayProducts(products);

			// Get user input.
			System.out.print("\nWhat item would you like to order? ");
			String userOrder = scnr.next();

			// Add to order if no error; else handle exception.
			if (products.containsKey(userOrder)) {
				System.out.println("Adding " + userOrder + " to cart at " + "$" + products.get(userOrder) + ".");
				// Store items/prices ordered.
				item.add(userOrder);
				cost.add(products.get(userOrder));
			} else {
				System.out.println("Sorry, we don't have those. Please try again.");
				continue;
			}
			// Prompt user to continue.
			System.out.print("Would you like to order anything else (y/n)? ");
			playAgain = scnr.next();

		} while (playAgain.equalsIgnoreCase("y"));

		// Display order.
		System.out.println("Thanks for your order!");
		System.out.println("Here's what you got: ");
		for (int i = 0; i < item.size(); ++i) {
			System.out.printf("\n%1$-15s %2$-8s", item.get(i), cost.get(i));
		}

		// Calculate average price.
		getAvgPrice(cost, avgPrice);

		// Calculate highest and lowest costs.
		System.out.printf("\nThe index of the most expensive item was %.2f", getHighestCost(cost));
		System.out.printf("\nThe index of the least expensive item was %.2f", getLowestCost(cost));
		System.out.println("\n\nHave a wonderful day!");

		scnr.close();
	}

	public static String getUserNameAndSayHello(Scanner scnr) {
		System.out.print("Please enter your name: ");
		String userName = scnr.nextLine();
		return "Hello, " + userName + "!";
	}

	public static void getAvgPrice(ArrayList<Double> cost, double avgPrice) {
		avgPrice = 0;
		for (int i = 0; i < cost.size(); ++i) {
			avgPrice += cost.get(i) / cost.size();
		}
		System.out.printf("\n\nAverage price per item in order was %.2f", avgPrice);
	}

	public static void displayProducts(HashMap<String, Double> products) {
		System.out.printf("%1$-15s %2$-8s", "Item", "Price");
		System.out.printf("\n%1$-15s %2$-8s", "=======", "=======");

		for (String product : products.keySet()) {
			double price = products.get(product);
			System.out.printf("\n%1$-15s %2$-8s", product, price);
		}
	}

	// Method for indices of the highest and lowest cost items.
	public static double getHighestCost(ArrayList<Double> costs) {
		double highestCost = 0;
		for (double cost : costs) {
			if (cost > highestCost) {
				highestCost = cost;
			}
		}
		return highestCost;
	}

	public static double getLowestCost(ArrayList<Double> costs) {
		double lowestCost = Double.MAX_VALUE;
		for (double cost : costs) {
			if (cost < lowestCost) {
				lowestCost = cost;
			}
		}
		return lowestCost;
	}
}