/**
 * Copyright 2007-2008 非也
 * All rights reserved. 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation。
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses. *
 */
package com.benet.fireflow.engine;


import com.benet.fireflow.engine.calendar.DefaultCalendarService;
import com.benet.fireflow.engine.condition.ConditionResolver;
import com.benet.fireflow.engine.definition.DefinitionService4DBMS;
import com.benet.fireflow.engine.persistence.DefaultPersistenceService;

/**
 * 在没有spring环境下构建RuntimeContext实例。<br/>
 * (暂未实现)
 * @author chennieyun
 */
public class RuntimeContextFactory {
    private static RuntimeContext ctx = null;
    
    public static RuntimeContext getRuntimeContext(String appCode){
        if (ctx==null){
            //ctx = RuntimeContext.getInstance();
            ctx=new RuntimeContext();
            ctx.setAppCode(appCode);
            ctx.setPersistenceService(new DefaultPersistenceService());
            ctx.setCalendarService(new DefaultCalendarService());
            ctx.setConditionResolver(new ConditionResolver());
            ctx.setDefinitionService(new DefinitionService4DBMS());
            ctx.setEnableTrace(true);
        }
        return ctx;
    }
}
