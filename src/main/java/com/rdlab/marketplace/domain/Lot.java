package com.rdlab.marketplace.domain;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "lots")
public class Lot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lot_id", nullable = false)
  private Integer id;

  @Column(name = "start_price")
  private Double startPrice;

  @DateTimeFormat(iso= ISO.DATE)
  @Column(name = "stop_date", nullable = false)
  private LocalDate stopDate;

  @Column(name = "bid_inc")
  private Double bidInc;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "bid_id")
  private User bid;

  @ManyToOne(optional = false)
  @JoinColumn(name = "item_id", nullable = false)
  @Exclude
  private Item item;

  private Boolean isActive;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Lot lot = (Lot) o;
    return id != null && Objects.equals(id, lot.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}