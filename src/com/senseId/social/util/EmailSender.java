package com.senseId.social.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.MailSender;
import org.springframework.stereotype.Component;

@Component("emailSender")
public class EmailSender {

	private static Properties props = new Properties();
	
	/**
	 * 
	 * @param mailTos
	 *            收件人地址，可用“，”隔开
	 * @param mailSubject
	 *            邮件主题
	 * @param mailText
	 *            邮件的内容
	 * @return 发送的数量
	 */
	public static int send(String mailTos, String mailSubject, String mailText) {
		if (mailTos == null || mailSubject == null || mailText == null)
			return 0;

		String encoding = "iso-8859-1";
		try {
			// 标题不需要编码，正文需要编码
			mailText = new String(mailText.getBytes(), encoding);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Fail to encoding mail text to " + encoding);
		}

		try {
			props.load(MailSender.class.getClassLoader().getResourceAsStream(
					"mail.properties"));
		} catch (IOException e1) {
			System.out
					.println("Fail to load config files from classpath,please recheck the mail.properties file");
			e1.printStackTrace();
		}
		// 构建发送者
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						props.getProperty("mail.smtp.from"),
						props.getProperty("mail.psw"));
			}
		};
		Session session = Session.getInstance(props, auth);
		session.setDebug(false);// 不打印发送信息

		Message msg = new MimeMessage(session);
		int sendNum = 0;// 发送的数量

		for (String mailTo : mailTos.split(",")) {
			try {
				InternetAddress[] addresses = { new InternetAddress(mailTo) };
				msg.setRecipients(Message.RecipientType.TO, addresses);// 设置邮件接收地址集
				msg.setSentDate(new java.util.Date());// 设置邮件发送日期
				msg.setSubject(mailSubject);// 设置邮件的标题
				msg.setFrom(new InternetAddress(MimeUtility.encodeText("云云创客 Sense Maker")+"<"+props.getProperty("mail.smtp.from")+">"));
				// msg.setText(mailText);// 设置邮件的内容(文本)
				msg.setContent(mailText, "text/html");// 设置邮件的内容
				Transport.send(msg);// 发送邮件

				sendNum++;// 发送记数
				System.out.println("Success to send email to " + mailTo);
			} catch (MessagingException e) {
				System.out.println("Fail to send email for:" + e);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sendNum;
	}

}
