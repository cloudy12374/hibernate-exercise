package app;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class Testapp {
	
	public static void main(String[] args) {
//		Testapp testapp = new Testapp();
//		Member member = new Member();
		
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
		
//		System.out.println(testapp.selectById(4).getNickname());
//		System.out.println(testapp.selectAll());
//		testapp.selectAll().forEach(member -> System.out.println(member.getNickname()));
//		testapp.selectAll().forEach(member -> System.out.println(member.getUsername()));
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //Datasource
		Session session = sessionFactory.openSession(); //con
//			// select USERNAME, NICKNAME
//			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//			javax.persistence.criteria.CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
//			//from Member
//			Root<Member> root = criteriaQuery.from(Member.class);
//			//where USENSME = ? and PASSWORD = ?
//			criteriaQuery.where(criteriaBuilder.and(
//					criteriaBuilder.equal(root.get("username"), "admin"),
//					criteriaBuilder.equal(root.get("password"),"P@ssw0rd")));
//			
//			//select USERNAME,NICKNAME
//			criteriaQuery.multiselect(root.get("username"), root.get("nickname"));
//			//order by ID
//			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
//			
//			Member member = session.createQuery(criteriaQuery).uniqueResult();
//			System.out.println(member.getNickname());
		
//		Dept dept = session.get(Dept.class, 30);
//		var emps = dept.getEmps();
//		for(var emp : emps) {
//			System.out.println(emp.getEname());
//		}
		Emp emp = session.get(Emp.class, 7369);
		Dept dept = emp.getDept();
		List<Emp> emps = dept.getEmps();
		for(Emp tmp : emps) {
			System.out.println(tmp.getEname());
		}
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
	
	public List<Member> selectAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //Datasource
		Session session = sessionFactory.openSession(); //con
		try {
			Transaction transaction= session.beginTransaction();
			Query<Member> query = session.createQuery(""
					+ "SELECT new web.member.pojo.Member(username, nickname) FROM Member", Member.class);
			List<Member> List = query.getResultList();
			transaction.commit();
			return List;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
		}
		return null;
	}
}

