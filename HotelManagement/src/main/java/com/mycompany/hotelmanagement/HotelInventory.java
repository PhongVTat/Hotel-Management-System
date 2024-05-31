/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagement;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class HotelInventory {
    private final Map<String, InventoryItem> inventory;

    public HotelInventory() {
        inventory = new HashMap<>();
        // Add default items to the inventory
        addItemToInventory("Towel", 50, 5.99);
        addItemToInventory("Soap", 100, 1.99);
        addItemToInventory("Rolling Bed", 20, 49.99);
        addItemToInventory("Pillow", 30, 9.99);
        addItemToInventory("Ashtray", 10, 2.49);
    }

    public void addItemToInventory(String itemName, int quantity, double price) {
        if (inventory.containsKey(itemName)) {
            System.out.println(itemName + " is already in inventory.");
        } else {
            InventoryItem newItem = new InventoryItem(itemName, quantity, price);
            inventory.put(itemName, newItem);
            System.out.println(itemName + " added to inventory.");
        }
    }

    public void removeItemFromInventory(String itemName) {
        if (inventory.containsKey(itemName)) {
            inventory.remove(itemName);
            System.out.println(itemName + " removed from inventory.");
        } else {
            System.out.println(itemName + " is not in inventory.");
        }
    }

    public void updateInventory(String itemName, int newQuantity) {
        if (inventory.containsKey(itemName)) {
            InventoryItem item = inventory.get(itemName);
            item.updateQuantity(newQuantity);
            System.out.println("Quantity of " + itemName + " updated to " + newQuantity + ".");
        } else {
            System.out.println(itemName + " is not in inventory.");
        }
    }

    public void displayInventory() {
        StringBuilder inventoryText = new StringBuilder("Current Inventory:\n");
        for (Map.Entry<String, InventoryItem> entry : inventory.entrySet()) {
            InventoryItem item = entry.getValue();
            inventoryText.append(item.getName()).append(": Quantity - ").append(item.getQuantity()).append(", Price - ").append(item.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(null, inventoryText.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }
}

