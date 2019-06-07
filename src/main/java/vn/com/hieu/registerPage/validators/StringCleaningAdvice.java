package vn.com.hieu.registerPage.validators;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

//@ControllerAdvice
public class StringCleaningAdvice {

	public static class StringModelCleaner extends PropertyEditorSupport {
		
		private static final Logger LOGGER = Logger.getLogger(StringModelCleaner.class);
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if(text == null) {
				setValue(null);
			} else {
				LOGGER.debug("Original text = " + text);
				String sanString = Jsoup.clean(text, Whitelist.basic());
				LOGGER.debug("Sanitzed text = " + sanString);
				setValue(sanString);
			}
		}

		@Override
		public String getAsText() {
			String value = (String) getValue();
			if(value == null) {
				return value;
			} else {
				return Jsoup.clean(value, Whitelist.basic());
			}
		}
		
		
		
	}
	
//	@InitBinder
//	public void bindStringModelCleaner(WebDataBinder binder) {
//		StringModelCleaner cleaner = new StringModelCleaner();
//		binder.registerCustomEditor(String.class, cleaner);
//	}
//	
}
