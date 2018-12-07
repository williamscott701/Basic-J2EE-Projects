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
@Table(name = "RESERVATIONHISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservationhistory.findAll", query = "SELECT r FROM Reservationhistory r"),
    @NamedQuery(name = "Reservationhistory.findByDateofbooking", query = "SELECT r FROM Reservationhistory r WHERE r.dateofbooking = :dateofbooking"),
    @NamedQuery(name = "Reservationhistory.findByPassengername", query = "SELECT r FROM Reservationhistory r WHERE r.passengername = :passengername"),
    @NamedQuery(name = "Reservationhistory.findByPassengeremail", query = "SELECT r FROM Reservationhistory r WHERE r.passengeremail = :passengeremail"),
    @NamedQuery(name = "Reservationhistory.findByPassengerage", query = "SELECT r FROM Reservationhistory r WHERE r.passengerage = :passengerage"),
    @NamedQuery(name = "Reservationhistory.findByDateofjourney", query = "SELECT r FROM Reservationhistory r WHERE r.dateofjourney = :dateofjourney"),
    @NamedQuery(name = "Reservationhistory.findByTrainid", query = "SELECT r FROM Reservationhistory r WHERE r.trainid = :trainid"),
    @NamedQuery(name = "Reservationhistory.findByUsername", query = "SELECT r FROM Reservationhistory r WHERE r.username = :username"),
    @NamedQuery(name = "Reservationhistory.findByPaid", query = "SELECT r FROM Reservationhistory r WHERE r.paid = :paid")})
public class Reservationhistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DATEOFBOOKING")
    private String dateofbooking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PASSENGERNAME")
    private String passengername;
    @Size(max = 200)
    @Column(name = "PASSENGEREMAIL")
    private String passengeremail;
    @Size(max = 200)
    @Column(name = "PASSENGERAGE")
    private String passengerage;
    @Size(max = 200)
    @Column(name = "DATEOFJOURNEY")
    private String dateofjourney;
    @Size(max = 200)
    @Column(name = "TRAINID")
    private String trainid;
    @Size(max = 200)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PAID")
    private Integer paid;

    public Reservationhistory() {
    }

    public Reservationhistory(String dateofbooking) {
        this.dateofbooking = dateofbooking;
    }

    public Reservationhistory(String dateofbooking, String passengername) {
        this.dateofbooking = dateofbooking;
        this.passengername = passengername;
    }

    public String getDateofbooking() {
        return dateofbooking;
    }

    public void setDateofbooking(String dateofbooking) {
        this.dateofbooking = dateofbooking;
    }

    public String getPassengername() {
        return passengername;
    }

    public void setPassengername(String passengername) {
        this.passengername = passengername;
    }

    public String getPassengeremail() {
        return passengeremail;
    }

    public void setPassengeremail(String passengeremail) {
        this.passengeremail = passengeremail;
    }

    public String getPassengerage() {
        return passengerage;
    }

    public void setPassengerage(String passengerage) {
        this.passengerage = passengerage;
    }

    public String getDateofjourney() {
        return dateofjourney;
    }

    public void setDateofjourney(String dateofjourney) {
        this.dateofjourney = dateofjourney;
    }

    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateofbooking != null ? dateofbooking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservationhistory)) {
            return false;
        }
        Reservationhistory other = (Reservationhistory) object;
        if ((this.dateofbooking == null && other.dateofbooking != null) || (this.dateofbooking != null && !this.dateofbooking.equals(other.dateofbooking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Reservationhistory[ dateofbooking=" + dateofbooking + " ]";
    }
    
}
