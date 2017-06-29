package com.niit.chattzz.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.chattzz.dao.UserDao;
import com.niit.chattzz.dao.UserDao;
import com.niit.chattzz.domain.User;

@SuppressWarnings("deprecation")
@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDao {

	// private static final Logger Logger =
	// LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// save user
	public User save(User user) {

		try {

			user.setRole("ROLE_USER");
			user.setEnabled("true");
			sessionFactory.getCurrentSession().save(user);

		} catch (HibernateException e) {
			System.out.println("exception occured while saving user******");
			e.printStackTrace();

		}

		return user;
	}

	@Transactional
	public boolean update(User user) {

		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> listUsers() {

		String hql = "from User";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		List<User> listUser = query.list();
		if (listUser == null || listUser.isEmpty()) {
			return null;

		}
		return query.list();
	}

	@Transactional
	public User get(String id) {

		String hql = "from User where id = " + "'" + id + "'";

		@SuppressWarnings({ "rawtypes" })
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<User> list = query.list();
		if (list == null || list.isEmpty()) {

			return null;
		} else {
			return list.get(0);
		}
	}

	@Transactional
	public User delete(int id) {
		User UserToDelete = new User();
		UserToDelete.setId(id);

		sessionFactory.getCurrentSession().delete(UserToDelete);
		return null;
	}

	@Transactional
	public User authenticate(String name, String password) {
		System.out.println("authenticate hit*********");
		String hql = "from User where name = " + "'" + name + "' and " + " password='" + password + "'";
		@SuppressWarnings({ "rawtypes" })
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
                      sessionFactory.getCurrentSession().flush();
                     
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		System.out.println(list.toString());
		if (list != null && !list.isEmpty()) {
			System.out.println(list.get(0).toString());
			return list.get(0);
		}
		 //sessionFactory.getCurrentSession().close();
		return null;
	}

	@Transactional
	public void setOffLine(String loggedInUserID) {
		// Logger.debug("Starting of the method setOnline");
		String hql = "UPDATE User SET isOnline = 'N' where userID ='" + loggedInUserID + "'";
		// Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		// Logger.debug("Ending of the method setOnline");
	}

	@Transactional
	public void setOnline(String loggedInUserID) {
		// Logger.debug("Starting of the method setOffline");
		String hql = "UPDATE User SET isOnline = 'Y' where userID = '" + loggedInUserID + "'";
		// Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		// Logger.debug("Ending of the method setOffline");

	}

}
