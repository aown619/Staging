package com.revature.data;


public class DAOFactory {

	private static DAOFactory self = null;
	
	private static PersonDAO personDAO = null;
	private static TileDAO tileDAO = null;
	private static TileStatusDAO tStatDAO = null;
	
	private static InviteDAO inviteDAO = null;
	
	private static InviteStatusDAO inviteStatusDAO = null;
	
	private static InviteTypeDAO inviteTypeDAO = null;
	
	private static ChatDAO chatDAO = null;
	
	private static GameDAO gameDAO= null;
	
	private static GameStatusDAO gamestatusDAO= null;

	private static BoardDAO boardDAO = null;
	
	private static MatchHistoryDAO matchDAO = null;
	
	private static SquidDAO squidDAO = null;

	private DAOFactory()
	{
	}
	
	public static DAOFactory getDAOFactory()
	{
		if (self == null)
		{
			self = new DAOFactory();
		}
		return self;
	}
	
	public static PersonDAO getPersonDAO() {
		if (personDAO == null) {
			personDAO = new PersonHibernate();
		}
		return personDAO;
	}
	
	public static InviteDAO getInviteDAO() {
		if(inviteDAO == null)
		{
			inviteDAO = new InviteHibernate();
		}
		return inviteDAO;
	}
	
	public static InviteStatusDAO getInviteStatusDAO() {
		if(inviteStatusDAO == null)
		{
			inviteStatusDAO = new InviteStatusHibernate();
		}
		return inviteStatusDAO;
	}
	
	public static InviteTypeDAO getInviteTypeDAO() {
		if(inviteTypeDAO == null)
		{
			inviteTypeDAO = new InviteTypeHibernate();
		}
		return inviteTypeDAO;
	}
	
	public static TileDAO getTileDAO()
	{
		if(tileDAO == null)
		{
			tileDAO = new TileHibernate();
		}
		return tileDAO;
	}
	
	public static TileStatusDAO getTileStatusDAO()
	{
		if(tStatDAO == null)
		{
			tStatDAO = new TileStatusHibernate();
		}
		return tStatDAO;
	}
	
	public static ChatDAO getChatDAO()
	{
		if(chatDAO == null)
		{
			chatDAO = new ChatHibernate();
		}
		return chatDAO;
	}
	public static GameDAO getGameDAO()
	{
		if(gameDAO == null)
		{
			gameDAO = new GameHibernate();
		}
		return gameDAO;
	}
	
	public static GameStatusDAO getGameStatusDAO() {
		if(gamestatusDAO==null) 
		{
			gamestatusDAO= new GameStatusHibernate();
		}
		return gamestatusDAO;
	}
	
	public static BoardDAO getBoardDAO()
	{
		if(boardDAO == null)
		{
			boardDAO = new BoardHibernate();
		}
		return boardDAO;
	}
	
	public static MatchHistoryDAO getMatchHistoryDAO()
	{
		if(matchDAO == null)
		{
			matchDAO = new MatchHistoryHibernate();
		}
		return matchDAO;
	}
	
	public static SquidDAO getSquidDAO()
	{
		if(squidDAO == null)
		{
			squidDAO = new SquidHibernate();
		}
		return squidDAO;
	}
}
