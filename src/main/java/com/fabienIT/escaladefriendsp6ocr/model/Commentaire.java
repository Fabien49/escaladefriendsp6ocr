package com.fabienIT.escaladefriendsp6ocr.model;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="commentaire")
public class Commentaire {

    @Id
    @Column(name = "commentaire_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    private String com;

    public Commentaire() {
    }

    public Commentaire(Long id, Date dateTime, String com) {
        this.id = id;
        this.dateTime = dateTime;
        this.com = com;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", com='" + com + '\'' +
                '}';
    }
}
