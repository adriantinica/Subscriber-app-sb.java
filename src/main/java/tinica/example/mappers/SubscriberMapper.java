package tinica.example.mappers;
import tinica.example.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SubscriberMapper implements RowMapper<Subscriber> {

	@Override
	public Subscriber mapRow(ResultSet resultset, int i) throws SQLException {
		
		Subscriber subscriber = new Subscriber();
		
		subscriber.setId(resultset.getInt("id")) ;	
		subscriber.setName(resultset.getString("name"));
		subscriber.setEmail(resultset.getString("email"));
		return subscriber;
		
	}
 
}
