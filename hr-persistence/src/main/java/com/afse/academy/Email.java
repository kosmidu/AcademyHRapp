package com.afse.academy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="email")
public class Email implements Serializable {
    private static final long serialVersionUID = -6924541096307396011L;

    @Id
    @GeneratedValue(generator = "EMAIL_SEQ")
    @SequenceGenerator(name = "EMAIL_SEQ", sequenceName = "EMAIL_SEQ", allocationSize = 1)
    @Column(name = "email_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "email_email_id_seq", sequenceName = "email_email_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "email_msg")
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
