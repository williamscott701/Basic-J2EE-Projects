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
@Table(name = "TESTHISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testhistory.findAll", query = "SELECT t FROM Testhistory t"),
    @NamedQuery(name = "Testhistory.findByCreatedon", query = "SELECT t FROM Testhistory t WHERE t.createdon = :createdon"),
    @NamedQuery(name = "Testhistory.findByUsername", query = "SELECT t FROM Testhistory t WHERE t.username = :username"),
    @NamedQuery(name = "Testhistory.findByCompanyname", query = "SELECT t FROM Testhistory t WHERE t.companyname = :companyname"),
    @NamedQuery(name = "Testhistory.findByScore", query = "SELECT t FROM Testhistory t WHERE t.score = :score")})
public class Testhistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CREATEDON")
    private String createdon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COMPANYNAME")
    private String companyname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCORE")
    private int score;

    public Testhistory() {
    }

    public Testhistory(String createdon) {
        this.createdon = createdon;
    }

    public Testhistory(String createdon, String username, String companyname, int score) {
        this.createdon = createdon;
        this.username = username;
        this.companyname = companyname;
        this.score = score;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (createdon != null ? createdon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Testhistory)) {
            return false;
        }
        Testhistory other = (Testhistory) object;
        if ((this.createdon == null && other.createdon != null) || (this.createdon != null && !this.createdon.equals(other.createdon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Testhistory[ createdon=" + createdon + " ]";
    }
    
}
