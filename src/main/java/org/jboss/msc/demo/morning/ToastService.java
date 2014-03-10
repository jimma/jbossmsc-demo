/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
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

import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

/**
 * @author <a href="mailto:ema@redhat.com"/>Jim Ma</a>
 *
 */
public class ToastService implements Service<Bread>{

    private Bread bread;
    /* (non-Javadoc)
     * @see org.jboss.msc.value.Value#getValue()
     */
    
    public ToastService(Bread bread) {
        this.bread = bread;
    }
    public Bread getValue() throws IllegalStateException, IllegalArgumentException {
        return bread;
    }

    /* (non-Javadoc)
     * @see org.jboss.msc.service.Service#start(org.jboss.msc.service.StartContext)
     */
    public void start(StartContext context) throws StartException {
        MessagesLogger.ROOT.startService(getClass().getSimpleName());
        bread.setToasted(true);
        
    }

    /* (non-Javadoc)
     * @see org.jboss.msc.service.Service#stop(org.jboss.msc.service.StopContext)
     */
    public void stop(StopContext context) {
        MessagesLogger.ROOT.stopService(getClass().getSimpleName());
        
    }

}