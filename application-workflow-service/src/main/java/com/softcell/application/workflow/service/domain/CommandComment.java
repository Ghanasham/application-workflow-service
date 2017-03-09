package com.softcell.application.workflow.service.domain;

public class CommandComment {

	public enum Command{
		APPROVE,
		REJECT
	}
	
	private Command command;
	
	private String comment;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
