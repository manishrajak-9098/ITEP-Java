package com.spring.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.model.User;

@Repository
public class UserDaoImplement implements UserDao {

	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImplement(JdbcTemplate jdbcTemplate) {
		this. jdbcTemplate = jdbcTemplate;
	}
	
	RowMapper<User> rowmapper = new RowMapper<User>() {
		
		
		@Override
		public User mapRow(ResultSet rs, int rowno) throws SQLException {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setEmail(rs.getString(3));
			user.setAddress(rs.getString(4));
			return user;
		}
	};
	
	@Override
	public void addUser(User user) {
		String query = "insert into userjdbctemplate (id, username, email, address) values (?,?,?,?)";
		jdbcTemplate.update(query,user.getId(), user.getUsername(), user.getEmail(), user.getAddress());
	}
	
	@Override
	public void updateUser (User user) {
		String query = "update userjdbctemplate set username=?, email=?, address=? where id=?";
		jdbcTemplate.update(query,user.getUsername(), user.getEmail(), user.getAddress(), user.getId());
	}
	
	@Override
	public void deleteUser(int id) {
		String query  = "delete from userjdbctemplate where id=?";
		jdbcTemplate.update(query, id);
		
	}
	
	@Override
	public User getUser(int id) {
		String query = "select * from userjdbctemplate where id=? ";
		return jdbcTemplate.queryForObject(query, rowmapper, id);
	}
	
	@Override
	public List<User> getAllUser(){
		String query = "select * from userjdbctemplate";
		return jdbcTemplate.query(query, rowmapper);
	}
}











