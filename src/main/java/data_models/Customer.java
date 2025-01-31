package data_models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer extends Person {
	private String annotation;
	private String customerId;
	private boolean problemCustomer;
	private double timefactor;
	private CustomerCategory customerCategory;
	private CustomerColour customerColour;
	private List<ColourMixture> colourMixtureList = new ArrayList<>();

	public Customer() {
		super();
		this.customerId = UUID.randomUUID().toString();
	}

	public Customer(String personId) {
	    super(personId);
	    customerId = UUID.randomUUID().toString();
	}

    public Customer(String personId, String customerId) {
        super(personId);
        this.customerId = customerId;
    }
	public Customer( String customerId, double timefactor, String annotation, char problemCustomer, String personId) {
		super(personId);
		this.annotation = annotation;
		this.customerId = customerId;
		if(problemCustomer == '0')
			this.problemCustomer = false;
		if(problemCustomer == '1')
			this.problemCustomer = true;
		this.timefactor = timefactor;
	}


    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

    public boolean isProblemCustomer() {
        return problemCustomer;
    }

    public void setProblemCustomer(boolean problemCustomer) {
        this.problemCustomer = problemCustomer;
    }

    public double getTimefactor() {
        return timefactor;
    }

    public void setTimefactor(double timefactor) {
        this.timefactor = timefactor;
    }

    public CustomerCategory getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
    }

	public CustomerColour getCustomerColour() {
		return customerColour;
	}

	public void setCustomerColour(CustomerColour customerColour) {
		this.customerColour = customerColour;
	}

	public List<ColourMixture> getColourMixtureList() {
		return colourMixtureList;
	}

	public void setColourMixtureList(List<ColourMixture> colourMixtureList) {
		this.colourMixtureList = colourMixtureList;
	}
}
