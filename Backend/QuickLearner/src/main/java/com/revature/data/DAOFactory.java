package com.revature.data;


public class DAOFactory {

	private static DAOFactory self = null;
	
	private static PersonDAO personDAO = null;
	

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
	
}
