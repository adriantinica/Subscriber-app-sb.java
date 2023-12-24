package tinica.example.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tinica.example.models.Message;

public class MessageMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setContent(rs.getString("content"));
		
		return message;
	}

}
