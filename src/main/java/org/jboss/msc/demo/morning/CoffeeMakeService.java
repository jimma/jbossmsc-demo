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

import org.jboss.msc.inject.Injector;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.msc.value.InjectedValue;

/**
 * @author <a href="mailto:ema@redhat.com"/>Jim Ma</a>
 *
 */
public class CoffeeMakeService implements Service<Coffee> {

    private Coffee coffee;
    private InjectedValue<CoffeePowder> powder = new InjectedValue<CoffeePowder>();
    public CoffeeMakeService() {
       
    }
    public Coffee getValue() throws IllegalStateException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return coffee;
    }

    /* (non-Javadoc)
     * @see org.jboss.msc.service.Service#start(org.jboss.msc.service.StartContext)
     */
    public void start(StartContext context) throws StartException {
        MessagesLogger.ROOT.startService(this.getClass().getName());
        System.out.println("Coffee powder brand : " + powder.getValue().getBrand());
        coffee = new Coffee(Coffee.CoffeeType.Espresso);
        
    }

    /* (non-Javadoc)
     * @see org.jboss.msc.service.Service#stop(org.jboss.msc.service.StopContext)
     */
    public void stop(StopContext context) {
        MessagesLogger.ROOT.stopService(this.getClass().getName());
        
    }

    public Injector<CoffeePowder> getCoffeePowderInjector() {
        return this.powder;
    }
}
