/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rackarmattan
 */
@Entity
@Table(name = "FRUITS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fruits.findAll", query = "SELECT f FROM Fruits f")
    , @NamedQuery(name = "Fruits.findByFname", query = "SELECT f FROM Fruits f WHERE f.fname = :fname")
    , @NamedQuery(name = "Fruits.findByColor", query = "SELECT f FROM Fruits f WHERE f.color = :color")})
public class Fruits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @Column(name = "COLOR")
    private String color;

    public Fruits() {
    }

    public Fruits(String fname) {
        this.fname = fname;
    }

    public Fruits(String fname, String color) {
        this.fname = fname;
        this.color = color;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fname != null ? fname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fruits)) {
            return false;
        }
        Fruits other = (Fruits) object;
        if ((this.fname == null && other.fname != null) || (this.fname != null && !this.fname.equals(other.fname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project2.model.Fruits[ fname=" + fname + " ]";
    }
    
}
