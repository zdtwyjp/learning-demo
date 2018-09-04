package org.tech.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {
	private static final long serialVersionUID = -2305947922647847840L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, length = 20)
	private String account;

	@Column(nullable = false, length = 20)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toLogString() {
		return null;
	}

}
