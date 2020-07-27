package org.example.entity;

public class Man {
    private String name;
    private Integer age;
    private String where;

    public Man() {
    }

    public Man(String name, Integer age, String where) {
        this.name = name;
        this.age = age;
        this.where = where;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", where='" + where + '\'' +
                '}';
    }
}
