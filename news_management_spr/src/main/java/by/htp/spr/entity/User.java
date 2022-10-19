package by.htp.spr.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;

	@Id
	@Pattern(regexp = "[a-zA-Z0-9_]+")
	@Column(name = "login")
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Pattern(regexp = "[a-zA-Z0-9_]+@[a-z]+.([a-z]+[^0-9])")
	@Column(name = "email")
	private String email;

	@Pattern(regexp = "[+]{1}([0-9]){12}")
	@Column(name = "telephone")
	private String telephone;

	@Column(name = "registrationDate")
	private Timestamp registrationDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private Set<News> news = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private Set<Role> role = new HashSet<>();

	public User() {
	}

	public User(int userId, String login, String password, String email, String telephone, Timestamp registrationDate,
			Set<News> news, Set<Role> role) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.registrationDate = registrationDate;
		this.news = news;
		this.role = role;
	}

	public User(String login, int userId) {
		super();

		this.login = login;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return userId == user.userId && Objects.equals(login, user.login) && Objects.equals(password, user.password)
				&& Objects.equals(email, user.email) && Objects.equals(telephone, user.telephone)
				&& Objects.equals(registrationDate, user.registrationDate) && Objects.equals(news, user.news)
				&& Objects.equals(role, user.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, login, password, email, telephone, registrationDate, news, role);
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", login='" + login + '\'' + ", password='" + password + '\''
				+ ", email='" + email + '\'' + ", telephone='" + telephone + '\'' + ", registrationDate="
				+ registrationDate + ", news=" + news + ", role=" + role + '}';
	}
}
