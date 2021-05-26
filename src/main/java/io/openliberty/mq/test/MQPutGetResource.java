// tag::comment[]
/*******************************************************************************
 * Copyright (c) 2017, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
 // end::comment[]
 package io.openliberty.mq.test;

import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

// tag::path[]
@ApplicationScoped
@Path("PUT-GET")
// end::path[]
public class MQPutGetResource {
    @Inject
	private JMSHelper jmsHelper;

    @Inject
	Counter counter;

    // tag::get[]
    @GET
    // end::get[]
    // tag::produces[]
    @Produces(MediaType.APPLICATION_JSON)
    // end::produces[]
    public String sendReceiveMessage() {
        int i = counter.getLastSent();
        String msg_send = String.valueOf(i);
        String result = jmsHelper.sendMessage(msg_send);
        counter.setLastSent(i+1);
        String msg_receive = jmsHelper.receiveMessage();
        System.out.println("Sent/Received: " + result + "/" + msg_receive);
        return msg_send + "/" + msg_receive;
    }

}
