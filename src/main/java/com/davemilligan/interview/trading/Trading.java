package com.davemilligan.interview.trading;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Trading {

    public static List<Product> getProductsSellingOver(
            List<Sale> sales,
            LocalDate startDate,
            LocalDate endDate,
            int boundary) {
        final Map<Integer, Integer> salesPerProduct = new HashMap<>();

        getSalesBetweenDates(
                sales,
                startDate,
                endDate).forEach(s -> {
            int currentTotal = (salesPerProduct.containsKey(s.pid)) ? salesPerProduct.get(s.pid) : 0;
            salesPerProduct.put(s.pid, currentTotal + s.volume);
        });

        final List<Product> products = new ArrayList<>();
        salesPerProduct.keySet().stream().forEach(pid -> {
            if (salesPerProduct.get(pid) > boundary) {
                products.add(getProduct(pid));
            }
        });
        return products;
    }

    public static int getTotalsBetweenDates(
            List<Sale> sales,
            LocalDate startDate,
            LocalDate endDate) {
        return getSalesBetweenDates(sales, startDate, endDate)
                .stream()
                .map(s -> s.volume)
                .reduce(0, (total, volume) -> {
                    return total += volume;
                });
    }

    public static int getSalesTotalsOnDate(List<Sale> sales, LocalDate date) {
        return getSalesOnDate(sales, date)
                .stream()
                .map(s -> s.volume)
                .reduce(0, (total, volume) -> {
                    return total += volume;
                });
    }

    public static List<Sale> getSalesBetweenDates(
            List<Sale> sales,
            LocalDate startDate,
            LocalDate endDate) {
        return sales
                .stream()
                .filter(s-> s.date.isAfter(startDate) && s.date.isBefore(endDate))
                .collect(Collectors.toList());
    }

    public static List<Sale> getSalesOnDate(List<Sale> sales, LocalDate date) {
        return sales
                .stream()
                .filter(s-> s.date.equals(date))
                .collect(Collectors.toList());
    }

    public static int getSales(List<Sale> sales) {
        return sales.stream().map(s -> s.volume).reduce(0, (total, volume) -> {
            return total += volume;
        });
    }

    //  Placeholder
    public static Product getProduct(int pid) {
        return new Product();
    }
}


class Sale {
    int pid;
    int volume;
    LocalDate date;
}

class Product {
    int id;
    double price;
    String name;
}
