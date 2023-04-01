package dto;

import java.time.LocalDate;

public class BilldetailDTOImpl implements BilldetailDTO {
	LocalDate paymentDate;
	LocalDate dueDate;
	double amountPaid;
	double unit;
	
	public BilldetailDTOImpl() {}
	public BilldetailDTOImpl(LocalDate paymentDate, LocalDate dueDate, double amountPaid, double unit) {
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
		this.amountPaid = amountPaid;
		this.unit = unit;
	}
	
	@Override
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	@Override
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public LocalDate getDueDate() {
		return dueDate;
	}
	@Override
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public double getAmountPaid() {
		return amountPaid;
	}
	@Override
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public double getUnit() {
		return unit;
	}
	@Override
	public void setUnit(double unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return paymentDate + "    | " + dueDate + "  |   " + amountPaid + "      |   " + unit;
	}
	
	
	
}
