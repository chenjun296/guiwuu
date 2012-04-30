/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.bayeux;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daijun
 */
public class DisconnectRequestTest {

    @Test
    public void testIsValid() {
        System.out.println("Static isValid...");
        DisconnectRequest disconnectRequest = new DisconnectRequest("client0");
        assertTrue(DisconnectRequest.isValid(disconnectRequest));
        assertTrue(disconnectRequest.isValid());
    }
}
