package com.rdlab.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  @JsonIgnore
  private Integer id;

  @Column(name = "username", nullable = false, length = 30)
  private String username;

  @Column(name = "email", nullable = false, length = 50)
  @JsonIgnore
  private String email;

  @Column(name = "password", nullable = false, length = 100)
  @JsonIgnore
  private String password;

  @Transient
  @JsonIgnore
  private String confirmPassword;

  @Column(name = "firstname", length = 25)
  @JsonIgnore
  private String firstname;

  @Column(name = "lastname", length = 35)
  @JsonIgnore
  private String lastname;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_user_roles",
      joinColumns = @JoinColumn(name = "users_user_id"),
      inverseJoinColumns = @JoinColumn(name = "userroles_role_id"))
  @JsonIgnore
  private Set<UserRole> userRoles;

  public Set<UserRole> getUserRoles() {
    return userRoles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}