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

import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceBuilder.DependencyType;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;

/**
 * Main class to create ServiceContainer and start all the services
 * @author <a href="mailto:ema@redhat.com>Jim Ma</a>
 *
 */
public class MorningLauncher 
{
    public static final ServiceName TOOTHBRUSHSN = ServiceName.JBOSS.of("ToothBrush");
    public static final ServiceName BREAKFEASTSN = ServiceName.JBOSS.of("Breakfeast");
    public static final ServiceName COFFEESN = ServiceName.JBOSS.of("Coffee");
    public static final ServiceName TOASTSN = ServiceName.JBOSS.of("Toast");
    public static final ServiceName GRINDSN = ServiceName.JBOSS.of("Grind");
    
    public static void main( String[] args )
    {
        ServiceContainer mscContainer = ServiceContainer.Factory.create("MorningServer", true); 
        //ToothBrush Service 
        ToothBrushService toothBrushService = new ToothBrushService();
        ServiceBuilder<Tooth> serviceBuilder = mscContainer.addService(TOOTHBRUSHSN, toothBrushService);
        //Depends on Breakfeast and injected the tooth from it
        serviceBuilder.addDependency(DependencyType.REQUIRED, BREAKFEASTSN, Tooth.class, toothBrushService.getToothInjector());
        serviceBuilder.install();
        
        //breakfeast service
        BreakfeastService breakFeastService = new BreakfeastService();
        ServiceBuilder<Tooth> breakfeastServiceBuilder = mscContainer.addService(BREAKFEASTSN, breakFeastService);
        breakfeastServiceBuilder.addDependency(DependencyType.REQUIRED, TOASTSN, Bread.class, breakFeastService.getBreadInjector());
        breakfeastServiceBuilder.addDependency(DependencyType.REQUIRED, COFFEESN, Coffee.class, breakFeastService.getCoffeeInjector());
        breakfeastServiceBuilder.install();
        
        
        //coffee service
        CoffeeMakeService coffeeService = new CoffeeMakeService();
        ServiceBuilder<Coffee> coffeeServiceBuilder = mscContainer.addService(COFFEESN, coffeeService);
        coffeeServiceBuilder.addDependency(DependencyType.REQUIRED, GRINDSN, CoffeePowder.class, coffeeService.getCoffeePowderInjector());
        coffeeServiceBuilder.install();
        
        //grind service
        CoffeeGrindService grindService = new CoffeeGrindService("illy");
        ServiceBuilder<CoffeePowder> grindServiceBuilder = mscContainer.addService(GRINDSN, grindService);
        grindServiceBuilder.install();
        
        //breadService
        ToastService toastService = new ToastService(new Bread());
        ServiceBuilder<Bread> toastServiceBuilder = mscContainer.addService(TOASTSN, toastService);
        toastServiceBuilder.install();
        
        
        
        
        
        
    }
}
