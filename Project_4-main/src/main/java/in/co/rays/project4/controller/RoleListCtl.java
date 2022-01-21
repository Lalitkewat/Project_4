package in.co.rays.project4.controller;

import in.co.rays.project4.bean.BaseBean;
import in.co.rays.project4.bean.RoleBean;
import in.co.rays.project4.exception.ApplicationException;
import in.co.rays.project4.model.RoleModel;
import in.co.rays.project4.util.DataUtility;
import in.co.rays.project4.util.PropertyReader;
import in.co.rays.project4.util.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Role functionality Controller. Performs operation for list, search
 * and delete operations of Role.
 * @author Lalit Kewat
 *
 */
@ WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends BaseCtl {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RoleListCtl.class);
	
	protected void preload(HttpServletRequest request) {
		RoleModel rm = new RoleModel();
		try {
			List rlist =  rm.list();
			request.setAttribute("name", rlist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        RoleBean bean = new RoleBean();
        
        bean.setId(DataUtility.getLong(request.getParameter("fname")));

        return bean;
    }

    /**
     * Contains Display logics
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("RoleListCtl doGet Start");
        List list = null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        RoleBean bean = (RoleBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        RoleModel model = new RoleModel();
        list = model.search(bean, pageNo, pageSize);
		ServletUtility.setList(list, request);
		if (list == null || list.size() == 0) {
		    ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
        log.debug("RoleListCtl doGet End");
    }

    /**
     * Contains Submit logics
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("RoleListCtl doPost Start");
        List list = null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        RoleBean bean = (RoleBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));

        RoleModel model = new RoleModel();
        ServletUtility.setBean(bean, request);
        if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
		        || "Previous".equalsIgnoreCase(op) || OP_RESET.equalsIgnoreCase(op)) {

		    if (OP_SEARCH.equalsIgnoreCase(op)) {
		        pageNo = 1;
		    } else if (OP_NEXT.equalsIgnoreCase(op)) {
		        pageNo++;
		    } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
		        pageNo--;
		    }else if(OP_RESET.equalsIgnoreCase(op)){
		    	ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
		    	return;
		    }else {
		    	ServletUtility.setErrorMessage("Select atleast one record", request);
		    }

		}
		list = model.search(bean, pageNo, pageSize);
		ServletUtility.setList(list, request);
		if (list == null || list.size() == 0) {
		    ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);

		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
        log.debug("RoleListCtl doPost End");
    }

    @Override
    protected String getView() {
        return ORSView.ROLE_LIST_VIEW;
    }

}