package web.emp.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Dept {
	@Id
	private Integer deptno;
	private String dname;
	private String loc;
//	@OneToMany
//	@JoinColumn(name = "DEPTNO", referencedColumnName = "DEPTNO")
	@OneToMany(mappedBy = "dept") //@OneToMany(mappedBy = "在對方實體類別內,關聯自方的屬性名" )
	private List<Emp> emps;
}
