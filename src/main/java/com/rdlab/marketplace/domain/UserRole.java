package com.rdlab.marketplace.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id", nullable = false)
  private Integer id;

  @Column(name = "role_title", nullable = false, length = 15)
  private String roleTitle;

  @ManyToMany(mappedBy = "userRoles")
  @Exclude
  private Set<User> users;

  public UserRole(Integer id) {
    this.id = id;
  }

  public UserRole(Integer id, String roleTitle) {
    this.id = id;
    this.roleTitle = roleTitle;
  }

  @Override
  public String getAuthority() {
    return getRoleTitle();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    UserRole userRole = (UserRole) o;
    return id != null && Objects.equals(id, userRole.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}