package com.collaborate.dao;

import java.util.List;
import com.collaborate.model.ForumComment;

public interface ForumCommentDAO {
	public boolean createForumComment(ForumComment forumComment);
	public ForumComment getForumComment(int id);
	public List<ForumComment> getForumComments();
	public boolean editForumComment(int id);
	public boolean deleteForumComment(int id);

}
