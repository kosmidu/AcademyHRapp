package com.afse.academy;//import java.io.Serializable;
//import javax.persistence.*;
//
//@Entity
//@Table(name = "employee")
//public class com.afse.academy.Employee implements Serializable {
//
//    private static final long serialVersionUID = 1450189575618318677L;
//    @Id
//    @Column(name = "id")
//    private Integer idEmployee;
//
//    @Transient
//    private String email;
//
//    @Transient
//    private String firstname;
//
//    @Transient
//    private String lastname;
//
//    public com.afse.academy.Employee() {}
//
//    public Integer getIdEmployee() {
//        return this.idEmployee;
//    }
//
//    public void setIdEmployee(Integer idEmployee) {
//        this.idEmployee = idEmployee;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirstname() {
//        return this.firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return this.lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
////    @Override
////    public String toString() {
////        return "com.afse.academy.Employee [idEmployee=" + idEmployee + ", email=" + email
////                + ", firstname=" + firstname + ", lastname=" + lastname + "]";
////    }
//
//    @Override
//    public String toString() {
//        return "com.afse.academy.Employee [idEmployee=" + idEmployee + "]";
//    }
//}

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "emp_db")
public class Employee {

    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min=1, max=2, message = "Test Validation first name")
    @Column(name = "fistName")
    private String firstName;

    @Size(min=1, max=2, message = "Test Validation last name")
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dept")
    private String dept;

    @Column(name = "email")
    private String email;

    public Employee() {}

    public Employee(Integer id, String firstName, String lastName, String dept, String email) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDept(dept);
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee [idEmployee=" + id
                + ", firstname=" + firstName + ", lastname=" + lastName + "]";
    }
}