/**
 * Copyright (c) 2008 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 * 
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation. 
 */
package com.redhat.rhn.frontend.action.schedule;

import com.redhat.rhn.domain.action.server.ServerAction;
import com.redhat.rhn.domain.server.ServerFactory;
import com.redhat.rhn.frontend.struts.RequestContext;
import com.redhat.rhn.frontend.struts.RhnListAction;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CompletedSystemsAction
 * @version $Rev$
 */
public class ClearSystemActionsAction extends RhnListAction {
    
	private static String DAYS_PARAM  = "days_to_clear";
	
	
	
    /** {@inheritDoc} */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm formIn,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    
    	String days = request.getParameter(DAYS_PARAM);
    	if (days != null) {
    		int daysInt;
    		try {
    			daysInt = Integer.parseInt(days);
    		}
    		catch(Exception e) {
    			//throw an error
    		}
    		Set<ServerAction> actions = null;
    		ServerFactory.deleteActions(actions);
    		
    		
    	}
    	
        Map params = getParamMap(request);
        
        return getStrutsDelegate().forwardParams(mapping.findForward("default"), params);
    }
    
    /**
     * Makes a parameter map containing request params that need to
     * be forwarded on to the success mapping.
     * @param request HttpServletRequest containing request vars
     * @return Returns Map of parameters
     */
    private Map getParamMap(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        
        Map params = requestContext.makeParamMapWithPagination();
        //Add action id to the params
        Long aid = requestContext.getParamAsLong("aid");
        params.put("aid", aid);
        
        return params;
    }
}
