package com.afse.academy;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="department")
public class Department implements Serializable {

    private static final long serialVersionUID = 5862147141541072372L;

    public Department() {}

    public Department(Long id, String name, Address adr){
        this.id = id;
        this.name = name;
        this.address = adr;
    }

    @Id
    @GeneratedValue(generator = "DEPARTMENT_SEQ")
    @SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "department_dep_id_seq", sequenceName = "department_dep_id_seq", allocationSize = 1)
    @Column(name="dep_id")
    private Long id;

    @NotNull
    private String name;

    @Valid
    @Embedded
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
