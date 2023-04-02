package dto;

import java.time.LocalDate;

public class BillDTOImpl implements BillDTO {
	int id;
	String cID;
	double unit;
	LocalDate start;
	LocalDate end;
	double fcharge;
	double tax;
	double totalAmount;
	double amountDue;
	double amountPaid;
	LocalDate dueDate;
	LocalDate paymentDate;
	int isPaid;
	
	public BillDTOImpl() {}
	public BillDTOImpl(int id,String cID, double unit, LocalDate start, LocalDate end, double fcharge, double tax,
			double totalAmount, double amountDue, double amountPaid, LocalDate dueDate, LocalDate paymentDate,
			int isPaid) {
		this.id = id;
		this.cID = cID;
		this.unit = unit;
		this.start = start;
		this.end = end;
		this.fcharge = fcharge;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.amountDue = amountDue;
		this.amountPaid = amountPaid;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.isPaid = isPaid;
	}
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id=id;
	}
	public String getcID() {
		return cID;
	}
	public void setcID(String cID) {
		this.cID = cID;
	}
	public double getUnit() {
		return unit;
	}
	public void setUnit(double unit) {
		this.unit = unit;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	public double getFcharge() {
		return fcharge;
	}
	public void setFcharge(double fcharge) {
		this.fcharge = fcharge;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	@Override
	public String toString() {
		return  cID +"       |  "+  unit +"  |  "+  start +"  |  "+ end +"  |  "+  fcharge  +"  |  "+ tax +"  |  "+ totalAmount +"  |  "+
	amountDue+"  |  "+  amountPaid +"  |  "+  dueDate +"  |  "+  paymentDate +"  |  "+ isPaid ;
	}
	
	
	
	
}
