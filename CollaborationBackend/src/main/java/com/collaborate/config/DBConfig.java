package com.collaborate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.collaborate.model.Blog;
import com.collaborate.model.BlogComment;
import com.collaborate.model.Forum;
import com.collaborate.model.Friend;
import com.collaborate.model.Job;
import com.collaborate.model.Users;

@Configuration //add Spring-context dependency to pom
@ComponentScan("com.collaborate")
@EnableTransactionManagement
public class DBConfig {
	
	
	//1]creating a datasource object which is used for local session factory
	public DataSource getOracleDataSource()
	{
		DriverManagerDataSource driverManagerDataSource= new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		driverManagerDataSource.setUsername("system");
		driverManagerDataSource.setPassword("system");
		return driverManagerDataSource;
	}
	
	//2] creating hibernate properties which is used by local session factory
	public Properties getHibernateProperties()
	{
		Properties properties= new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		return properties;
	}
	
	//3]creating session factory bean using local session factory
	@Bean
	public SessionFactory getSessionFactory() //add hibernate-core dependency for SessionFactory
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder= new LocalSessionFactoryBuilder(getOracleDataSource());
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		localSessionFactoryBuilder.addAnnotatedClass(Blog.class);
		localSessionFactoryBuilder.addAnnotatedClass(Forum.class);
		localSessionFactoryBuilder.addAnnotatedClass(Users.class);
		localSessionFactoryBuilder.addAnnotatedClass(Job.class);
		localSessionFactoryBuilder.addAnnotatedClass(Friend.class);
		localSessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		System.out.println("Session Factory Bean Created........");
		return localSessionFactoryBuilder.buildSessionFactory();
	}
	//4] hibernate transaction bean
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		//HibernateTransactionManager Used for all crud operations
		return new HibernateTransactionManager(sessionFactory);
	}
	
	//5] application specific dao bean
	/*@Bean
	public BlogDAO getBlogDAO(SessionFactory sessionFactory)
	{
		return new BlogDAOImpl(sessionFactory);
	}
	@Bean
	public ForumDAO getForumDAO(SessionFactory sessionFactory)
	{
		return new ForumDAOImpl(sessionFactory);
	}
	@Bean
	public UsersDAO getUsersDAO(SessionFactory sessionFactory)
	{
		return new UsersDAOImpl(sessionFactory);
	}
*/
}
