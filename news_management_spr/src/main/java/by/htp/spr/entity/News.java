package by.htp.spr.entity;

import java.sql.Timestamp;
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
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsId")
	private int newsId;

	@Column(name = "newsDate")
	private Timestamp newsDate;

	@Column(name = "newsTitle")
	private String newsTitle = "";

	@Column(name = "newsBrief")
	private String newsBrief = "";

	@Column(name = "newsContent")
	private String newsContent = "";

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_login")
	private User user;

	public News() {
	}

	public News(int newsId, Timestamp newsDate, String newsTitle, String newsBrief, String newsContent, User user) {
		super();

		this.newsId = newsId;
		this.newsDate = newsDate;
		this.newsTitle = newsTitle;
		this.newsBrief = newsBrief;
		this.newsContent = newsContent;
		this.user = user;

	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Timestamp getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Timestamp newsDate) {
		this.newsDate = newsDate;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsBrief() {
		return newsBrief;
	}

	public void setNewsBrief(String newsBrief) {
		this.newsBrief = newsBrief;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "News{" + "newsId=" + newsId + ", newsDate='" + newsDate + '\'' + ", newsTitle='" + newsTitle + '\''
				+ ", newsBrief='" + newsBrief + '\'' + ", newsContent='" + newsContent + '\'' + ", user='" + user + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		News news = (News) o;
		return newsId == news.newsId && Objects.equals(newsDate, news.newsDate)
				&& Objects.equals(newsTitle, news.newsTitle) && Objects.equals(newsBrief, news.newsBrief)
				&& Objects.equals(newsContent, news.newsContent) && user == news.user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(newsId, newsDate, newsTitle, newsBrief, newsContent, user);
	}

}
