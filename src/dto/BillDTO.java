package dto;

import java.time.LocalDate;

public interface BillDTO {
	public int getid();
	public void setid(int id);
	public String getcID();
	public void setcID(String cID);
	public double getUnit();
	public void setUnit(double unit);
	public LocalDate getStart();
	public void setStart(LocalDate start);
	public LocalDate getEnd();
	public void setEnd(LocalDate end);
	public double getFcharge();
	public void setFcharge(double fcharge);
	public double getTax();
	public void setTax(double tax);
	public double getTotalAmount();
	public void setTotalAmount(double totalAmount);
	public double getAmountDue();
	public void setAmountDue(double amountDue);
	public double getAmountPaid();
	public void setAmountPaid(double amountPaid);
	public LocalDate getDueDate();
	public void setDueDate(LocalDate dueDate);
	public LocalDate getPaymentDate();
	public void setPaymentDate(LocalDate paymentDate);
	public int getIsPaid();
	public void setIsPaid(int isPaid);
}
