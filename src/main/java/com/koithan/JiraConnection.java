package com.koithan;

import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.RestClient;
import net.rcarz.jiraclient.User;
import net.rcarz.jiraclient.agile.Board;

public class JiraConnection {

	static private JiraClient jiraClient = null;
	static private RestClient restClient = null;
	static public boolean loggedIn = false;
	
	public JiraConnection (String userName, String passWord)
	{
		BasicCredentials creds = new BasicCredentials(userName, passWord);

	    try {
	    	JiraConnection.jiraClient = new JiraClient("http://jira/", creds);
	    	JiraConnection.restClient = jiraClient.getRestClient();
			
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			System.err.println("Jira login failed!");
			JiraConnection.jiraClient = null;
			JiraConnection.restClient = null;
			return;
		}
	    
	    // Get all Scrum Boards
	    List<Board> allBoards = null;
	    try {
	 			allBoards = Board.getAll(jiraClient.getRestClient());
	 	}catch (JiraException ex) {
	 		System.err.println("Jira login failed!");
	        JiraConnection.jiraClient = null;
	        JiraConnection.restClient = null;
			return;
	    }
		
		if ((JiraConnection.jiraClient != null) && (JiraConnection.restClient != null)) {
			JiraConnection.loggedIn = true;
		}
	}
	
	static JiraClient getJiraClient() {
		return jiraClient;
	}
	
	static RestClient getRestClient() {
		return restClient;
	}
	
}
