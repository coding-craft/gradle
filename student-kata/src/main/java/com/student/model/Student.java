package com.student.model;

public class Student {

    private Long id;

    private String name;

    private String address;


    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class StudentBuilder {
        private Long id;
        private String name;
        private String address;

        public Student.StudentBuilder id(Long value) {
            id = value;
            return this;
        }

        public Student.StudentBuilder name(String value) {
            name = value;
            return this;
        }

        public Student.StudentBuilder address(String value) {
            address = value;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    private Student(Student.StudentBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
    }
}
