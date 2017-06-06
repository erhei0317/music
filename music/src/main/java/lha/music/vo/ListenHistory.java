package lha.music.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ListenHistory implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer historyId;
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(referencedColumnName="username",nullable=false)
	private User user;
	@ManyToOne(targetEntity=Song.class)
	@JoinColumn(referencedColumnName="song_id",nullable=false)
	private Song song;

	private Integer listenTime;//听歌次数
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public Integer getListenTime() {
		return listenTime;
	}
	public void setListenTime(Integer listenTime) {
		this.listenTime = listenTime;
	}
	
}
