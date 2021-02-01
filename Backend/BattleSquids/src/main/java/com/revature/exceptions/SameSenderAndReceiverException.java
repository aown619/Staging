package com.revature.exceptions;

public class SameSenderAndReceiverException extends Exception {
	public SameSenderAndReceiverException() {
		super("Invites cannot have the same sender and receiver.");
	}
}
