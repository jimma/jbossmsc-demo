/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.msc.demo.morning;
import static org.jboss.logging.Logger.Level.INFO;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

/**
 * Logger class
 * @author <a href="mailto:ema@redhat.com>Jim Ma</a>
 *
 */
@MessageLogger(projectCode = "Morning")
public interface MessagesLogger extends BasicLogger {
	MessagesLogger ROOT = Logger.getMessageLogger(MessagesLogger.class, "org.jboss.msc.demo.morning");
    @LogMessage(level = INFO)
    @Message(id = 1000, value = "Start service %s")
    void startService(String serviceName);
    @LogMessage(level = INFO)
    @Message(id = 1001, value = "Stop service %s")
    void stopService(String serviceName);
}
