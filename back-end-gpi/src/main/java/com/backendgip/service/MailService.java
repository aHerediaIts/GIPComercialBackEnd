//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

public interface MailService {
	void sendSimpleMail(String sentTo, String subject, String message);

	void sendMailWithAttachment(String sentTo, String subject, String message)
			throws MessagingException, UnsupportedEncodingException;
}
