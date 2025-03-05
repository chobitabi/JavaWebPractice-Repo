package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import domain.Member;

//DBからデータを取得する具体的な処理を担当

public class MemberDaoImpl implements MemberDao {
	private DataSource ds;

	public MemberDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	//membersテーブルの全データを取得
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
	//会員一人分のデータを取得
	public Member findById(Integer id) throws Exception {
		Member member = new Member();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
					+ " members.id, members.name, members.age,"
					+ " members.address, members.type_id, members.created,"
					+ " member_types.name as type_name"
					+ " FROM members"
					+ " JOIN member_types"
					+ " ON members.type_id = member_types.id"
					+ " WHERE members.id = ?";
			PreparedStatement stmt = con.prepareStatement(sql); //SQLを実行
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() == true) {
				member = mapToMember(rs); //取得したデータをMemberクラスのオブジェクトに変換
			}
		} catch (Exception e) {
			throw e;
		}
		return member;
	}

	@Override
	//新しい会員を追加
	public void insert(Member member) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO members"
					+ "(name, age, address, type_id, created)"
					+ "VALUES (?, ?, ?, ?, NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setObject(2, member.getAge(), Types.INTEGER);
			stmt.setString(3, member.getAddress());
			stmt.setObject(4, member.getTypeId(), Types.INTEGER);
			stmt.executeUpdate(); //DBにデータを挿入
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	//データ更新
	public void update(Member member) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE members"
					+ " SET name = ?, age = ?, address = ?, type_id = ?"
					+ " WHERE id = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setObject(2, member.getAge(), Types.INTEGER);
			stmt.setString(3, member.getAddress());
			stmt.setObject(4, member.getTypeId(), Types.INTEGER);
			stmt.setObject(5, member.getId(), Types.INTEGER);
			stmt.executeUpdate(); //DBにデータを挿入

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	//データ削除
	public void delete(Member member) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM members WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, member.getId(), Types.INTEGER);
			stmt.executeUpdate(); //DBにデータを挿入

		} catch (Exception e) {
			throw e;
		}

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
