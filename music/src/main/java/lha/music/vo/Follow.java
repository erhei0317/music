package lha.music.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Follow implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer followId;
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(referencedColumnName="username",nullable=false)
	private User followUser;//关注人
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(referencedColumnName="username",nullable=false)
	private User followedUser;//被关注人
	public Integer getFollowId() {
		return followId;
	}
	public void setFollowId(Integer followId) {
		this.followId = followId;
	}
	public User getFollowUser() {
		return followUser;
	}
	public void setFollowUser(User followUser) {
		this.followUser = followUser;
	}
	public User getFollowedUser() {
		return followedUser;
	}
	public void setFollowedUser(User followedUser) {
		this.followedUser = followedUser;
	}
	
}
