package dto;

import java.time.LocalDate;

public interface BilldetailDTO {
	public LocalDate getPaymentDate();

	public void setPaymentDate(LocalDate paymentDate);

	public LocalDate getDueDate();

	public void setDueDate(LocalDate dueDate);

	public double getAmountPaid();

	public void setAmountPaid(double amountPaid);

	public double getUnit();

	public void setUnit(double unit);
}
