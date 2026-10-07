package entities;

public class Payment {

	private Double value;
	private Boolean pay;

	public Payment(Double value) {
		this.value = value;
		this.pay = false;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getPay() {
		return pay;
	}

	public void setPay(Boolean pay) {
		this.pay = pay;
	}

}
