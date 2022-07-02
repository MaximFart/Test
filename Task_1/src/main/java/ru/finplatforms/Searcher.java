package ru.finplatforms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Searcher {

    private String nameRoot;
    private Scanner scanner;
    private List<File> listTxtFiles;

    public Searcher() {
        this.scanner = new Scanner(System.in);
        this.listTxtFiles = new ArrayList<>();
    }

    public void defineTxtFiles(File file) throws NotFoundException {
        if (file.exists()) {
            for (File item : Objects.requireNonNull(file.listFiles())) {
                if (item.isDirectory()) {
                    defineTxtFiles(item);
                } else {
                    listTxtFiles.add(item);
                }
            }
        } else {
            throw new NotFoundException();
        }
    }

    public String getNameRoot() {
        return nameRoot;
    }

    public void setNameRoot(String nameRoot) {
        this.nameRoot = nameRoot;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<File> getListTxtFiles() {
        return this.listTxtFiles;
    }

    public void setListTxtFiles(List<File> listTxtFiles) {
        this.listTxtFiles = listTxtFiles;
    }
}
