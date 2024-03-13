package coid.bcafinance.bpsringbootfinal.configuration;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 04/03/2024 9:58
@Last Modified 04/03/2024 9:58
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.core.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:midtrans.properties")
public class MidtransConfig {
    private static String serverKey;
    private static String isProduction;

    @Value("${midtrans.serverKey}")
    public static void setServerKey(String serverKey) {
        MidtransConfig.serverKey = Crypto.performDecrypt(serverKey);;
    }

    public static void setIsProduction(String isProduction) {
        MidtransConfig.isProduction = isProduction;
    }

    public static String getServerKey() {
        return serverKey;
    }

    public static String getIsProduction() {
        return isProduction;
    }
}
