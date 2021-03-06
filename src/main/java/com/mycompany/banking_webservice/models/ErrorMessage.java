/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking_webservice.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianarbuckle
 */
@XmlRootElement
public class ErrorMessage {
    
    private String message;
    private int errorCode;
    
    public ErrorMessage() {
        
    }

    public ErrorMessage(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    } 
    
}
