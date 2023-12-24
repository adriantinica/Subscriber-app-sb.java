package tinica.example.tasks;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tinica.example.models.Message;
import tinica.example.models.Subscriber;
import tinica.example.repos.MessageRepo;

@Component
public class EmailScheduler {
	
	@Autowired
	MessageRepo messageRepo;
	@Autowired
    private JavaMailSender mailSender;
	Map<Subscriber,Message> tuple1 = new HashMap<Subscriber, Message>();

	@Scheduled(fixedRate = 60000,initialDelay = 10000 )
	public void sendEmail() {
		System.err.println("Preparing to send message !!!");
		//   this new Map             =     this returned Map
		//       |                                  |
		//       v                                  v
		Map<Subscriber,Message> tuple = messageRepo.getNextUnsentMessage();
		System.err.println(tuple);
		
		//Sending the email:
		
		if (!tuple.isEmpty()) {
			
		    Map.Entry<Subscriber, Message> entry = tuple.entrySet().iterator().next();
		    
		    Subscriber subscriber = entry.getKey();
		    Message messageContent = entry.getValue();
		    
		    Map<Subscriber,Message> sentMessages = messageRepo.countNextsentMessage();
		    
		    	//while(messageContent.getContent() != null && !sentMessages.equals(tuple)){
		    	
			    SimpleMailMessage message = new SimpleMailMessage(); 
			    message.setFrom("testjavatinica@gmail.com");
			    message.setTo(subscriber.getEmail()); 
			    message.setSubject("Newsletter"); 
			    message.setText(messageContent.getContent());
	
			    mailSender.send(message);
			    //System.out.println(subscriber.getId());
			 /// if is no exceptions appeared
			    messageRepo.markMessageAsSent(subscriber.getId());
			    
			    //if(sentMessages.equals(tuple)){
			    	//System.out.println("All the messages were sent!!!");
			    //}	
			    //}
			    
			    
		    }else {
		    	System.err.println("Nothing to send !!!");
		    	
		    }
		    
		   
		
         }
	}

	

