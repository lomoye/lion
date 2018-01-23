package com.lomoye.lion.core.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * 发送给管理员邮件通知的服务.
 */
public class MailUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

    public static boolean sendMail(String mailTo, String mailCc, String title, String content) {
        List<String> mailToList = new ArrayList<>();
        List<String> mailCcList = new ArrayList<>();
        if (!Strings.isNullOrEmpty(mailTo)) {
            mailToList.addAll(Splitter.on(",").omitEmptyStrings().trimResults().splitToList(mailTo));
        }
        if (!Strings.isNullOrEmpty(mailCc)) {
            mailCcList.addAll(Splitter.on(",").omitEmptyStrings().trimResults().splitToList(mailCc));
        }
        return sendMail(mailToList, mailCcList, title, content);
    }

    public static boolean sendMail(List<String> mailToList, List<String> mailCcList, String title, String content) {
        try {
            doSendMail(mailToList, mailCcList, title, content);
            return true;
        } catch (Exception e) {
            LOGGER.warn("send email failed|" + title, e);
            return false;
        }
    }

    public static void doSendMail(List<String> mailToList, List<String> mailCcList, String title, String content) throws MessagingException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        // 发件人的账号
        props.put("mail.user", "yechangjun@quannengzhanggui.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "Qaz6363");

        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        for (String mailTo : mailToList) {
            InternetAddress to = new InternetAddress(mailTo);
            message.addRecipient(RecipientType.TO, to);
        }

        // 设置抄送
        for (String mailCC : mailCcList) {
            InternetAddress cc = new InternetAddress(mailCC);
            message.addRecipient(MimeMessage.RecipientType.CC, cc);
        }

        // 设置邮件标题
        message.setSubject(title);

        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
    }
}
