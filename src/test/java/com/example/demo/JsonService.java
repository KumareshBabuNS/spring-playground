package com.example.demo;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

class JsonService {

    String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}
