package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        // Группировка по продуктам и подсчет их общей стоимости
        Map<String, Double>productTotalPrice = orders.stream().
                collect(Collectors.groupingBy(
                    Order::getProduct,
                    Collectors.summingDouble(Order::getCost)
                ));

        // Сортировка по убыванию стоимости
        List<Map.Entry<String,Double>> sortedProducts = productTotalPrice.entrySet().stream()
                .sorted(Map.Entry.<String,Double>comparingByValue().reversed()).toList();
        // Выборка 3 самых дорогих продуктов
        List<Map.Entry<String,Double>> topThreeProducts = sortedProducts.stream().limit(3).toList();

        // Подсчет общей стоимости топ-3 продуктов
        double topThreeTotalCost = topThreeProducts.stream().mapToDouble(Map.Entry::getValue).sum();

        System.out.println("Топ 3 самых дорогих продуктов");
        topThreeProducts.forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()+"$"));

        System.out.println("Общая стоимость трех самых дорогих продуктов: " + topThreeTotalCost);
    }
}
