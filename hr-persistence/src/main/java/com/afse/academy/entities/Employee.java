package com.afse.academy.entities;

import com.afse.academy.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 7974477907836459125L;

    public Employee() {}

    public Employee(Employee e) {
        this.setId(e.getId());
        this.setFirstName(e.getFirstName());
        this.setLastName(e.getLastName());
        this.setEmail(e.getEmail());
        this.setDepartment(e.getDepartment());
        this.setJoinDate(e.getJoinDate());
        this.setBirthDate(e.getBirthDate());
        this.setAddress(e.getAddress());
        this.setPhoneNumber(e.getPhoneNumber());
        this.setSalary(e.getSalary());
    }

    public Employee(Long id, String firstName, String lastName, String email) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
    }

    @Id
    @GeneratedValue(generator = "EMPLOYEE_SEQ")
    @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    @Column(name="emp_id")
    private Long id;

    @NotNull
    @Size(min=1, max=20, message = "Invalid first name")
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Size(min=1, max=20, message = "Invalid last name")
    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "Invalid birth date")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name="birth_date")
    private Date birthDate;

    @Valid
    @Embedded
    private Address address;

    @NotNull
    @Size(min=1, max=50)
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Invalid phone number")
    @Column(name="phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;

    @Positive(message = "Invalid salary")
    @Digits(integer = 10, fraction = 2)
    private double salary;

    @NotNull(message = "Invalid join date")
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name="join_date")
    private Date joinDate;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return lastName + " :: " + firstName + " :: " + department.getName();
    }
}