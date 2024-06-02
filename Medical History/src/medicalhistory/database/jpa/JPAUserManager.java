package medicalhistory.database.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import medicalhistory.database.interfaces.UserManager;
import medicalhistory.database.pojos.Role;
import medicalhistory.database.pojos.User;

	public class JPAUserManager implements UserManager {

		private EntityManager em;
		
		public JPAUserManager() {
			em = Persistence.createEntityManagerFactory("medicalhistory-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			// Create default roles
			// If they don't exist already
			try {
		        em.getTransaction().begin();
		        createRole(new Role("Administrator"));
		        em.getTransaction().commit();
		    } catch (PersistenceException e) {
		        em.getTransaction().rollback();
		        // Log or handle the exception appropriately
		        e.printStackTrace();
		    }

		    try {
		        em.getTransaction().begin();
		        createRole(new Role("Doctor"));
		        createRole(new Role("Patient"));
		        em.getTransaction().commit();
		    } catch (PersistenceException e) {
		        em.getTransaction().rollback();
		        // Log or handle the exception appropriately
		        e.printStackTrace();
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
			Role existingRole = getRole(r.getName());
		    if (existingRole == null) {
		        try {
		            em.getTransaction().begin();
		            em.persist(r);
		            em.getTransaction().commit();
		        } catch (PersistenceException e) {
		            em.getTransaction().rollback();
		            // Log or handle the exception appropriately
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("Role '" + r.getName() + "' already exists.");
		       
		    }
		}
		

		@Override
		public Role getRole(String name) {
			Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
			q.setParameter(1, name);
			System.out.println(name+" "+q.getResultList().toString());
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
		
		@Override
		public void ChangeUser(User a, String username, String password) {
		     
		    try {   a.setPassword(password);
		    	a.setUsername(username);
		    	em.getTransaction().begin();
		    	em.merge(a);
		        em.getTransaction().commit();
		        
		      
		    } catch (Exception e) {
		        em.getTransaction().rollback();
		        throw e; // Re-throw the exception after rolling back the transaction
		    }  
		    
		}

		@Override
		public User getUserByUsername(String username) {
			User u = null;
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? ", User.class);
			q.setParameter(1, username);
			try {
				u = (User) q.getSingleResult();
				
			} catch (NoResultException e) {
				em.getTransaction().rollback();
				return null;
			}return u;	
		}
		@Override
		public void deleteUser(String username) {
			try {
	            Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? ", User.class);
	            q.setParameter(1, username);
	            User user = (User) q.getSingleResult();
	            em.getTransaction().begin();
	            em.remove(user);
	            em.getTransaction().commit();
	            
	        } catch (NoResultException e) {
	        	em.getTransaction().rollback();
	            // Manejar el caso donde no se encuentra el usuario
	            System.out.println("No user found with the given username.");
	        } 
	        
		}

}
