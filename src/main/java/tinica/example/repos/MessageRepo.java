package tinica.example.repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import tinica.example.mappers.MessageMapper;
import tinica.example.models.Message;
import tinica.example.models.Subscriber;

@Repository
public class MessageRepo {
   
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SubscriberRepo subscriberRepo;
	
	 
	public void saveMessage(Message message) {
		String sql = "INSERT INTO public.messages(\r\n"
				+ "	 content)\r\n"
				+ "	VALUES ('"+ message.getContent()+"');";
		
	}
	
	public Message getMessageById(Integer id) {
		String sql = "SELECT content\r\n"
				+ "	FROM public.messages\r\n"
				+ "	WHERE id=("+ id +");";
		  Message message = (Message) jdbcTemplate.query(sql, new MessageMapper()).get(0);
		return message;
	}
	
	public List<Message> allMessages(){
		
		String sql= "SELECT content\r\n"
				+ "	FROM public.messages;";
		
		List<Message> messages = jdbcTemplate.query(sql, new MessageMapper());
		
		
		return messages;
		
	}
	
	public Map<Subscriber,Message> getNextUnsentMessage() {
		Map<Subscriber,Message> tuple = new HashMap();
		
		
		String sql = "SELECT subscriber_id, message_id FROM public.subscribers_message \r\n"
				+ "WHERE sent = false LIMIT 1  OFFSET 0 ;";
		 
		SqlRowSet result= jdbcTemplate.queryForRowSet(sql);
		result.first();
		//System.err.println(">>>>>>" + result.getInt("subscriber_id")+" "+ result.getInt("message_id"));   verification
		Message message = getMessageById(result.getInt("message_id"));
		Subscriber subscriber = subscriberRepo.getSubscribersById(result.getInt("subscriber_id"));
		tuple.put(subscriber, message);
		
		
		return tuple;
	}
	
	public void markMessageAsSent(Integer id) {
		
		String sql = "UPDATE public.subscribers_message\r\n"
				+ "	SET  sent = 'true' \r\n"
				+ "	WHERE subscriber_id="+ id +" ;";
		
		jdbcTemplate.update(sql);
		
		
	}
	

	   public Map<Subscriber,Message> countNextsentMessage( ){
			Map<Subscriber,Message> tuple1 = new HashMap();
			
			
			String sql = "SELECT subscriber_id, message_id FROM public.subscribers_message \r\n"
					+ "WHERE sent = true LIMIT 1  OFFSET 0 ;";
			 
			SqlRowSet result= jdbcTemplate.queryForRowSet(sql);
			result.first();
			//System.err.println(">>>>>>" + result.getInt("subscriber_id")+" "+ result.getInt("message_id"));   verification
			Message message = getMessageById(result.getInt("message_id"));
			Subscriber subscriber = subscriberRepo.getSubscribersById(result.getInt("subscriber_id"));
			tuple1.put(subscriber, message);
			
			
			return tuple1;
		}
		
		
	
	
	
	
	
}
