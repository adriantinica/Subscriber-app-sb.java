package tinica.example.models;

import org.springframework.stereotype.Component;


public class Message {

	
		private Integer id;
		private String content;
		
		
		public Message() {
			super();
		}


		public Message(String content) {
			super();
			this.content = content;
		}


		public Message(Integer id, String content) {
			super();
			this.id = id;
			this.content = content;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		@Override
		public String toString() {
			return "message [ content=" + content + "]";
		}
		
		
		
		
		
		
	}

