package osbcookbook.util.validation;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class ValidationConfiguration {
	private boolean valdiationEnabled = true;
	
	@ManagedAttribute
	public boolean isValidationEnabled() {
		return valdiationEnabled;
	}

	@ManagedAttribute
	public void setValidationEnabled(boolean validationEnabled) {
		this.valdiationEnabled = validationEnabled;
	}

}
