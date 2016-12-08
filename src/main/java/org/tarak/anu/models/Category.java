package org.tarak.anu.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tarak on 12/3/2016.
 */
@Entity
public class Category {

    @Id
    @Column(name = "category_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique=true)
    @NotNull
    @Size(min=3,message ="Category should have minimum 3 characters")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
