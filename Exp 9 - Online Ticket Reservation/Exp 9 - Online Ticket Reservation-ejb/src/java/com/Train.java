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
@Table(name = "TRAIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Train.findAll", query = "SELECT t FROM Train t"),
    @NamedQuery(name = "Train.findById", query = "SELECT t FROM Train t WHERE t.id = :id"),
    @NamedQuery(name = "Train.findByName", query = "SELECT t FROM Train t WHERE t.name = :name"),
    @NamedQuery(name = "Train.findByCost", query = "SELECT t FROM Train t WHERE t.cost = :cost"),
    @NamedQuery(name = "Train.findByAvailable", query = "SELECT t FROM Train t WHERE t.available = :available")})
public class Train implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST")
    private int cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVAILABLE")
    private int available;

    public Train() {
    }

    public Train(Integer id) {
        this.id = id;
    }

    public Train(Integer id, String name, int cost, int available) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Train)) {
            return false;
        }
        Train other = (Train) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Train[ id=" + id + " ]";
    }
    
}
