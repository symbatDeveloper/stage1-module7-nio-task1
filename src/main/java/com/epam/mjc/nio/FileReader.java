package com.epam.mjc.nio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FileReader {
    Profile profile = new Profile();
    public Profile getDataFromFile(File file) {
        try (Stream<String> lines = Files.lines(Paths.get(file.toURI()))) {
            return getProfileFromRawStrings(lines.toArray(String[]::new));
        } catch (IOException e) {
            throw new TestFileIncorrectException(e.getMessage());
        }
    }



    public class TestFileIncorrectException extends RuntimeException {
        TestFileIncorrectException(String message) {
            super(message);
        }

        TestFileIncorrectException() {
            super();
        }
    }

    private Profile getProfileFromRawStrings(String[] strings) {

            for (String str : strings) {
                String[] answer = str.split(": ");
                switch (answer[0]) {
                    case "Name":
                        profile.setName(answer[1]);
                        break;
                    case "Age":
                        profile.setAge(Integer.parseInt(answer[1]));
                        break;
                    case "Email":
                        profile.setEmail(answer[1]);
                        break;
                    case "Phone":
                        profile.setPhone(Long.parseLong(answer[1]));
                        break;
                    default:
                        break;
                }
            }

            return profile;
        }
    }
