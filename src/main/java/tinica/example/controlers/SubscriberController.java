package tinica.example.controlers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tinica.example.models.Subscriber;
import tinica.example.repos.SubscriberRepo;

@RestController
public class SubscriberController {
	
	@Autowired
	SubscriberRepo subscriberRepo;
	
	
	@GetMapping("/subscribers")    //GetMapping- Enter 
	public List<Subscriber> subscriberIndex() {
		return subscriberRepo.getSubscribers();
		
		
	}
	
	@GetMapping("/subscribers/add/{email}/{name}")
	public String subscriberAdd(@PathVariable String email, @PathVariable String name) {
		
		subscriberRepo.save(new Subscriber(name, email));
		
		return "Subscriber ADDED !";
	}
	
	@GetMapping("/subscribers/setname/{email}/{newName}")
	public String subscriberSetName(@PathVariable String email, @PathVariable String newName) {
		
		subscriberRepo.updateName(email, newName);
		
		return "Subscriber NAME UPDATED Successful !";
	}

}
