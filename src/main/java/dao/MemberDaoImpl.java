package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import domain.Member;

//データベースからデータを取得する具体的な処理を担当

public class MemberDaoImpl implements MemberDao {
	private DataSource ds;

	public MemberDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Member> findAll() throws Exception {
		List<Member> memberList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
					+ " members.id, members.name, members.age,"
					+ " members.address, members.type_id, members.created,"
					+ " member_types.name as type_name"
					+ " FROM members"
					+ " JOIN member_types"
					+ " ON members.type_id = member_types.id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				memberList.add(mapToMember(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return memberList;
	}

	@Override
	public Member findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insert(Member member) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(Member member) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Member member) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * ResultSet から Member オブジェクトへの変換
	 */
	private Member mapToMember(ResultSet rs) throws Exception {
		Integer id = (Integer) rs.getObject("id");
		String name = rs.getString("name");
		Integer age = (Integer) rs.getObject("age");
		String address = rs.getString("address");
		Integer typeId = (Integer) rs.getObject("type_id");
		Date created = rs.getTimestamp("created");
		String typeName = rs.getString("type_name");

		return new Member(id, name, age, address, typeId, created, typeName);
	}

}
