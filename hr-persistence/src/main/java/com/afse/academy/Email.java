package com.afse.academy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="email")
public class Email implements Serializable {
    private static final long serialVersionUID = -6924541096307396011L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "email_id_seq", sequenceName = "email_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "value")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
