/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

public class NoSessionException extends Exception {

    private static final long serialVersionUID = 5543012249884572720L;

    public NoSessionException() {
        super();
    }

    public NoSessionException(String detailMessage) {
        super(detailMessage);
    }

    public NoSessionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NoSessionException(Throwable throwable) {
        super(throwable);
    }
}
