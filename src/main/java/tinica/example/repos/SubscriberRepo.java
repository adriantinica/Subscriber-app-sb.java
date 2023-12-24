package tinica.example.repos;
import tinica.example.mappers.SubscriberMapper;
import tinica.example.models.Subscriber;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SubscriberRepo {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	public List<Subscriber> getSubscribers() {
		String sql = "SELECT * FROM public.subscribers;";
		return jdbcTemplate.query(sql, new SubscriberMapper());
	}
	
	public void save ( Subscriber subscriber) {
		jdbcTemplate.update("INSERT INTO public.subscribers(\n"
							+ " name, email)\n"
							+ "	VALUES ( '"+ subscriber.getName()+"', '"+subscriber.getEmail()+"');");
	}
	
	public void updateName( String email, String newName) {
		jdbcTemplate.update("UPDATE public.subscribers\n"
					+"SET name='"+newName+"' \n" 
					+" WHERE email='"+ email+"';");
	}
	
	public void deleteSubscriber( int id) {
		jdbcTemplate.update("DELETE FROM public.subscribers \n" +
				"WHERE id ="+id+";");
		
	}
	
	public List<String> getSubscribersEmails(List <Integer> ids) {
		
		String id_values = " ";
		//ids.forEach(id -> id_values += id + "," );
		
		for (Integer id :ids ) {
			id_values += id + ",";
		}
		id_values = id_values.substring(0, id_values.length()-1);
		
		String sql = "SELECT email FROM public.subscribers WHERE id IN ("+id_values+" );";
		return jdbcTemplate.queryForList(sql, String.class);
	}
	
	public Subscriber getSubscribersById(Integer id) {
		
		String sql = "SELECT * FROM public.subscribers WHERE id= "+id+";";
		Subscriber subscriber = jdbcTemplate.query(sql, new SubscriberMapper()).get(0);
		return subscriber;
				
	}
	

	
}
