/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William Scott
 */
@Entity
@Table(name = "BANKACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bankaccount.findAll", query = "SELECT b FROM Bankaccount b"),
    @NamedQuery(name = "Bankaccount.findByName", query = "SELECT b FROM Bankaccount b WHERE b.name = :name"),
    @NamedQuery(name = "Bankaccount.findByUsername", query = "SELECT b FROM Bankaccount b WHERE b.username = :username"),
    @NamedQuery(name = "Bankaccount.findByEmail", query = "SELECT b FROM Bankaccount b WHERE b.email = :email"),
    @NamedQuery(name = "Bankaccount.findByDob", query = "SELECT b FROM Bankaccount b WHERE b.dob = :dob"),
    @NamedQuery(name = "Bankaccount.findByPassword", query = "SELECT b FROM Bankaccount b WHERE b.password = :password"),
    @NamedQuery(name = "Bankaccount.findByBalance", query = "SELECT b FROM Bankaccount b WHERE b.balance = :balance")})
public class Bankaccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "USERNAME")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 220)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DOB")
    private String dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 220)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private int balance;

    public Bankaccount() {
    }

    public Bankaccount(String username) {
        this.username = username;
    }

    public Bankaccount(String username, String name, String email, String dob, String password, int balance) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bankaccount)) {
            return false;
        }
        Bankaccount other = (Bankaccount) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Bankaccount[ username=" + username + " ]";
    }
    
}
