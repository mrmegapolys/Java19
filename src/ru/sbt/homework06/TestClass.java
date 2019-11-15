package ru.sbt.homework06;

import java.util.List;
import java.util.Map;

public class TestClass {
    private final Person[] personArray;
    private final List<Person> personList;
    private final String string;
    private final int intValue;
    private Integer notInitialized;
    private final Object nullValue = null;
    private final Map<Person, Person> map;

    public TestClass(Person[] personArray, List<Person> personList, String string, int intValue, Map<Person, Person> map) {
        this.personArray = personArray;
        this.personList = personList;
        this.string = string;
        this.intValue = intValue;
        this.map = map;
    }
}
