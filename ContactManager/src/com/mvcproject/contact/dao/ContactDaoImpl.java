package com.mvcproject.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mvcproject.contact.model.ContactEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ContactDaoImpl implements ContactDao {

	private final JdbcTemplate jdbcTemplate;

//	public ContactDaoImpl(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}

	@Override
	public int save(ContactEntity contact) {
		String saveSql = "insert into contact(name, email, phone, address) values (?, ?, ?, ?)";

		return  jdbcTemplate.update(saveSql, 
				contact.getName(),
				contact.getEmail(), contact.getPhone(), contact.getAddress()
				);
	}

	@Override
	public int update(ContactEntity contact) {
		String saveSql = "update contact set name=?,email=?,phone=?,address=? where contact_id=?";
		return jdbcTemplate.update(saveSql, 
				contact.getName(),
				contact.getEmail(), contact.getPhone(), contact.getAddress(),
				contact.getId());
	}

	@Override
	public ContactEntity get(int id) {
		String sql = "select * from contact where contact_id="+ id;

		ResultSetExtractor<ContactEntity> rs= new ResultSetExtractor<ContactEntity>() {

			@Override
			public ContactEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String address = rs.getString("address");
					return ContactEntity.builder()
							.id(id)
							.name(name)
							.email(email)
							.phone(phone)
							.address(address)
							.build();
				}
				return null;
			}

		};
		return jdbcTemplate.query(sql, rs);
	}

	@Override
	public int delete(int id) {
		String sql ="delete from contact where contact_id="+ id;
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<ContactEntity> getAll() {
		String sql ="select * from contact";

		RowMapper<ContactEntity> rowMapper = new RowMapper<ContactEntity>() {
			@Override
			public ContactEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					int id = rs.getInt(1);
					String name = rs.getString("name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String address = rs.getString("address");
					return  new ContactEntity(id, name, email,address,  phone );
			}
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

}
