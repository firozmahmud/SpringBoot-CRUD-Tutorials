package com.example.springbootcrudtutorials.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private boolean published;
}
