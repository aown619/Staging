package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static HibernateUtil hu;
	private static StandardServiceRegistry standardRegistry;
	private static SessionFactory sessionFactory;
	
	private HibernateUtil() {
		super();
	}
	
	public synchronized static HibernateUtil getHibernateUtil() {
		if (hu == null) {
			hu = new HibernateUtil();
		}
		return hu;
	}
	
	public synchronized static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				standardRegistry = new StandardServiceRegistryBuilder().configure().build();
				
				Metadata meta = new MetadataSources(standardRegistry)
						.getMetadataBuilder()
						.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
						.build();
				
				sessionFactory = meta.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (standardRegistry != null) {
					StandardServiceRegistryBuilder.destroy(standardRegistry);
				}
			}

		}
		return sessionFactory;
	}
	
	public Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	public synchronized static void shutdown() {
		if (standardRegistry != null) {
			StandardServiceRegistryBuilder.destroy(standardRegistry);
		}
	}
	
}
