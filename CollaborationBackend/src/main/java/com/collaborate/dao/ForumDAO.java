package com.collaborate.dao;

import java.util.List;
import com.collaborate.model.Forum;

public interface ForumDAO {
	public boolean createForum(Forum forum);
	public Forum getForum(int forumid);
	public List<Forum> getForums();
	public boolean approveForum(Forum forum);
	public boolean editForum(int forumid);
	public boolean deleteForum(int forumid);
	 public List<Forum> getApprovedForums();

}
