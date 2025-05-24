package com.web.models;

public class SmtpDetails {

	private String smtpHost;
	private int smtpIp;
	private String user;
	private String pass;
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public int getSmtpIp() {
		return smtpIp;
	}
	public void setSmtpIp(int smtpIp) {
		this.smtpIp = smtpIp;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "SmtpDetails [smtpHost=" + smtpHost + ", smtpIp=" + smtpIp + ", user=" + user + ", pass=" + pass + "]";
	}
}
