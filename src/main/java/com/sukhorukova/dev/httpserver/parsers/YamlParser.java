package com.sukhorukova.dev.httpserver.parsers;

import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Data
public class YamlParser {
    private Yaml yaml;
    private Map<String, Object> resultMap;

    public YamlParser() {
        this.yaml = new Yaml();
    }

    public void parse(String fileName) {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);
        this.resultMap = yaml.load(inputStream);
    }
}
