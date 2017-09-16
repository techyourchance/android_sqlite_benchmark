package com.techyourchance.sqlitebenchmark.test;

import org.apache.commons.text.RandomStringGenerator;

public class TestContentGenerator {

    /**
     * @return random string of a given size consisting of lowercase alphabet characters
     */
    public String generate(int count) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        return generator.generate(count);
    }
}
