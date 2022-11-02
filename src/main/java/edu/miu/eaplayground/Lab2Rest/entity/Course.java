package edu.miu.eaplayground.Lab2Rest.entity;

import lombok.Data;

@Data
public class Course {
    private Integer id;
    private String name;
    private String code;

    private boolean isDeleted;
}
