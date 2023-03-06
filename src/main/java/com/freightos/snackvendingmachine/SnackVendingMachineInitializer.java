package com.freightos.snackvendingmachine;

import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.models.item.ItemSlot;
import com.freightos.snackvendingmachine.models.item.SnackItem;
import com.freightos.snackvendingmachine.models.money.MoneySlot;
import com.freightos.snackvendingmachine.vendingmachine.SnackVendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
public class SnackVendingMachineInitializer implements ApplicationRunner {

    @Value("${vendingmachine.rows.count}")
    private int numberOfRows;

    @Value("${vendingmachine.colomns.count}")
    private int numberOfColumns;

    @Autowired
    SnackVendingMachine snackVendingMachine;

    private List<String> snacksNames = Arrays.asList(
            "Popcorn", "Pretzels", "Potato chips", "Tortilla chips", "Cheese crackers", "Trail mix", "Beef jerky",
            "Granola bars", "Rice cakes", "Roasted nuts", "Veggie straws", "Rice crackers", "Pita chips", "Corn nuts",
            "Fruit leather", "Seaweed snacks", "Animal crackers", "Sunflower seeds", "Wasabi peas",
            "Peanut butter crackers", "Fig bars", "Raisins", "Graham crackers", "Saltine crackers", "Snack mix",
            "Chex mix", "Cheese puffs", "Cracker jacks", "Goldfish crackers", "Peanut butter cups",
            "Toasted coconut chips", "Dried mango", "Dried pineapple", "Beef sticks", "Cheddar popcorn",
            "Popcorn balls", "Popcorn seasoning", "Chocolate-covered almonds", "Chocolate-covered pretzels",
            "Dark chocolate-covered raisins"
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        snackVendingMachine.setNumberToSlotMap(new HashMap<>());
        snackVendingMachine.setDenominationToSlotMap(new HashMap<>());
        Random random = new Random();
        int numberOfItems = numberOfRows * numberOfColumns;
        for (int i = 0; i < numberOfItems; ++i) {
            SnackItem item = new SnackItem(snacksNames.get(i));
            String number = calculateNumber(i);
            double price = Math.round((random.nextDouble() * 4 + 1) * 10) / 10.0;
            int stock = (int) Math.round(random.nextDouble() * 11);
            ItemSlot itemSlot = new ItemSlot(number, price, stock, item);
            snackVendingMachine.getNumberToSlotMap().put(number, itemSlot);
        }

        Arrays.stream(MoneyDenomination.values()).forEach(moneyDenomination -> {
            int count = (int) Math.round(random.nextDouble() * 5);
            MoneySlot moneySlot = new MoneySlot(count, moneyDenomination);
            snackVendingMachine.getDenominationToSlotMap().put(moneyDenomination, moneySlot);
        });
    }

    private String calculateNumber(int index) {
        return index / numberOfColumns + "" + index % numberOfColumns;
    }
}
