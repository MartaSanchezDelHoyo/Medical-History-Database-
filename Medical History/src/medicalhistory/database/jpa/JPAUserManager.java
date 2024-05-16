package medicalhistory.database.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import medicalhistory.database.interfaces.UserManager;
import medicalhistory.database.pojos.Role;
import medicalhistory.database.pojos.User;

	public class JPAUserManager implements UserManager {

		private EntityManager em;
		
		public JPAUserManager() {
			em = Persistence.createEntityManagerFactory("library-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			// Create default roles
			// If they don't exist already
			try {
				this.getRole("librarian");
			} catch(NoResultException e) {
				this.createRole(new Role("Doctor"));
				this.createRole(new Role("Patient"));
				this.createRole(new Role("Hospiatl"));
			}
		}
		
		@Override
		public void register(User u) {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		}

		@Override
		public void createRole(Role r) {
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
		}

		@Override
		public Role getRole(String name) {
			Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
			q.setParameter(1, name);
			Role r = (Role) q.getSingleResult();
			return r;
		}
		
		@Override
		public List<Role> getAllRoles() {
			Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
			List<Role> roles = (List<Role>) q.getResultList();
			return roles;
		}

		@Override
		public void assignRole(User u, Role r) {
			em.getTransaction().begin();
			u.setRole(r);
			r.addUser(u);
			em.getTransaction().commit();
		}

		@Override
		public User login(String username, String password) {
			User u = null;
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, username);
			q.setParameter(2, password);
			try {
				u = (User) q.getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
			return u;
		}

}
