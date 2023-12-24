package tinica.example.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import tinica.example.repos.SubscriberRepo;

@Controller
public class AdminControler {

	

	
		
		@Autowired
		SubscriberRepo subscriberRepo;
		
		
		@GetMapping("/admin/subscribers")    //GetMapping- Enter 
		public String adminSubscriberIndex(Model model) {    // we use Model type object when we work with a template
		
			model.addAttribute("subscribers", subscriberRepo.getSubscribers());
			return "admin/subscribers";
			
		}
		
		@GetMapping("/admin/subscribers/compose")    //GetMapping- Enter 
		public String adminSubscriberCompose(@RequestParam List <Integer> subscribers) {  
			
			// 
			List <String> emails = subscriberRepo.getSubscribersEmails(subscribers);
			System.out.println(emails);			
			return "admin/compose";
			
		}
		
		
		
		@PostMapping("/admin/subscribers/remove")
		public String deleteSubscriber(@RequestParam (required=true) int id) {
			
			subscriberRepo.deleteSubscriber(id);
			return "Subscriber "+ id +" was removed by Admin !";
		}
}
	
