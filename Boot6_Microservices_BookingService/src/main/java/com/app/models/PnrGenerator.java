package com.app.models;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PnrGenerator {
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();
 
    public static String generatePnr() {
        StringBuilder pnr = new StringBuilder("PNR");
        
       
        String datePart = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        pnr.append(datePart);
        
        
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(CHARACTERS.length());
            pnr.append(CHARACTERS.charAt(index));
        }
        
        return pnr.toString();
    }
}