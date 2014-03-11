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

import org.jboss.msc.inject.Injector;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.msc.value.InjectedValue;

/**
 * ToothBrush service to demonstrate the clean brush
 * 
 * @author <a href="mailto:ema@redhat.com>Jim Ma</a>
 * 
 */
public class ToothBrushService implements Service<Tooth> {

    private Tooth result;
    private InjectedValue<Tooth> tooth = new InjectedValue<Tooth>();

    public Tooth getValue() throws IllegalStateException, IllegalArgumentException {
        return result;

    }

    public void start(StartContext context) throws StartException {       
        if (tooth.getValue().equals(Tooth.DIRTY)) {
            result = Tooth.CLEANED;
        }
        MessagesLogger.ROOT.startService(this.getClass().getSimpleName());
    }

    public void stop(StopContext context) {
        MessagesLogger.ROOT.stopService(this.getClass().getSimpleName());
    }

    public Injector<Tooth> getToothInjector() {
        return tooth;
    }

}
