package com.davemilligan.interview.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Input {

    public static void main(String[] args) throws IOException {
//        getFileReader();
//        readCharByChar();
//        readLineByLineWithBufferedReader();
//        readFromFileUsingScanner();
//        nioReadLines();
//        nioReadAllBytes();
        nioBufferedReader();
    }

    private static void nioBufferedReader() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(getUrl()), Charset.forName("US-ASCII"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * How its done since java 1.7
     * Reads line by line .
     *
     * @throws FileNotFoundException
     */
    private static void nioReadLines() throws IOException {
        Stream<String> stream = Files.lines(Path.of(getUrl()));
        stream.forEach(System.out::println);
    }

    /**
     * Read a file into a string.
     */
    private static void nioReadAllBytes() throws IOException {
        System.out.println(new String(Files.readAllBytes(Path.of(getUrl()))));
    }

    /**
     * How its done since java 1.5
     * Reads by line char int string etc, uses whitespace to plit tokens and try to parse.
     *
     * @throws FileNotFoundException
     */
    private static void readFromFileUsingScanner() throws FileNotFoundException {
        Scanner sc = new Scanner(getFile());
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }

    /**
     * How its done since java 1.1
     * Reads line
     *
     * @throws FileNotFoundException
     */
    private static void readLineByLineWithBufferedReader() throws IOException {
        BufferedReader reader = new BufferedReader(getFileReader());
        try (Stream<String> stringStream = reader.lines()) {
            stringStream.forEach(System.out::println);
        }
    }

    private static void readCharByChar() throws IOException {
        FileReader fileReader = getFileReader();
        while(fileReader.ready()) {
            System.out.print((char)fileReader.read());
        }
    }

    private static FileReader getFileReader() throws IOException {
        return new FileReader(getFile());
    }

    private static File getFile() {
        return new File(getUrl());
    }

    private static String getUrl() {
        return Thread.currentThread().getContextClassLoader().getResource("input.txt").getFile();
    }
}
