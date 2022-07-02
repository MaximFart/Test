package ru.finplatforms;

import java.io.*;

public class Main {

    public static void main(String[] args) throws NotFoundException, IOException {
        Searcher searcher = new Searcher();
        System.out.println("Write path to file:");
        searcher.setNameRoot(searcher.getScanner().nextLine());
        File file = new File(searcher.getNameRoot());
        searcher.defineTxtFiles(file);
        System.out.println("Sorted txt files:");
        searcher.getListTxtFiles().stream().map(File::getName).sorted().forEach(System.out::println);
        System.out.println("Write path to txt file to paste the contents all txt files:");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(searcher.getScanner().nextLine()));){
            for (File item : searcher.getListTxtFiles()) {
                if (item.length() != 0) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(item))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line + "\n");
                        }
                    }
                }
            }
        }
    }
}
