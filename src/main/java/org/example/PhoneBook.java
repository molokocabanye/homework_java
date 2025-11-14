package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    public void add(String lastName, String phone) {
        if (!contacts.containsKey(lastName)) {
            contacts.put(lastName, new ArrayList<>());
        }
        contacts.get(lastName).add(phone);
    }

    public List<String> get(String lastName) {
        if (contacts.containsKey(lastName)) {
            return contacts.get(lastName);
        } else {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "111-111");
        phoneBook.add("Иванов", "222-222");
        phoneBook.add("Петров", "333-333");
        phoneBook.add("Сидорова", "444-444");
        phoneBook.add("Иванов", "555-555");

        System.out.println("Телефоны Иванова:");
        List<String> ivanovPhones = phoneBook.get("Иванов");
        for (String phone : ivanovPhones) {
            System.out.println(phone);
        }

        System.out.println("\nТелефоны Петрова:");
        List<String> petrovPhones = phoneBook.get("Петров");
        for (String phone : petrovPhones) {
            System.out.println(phone);
        }

        System.out.println("\nТелефоны Неизвестного:");
        List<String> unknownPhones = phoneBook.get("Неизвестный");
        if (unknownPhones.isEmpty()) {
            System.out.println("Такой фамилии нет в справочнике");
        }
    }
}