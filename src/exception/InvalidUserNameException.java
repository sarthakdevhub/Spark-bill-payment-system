package exception;

public class InvalidUserNameException extends Exception {
	@Override
	public String toString() {
		return "Username Already Taken";
	}
}
