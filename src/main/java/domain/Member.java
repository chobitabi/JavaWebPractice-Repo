package domain;

import java.util.Date;

//Memberクラスは、会員の情報をまとめるための「箱」 
public class Member {

	//フィールド
	private Integer id;
	private String name;
	private Integer age;
	private String address;
	private Integer typeId;
	private Date created;
	private String typeName;

	//コンストラクタ
	public Member() {

	}

	public Member(Integer id, String name, Integer age, String address, Integer typeId, Date created, String typeName) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.typeId = typeId;
		this.created = created;
		this.typeName = typeName;
	}

	//アクセッサ
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;

	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
