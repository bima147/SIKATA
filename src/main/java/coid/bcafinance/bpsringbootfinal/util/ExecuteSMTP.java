package coid.bcafinance.bpsringbootfinal.util;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 07/03/2024 15:42
@Last Modified 07/03/2024 15:42
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.configuration.OtherConfig;
import coid.bcafinance.bpsringbootfinal.core.SMTPCore;

public class ExecuteSMTP {
    private StringBuilder stringBuilder = new StringBuilder();
    private String [] strException = new String[2];

    public ExecuteSMTP() {
        strException[0] = "ExecuteSMTP";
    }

    /*
        Untuk hit 1 User saja
     */
    public Boolean sendSMTPToken(String mailAddress, String subject, String [] strVerification,String pathFile)
    {
        try
        {
//            System.out.println("Try sendSMTPToken => start");
            if(OtherConfig.getFlagSmtpActive().equalsIgnoreCase("y") && !mailAddress.equals(""))
            {
                String strContent = new ReadTextFileSB(pathFile).getContentFile();
//                System.out.println("Try sendSMTPToken => "+ strContent);
                strContent = strContent.replace("#JKVM3NH",strVerification[0]);//Kepentingan
                strContent = strContent.replace("XF#31NN",strVerification[1]);//Nama Lengkap
                strContent = strContent.replace("8U0_1GH$",strVerification[2]);//TOKEN

                String [] strEmail = {mailAddress};
                SMTPCore sc = new SMTPCore();
                return  sc.sendMailWithAttachment(strEmail,
                        subject,
                        strContent,
                        "SSL",null);
            }
        }
        catch (Exception e)
        {
            strException[1]="sendSMTPToken(String mailAddress, String subject, String purpose, String token) -- LINE 38";
            LoggingFile.exceptionStringz(strException,e, OtherConfig.getFlagLoging());
            return false;
        }
        return true;
    }

    /*
        Untuk hit User yang banyak
     */
    public Boolean sendSMTPToken(String[] mailAddress, String subject, String [] strVerification,String pathFile)
    {
        try
        {

            if(OtherConfig.getFlagSmtpActive().equalsIgnoreCase("y") && !mailAddress.equals(""))
            {
                String strContent = new ReadTextFileSB(pathFile).getContentFile();
                strContent = strContent.replace("#JKVM3NH",strVerification[0]);//Kepentingan
                strContent = strContent.replace("XF#31NN",strVerification[1]);//Nama Lengkap
                strContent = strContent.replace("8U0_1GH$",strVerification[2]);//TOKEN

                String [] strEmail = mailAddress;
                SMTPCore sc = new SMTPCore();
                return  sc.sendMailWithAttachment(strEmail,
                        subject,
                        strContent,
                        "SSL",null);
            }
        }
        catch (Exception e)
        {
            strException[1]="sendSMTPToken(String mailAddress, String subject, String purpose, String token) -- LINE 38";
            LoggingFile.exceptionStringz(strException,e, OtherConfig.getFlagLoging());
            return false;
        }
        return true;
    }
}
