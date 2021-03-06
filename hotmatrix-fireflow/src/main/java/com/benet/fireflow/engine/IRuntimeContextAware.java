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

/**
 * 类似<link>IWorkflowSessionAware</link>
 * @author 非也，nychen2000@163.com
 * @see com.benet.fireflow.engine.IWorkflowSessionAware
 */
public interface IRuntimeContextAware {
    /**
     * @param ctx
     */
    public void setRuntimeContext(RuntimeContext ctx);
    
    /**
     * @return
     */
    public RuntimeContext getRuntimeContext();
}
