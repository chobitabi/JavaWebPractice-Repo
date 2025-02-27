package dao;

import java.util.List;

import domain.Member;

//「どんな操作をするのか？」ルールを決めている
//実際のDB操作はMemberDaoImplで実装

public interface MemberDao {
	List<Member> findAll() throws Exception;
	Member findById(Integer id) throws Exception;
	void insert(Member member) throws Exception;
	void update(Member member) throws Exception;
	void delete(Member member) throws Exception;

}
