package app;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import web.member.pojo.Member;

public class Testapp {
	
	public static void main(String[] args) {
		Testapp testapp = new Testapp();
		Member member = new Member();
		
//		member.setUsername("小豬佩奇");
//		member.setPassword("9487");
//		member.setNickname("佩奇");
//		testapp.insert(member);
//		System.out.println(member.getId());
		
//		int i = testapp.deleteById(3);
//		System.out.println(i);
		
//		Member newMember = new Member();
//		newMember.setId(4);
//		newMember.setPass(false);
//		newMember.setRoleId(1);
//		System.out.println(testapp.updateById(newMember));
		
		System.out.println(testapp.selectById(4).getNickname());
	}
	
	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //Datasource
		Session session = sessionFactory.openSession(); //con
		try {
			Transaction transaction= session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return null;
	}

	public int deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //Datasource
		Session session = sessionFactory.openSession(); //con
		try {
			Transaction transaction= session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return -1;
	}
	
	public int updateById(Member newMember) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //Datasource
		Session session = sessionFactory.openSession(); //con
		try {
			Transaction transaction= session.beginTransaction();
			Member oldMember = session.get(Member.class, newMember.getId());
			
			//若有傳pass,就將此pass設定給oldMember
			if(newMember.getPass() != null) {
				oldMember.setPass(newMember.getPass());
			}
			//若有傳roldId,就將此pass設定給oldMember
			if(newMember.getRoleId() != null) {
				oldMember.setRoleId(newMember.getRoleId());
			}
			transaction.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return -1;
	}
	
	public Member selectById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //Datasource
		Session session = sessionFactory.openSession(); //con
		try {
			Transaction transaction= session.beginTransaction();
			Member member = session.get(Member.class, id);
			transaction.commit();
			return member;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return null;
	}
}

