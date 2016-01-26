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
	 *            �ռ��˵�ַ�����á���������
	 * @param mailSubject
	 *            �ʼ�����
	 * @param mailText
	 *            �ʼ�������
	 * @return ���͵�����
	 */
	public static int send(String mailTos, String mailSubject, String mailText) {
		if (mailTos == null || mailSubject == null || mailText == null)
			return 0;

		String encoding = "iso-8859-1";
		try {
			// ���ⲻ��Ҫ���룬������Ҫ����
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
		// ����������
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						props.getProperty("mail.smtp.from"),
						props.getProperty("mail.psw"));
			}
		};
		Session session = Session.getInstance(props, auth);
		session.setDebug(false);// ����ӡ������Ϣ

		Message msg = new MimeMessage(session);
		int sendNum = 0;// ���͵�����

		for (String mailTo : mailTos.split(",")) {
			try {
				InternetAddress[] addresses = { new InternetAddress(mailTo) };
				msg.setRecipients(Message.RecipientType.TO, addresses);// �����ʼ����յ�ַ��
				msg.setSentDate(new java.util.Date());// �����ʼ���������
				msg.setSubject(mailSubject);// �����ʼ��ı���
				msg.setFrom(new InternetAddress(MimeUtility.encodeText("���ƴ��� Sense Maker")+"<"+props.getProperty("mail.smtp.from")+">"));
				// msg.setText(mailText);// �����ʼ�������(�ı�)
				msg.setContent(mailText, "text/html");// �����ʼ�������
				Transport.send(msg);// �����ʼ�

				sendNum++;// ���ͼ���
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
