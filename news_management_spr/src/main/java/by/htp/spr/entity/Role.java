package by.htp.spr.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId")
	private int roleId;

	@Column(name = "role")
	private String role;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_login")
	private User user;

	public Role() {
	}

	public Role(int roleId, String role, User user) {
		this.roleId = roleId;
		this.role = role;
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Role role1 = (Role) o;
		return roleId == role1.roleId && Objects.equals(role, role1.role) && Objects.equals(user, role1.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, role, user);
	}

	@Override
	public String toString() {
		return "Role{" + "roleId=" + roleId + ", role='" + role + '\'' + ", user=" + user + '}';
	}
}
